package org.mongo.integration;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;
import org.mongo.bson.MetaDataWriter;
import org.simple.mysql.MySqlDao;
import org.simple.mysql.MySqlIndexizer;

import com.mongodb.BasicDBList;

public class MySqlDaoTest {

	@Test
	public void test() throws SQLException {
		MySqlDao dao = new MySqlDao("jdbc:mysql://localhost/test?");

		BasicDBList indexInfoForTable = dao.getIndexInfoForTable("test", "foo");
		System.out.println(indexInfoForTable);
		dao.close();
	}
	
	@Test
	public void testItAllTogether() throws IOException, SQLException {
		MetaDataWriter.writeMetaDataFile("test", "foo", new MySqlIndexizer("jdbc:mysql://localhost/test?"));
	}

}
