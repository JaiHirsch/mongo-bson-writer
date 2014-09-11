package org.simple.mysql;

import java.sql.SQLException;

import org.mongo.bson.Indexizer;

import com.mongodb.BasicDBList;

public class MySqlIndexizer implements Indexizer {
	
	private MySqlDao dao;

	public MySqlIndexizer(String connString) throws SQLException {
		dao = new MySqlDao(connString);
		
	}

	@Override
	public BasicDBList getMetaData(String nameSpace, String collectionName) {
		try {
			return dao.getIndexInfoForTable(nameSpace, collectionName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
