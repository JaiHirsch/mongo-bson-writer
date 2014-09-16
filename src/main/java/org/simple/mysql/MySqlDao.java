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

   public void exportMySqlToBSON(String query, String path)
         throws SQLException, IOException {
      BSONFileWriter bsonWriter = new BSONFileWriter(path);
      Statement st = null;
      try {
         Map<String, Object> mapper = new HashMap<String, Object>();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         // use the result set meta data to populate the keys for the hashmap
         // this will allow us to use the column names as the field keys in
         // MongoDB
         ResultSetMetaData metaData = rs.getMetaData();
         while (rs.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
               mapper.put(metaData.getColumnName(i), rs.getObject(i));
            }
            bsonWriter.write(new BasicDBObject(mapper));
         }
      } finally {
         if (st != null)
            st.close();
      }
   }

   public BasicDBList getIndexInfoForTable(String schema, String tableName)
         throws SQLException {
      BasicDBList rtn = new BasicDBList();
      Statement st = conn.createStatement();
      String query = "SHOW INDEX FROM %s";
      ResultSet rs = st.executeQuery(String.format(query, tableName));
      while (rs.next()) {
         MetaData md = new MetaData(1, rs.getString("COLUMN_NAME"), 1,
               rs.getString("COLUMN_NAME")+"_", schema + "." + tableName);
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
