package org.mongo.bson;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.BasicDBList;

public class ExistingMetaDataRetrieverTest {

	@Test
	public void test() throws UnknownHostException {
		ExistingMetaDataRetriever ret = new ExistingMetaDataRetriever();
		BasicDBList metaData = ret.getMetaData("test.foo");
		System.out.println(metaData);
	}

}
