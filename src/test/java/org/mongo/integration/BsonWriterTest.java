package org.mongo.integration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mongo.bson.BSONFileWriter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class BsonWriterTest {

	@Test
	public void test() throws IOException {
		File delfile = new File("dump/test/foo.bson");
		if (delfile.exists()) {
			delfile.delete();
		}
		File dump = new File("dump");
		dump.mkdir();
		File dbDir = new File("dump/test");
		dbDir.mkdir();
		BSONFileWriter writer = new BSONFileWriter("dump/test/foo.bson");
		List<DBObject> dbos = new ArrayList<DBObject>();
		dbos.add(createDbo("Bob"));
		dbos.add(createDbo("Squid"));
		dbos.add(createDbo("Pat"));
		dbos.add(createDbo("Bob"));

		for (DBObject dbo : dbos) {
			writer.write(dbo);
		}
	}

	private DBObject createDbo(String name) {
		DBObject dbo = new BasicDBObject();
		dbo.put("f_name", name);
		dbo.put("l_name", "SquarePants");

		DBObject addr = new BasicDBObject();
		addr.put("home", "pineapple");
		addr.put("work", "krusty krab");

		DBObject stats = new BasicDBObject();
		stats.put("age", 14);
		stats.put("friends", Arrays.asList(new String[] { "Sandy", "Patric" }));
		stats.put("where", Arrays.asList(new Long[] { 34534656l, 74634568l }));

		dbo.put("places", addr);
		dbo.put("stats", stats);
		return dbo;
	}

}
