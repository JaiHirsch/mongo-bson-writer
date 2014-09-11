package org.mongo.bson;

import com.mongodb.BasicDBList;

public interface Indexizer {


	public BasicDBList getMetaData(String nameSpace, String collectionName);

}
