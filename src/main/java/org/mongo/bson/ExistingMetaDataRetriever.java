package org.mongo.bson;

import java.net.UnknownHostException;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class ExistingMetaDataRetriever {

	public synchronized static BasicDBList getMetaData(String ns) throws UnknownHostException {
		String database = ns.split("\\.")[0];
		System.out.println("Getting index info for: "+database);
		BasicDBList metaList = new BasicDBList();
		MongoClient mc = null;
		DBCursor find = null;
		try {
			mc = new MongoClient("localhost:27017");
			DBCollection collection = mc.getDB(database).getCollection("system.indexes");
			find = collection.find(new BasicDBObject("ns", ns));
			while (find.hasNext()) {
				metaList.add(find.next());
			}
		} finally {
			find.close();
			mc.close();
		}
		return metaList;
	}

}
