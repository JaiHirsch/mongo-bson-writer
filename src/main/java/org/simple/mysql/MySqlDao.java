package org.simple.mysql;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.mongo.bson.BSONFileWriter;
import org.mongo.bson.MetaData;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class MySqlDao implements Closeable {

	private final Connection conn;

	public MySqlDao(String connString) throws SQLException {
		conn = DriverManager.getConnection(connString);
	}

	public void exportMySqlToBSON(String query) throws SQLException, IOException {
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(query);

		Map<String, Object> mapper = new HashMap<String, Object>();

		BSONFileWriter bw = new BSONFileWriter("dump/test/nob.bson");
		while (rs.next()) {

			ResultSetMetaData metaData = rs.getMetaData();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				mapper.put(metaData.getColumnName(i), rs.getObject(i));
				System.out.println(metaData.getColumnClassName(i));
				System.out.println(metaData.getColumnType(i));
				System.out.println("-------------------");
			}
			bw.write(new BasicDBObject(mapper));
			System.out.println(metaData.getColumnCount());
		}

		System.out.println(mapper);

		conn.close();
	}

	public BasicDBList getIndexInfoForTable(String schema, String tableName) throws SQLException {
		BasicDBList rtn = new BasicDBList();
		Statement st = conn.createStatement();
		String query = "SHOW INDEX FROM %s";
System.out.println("schema: "+schema+" tablename:"+tableName);
		ResultSet rs = st.executeQuery(String.format(query, tableName));
		while (rs.next()) {
			MetaData md = new MetaData(1, rs.getString("COLUMN_NAME"), 1, rs.getString("COLUMN_NAME"), schema + "."
					+ tableName);
			rtn.add(md.getMetaData());
		}
		return rtn;
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
