package org.mongo.bson;

import static org.mongo.bson.GlobalConstants.PATH_PATTERN;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MetaDataWriter {

	public static synchronized void writeMetaDataFile(String DBName, String DBCollectionName, Indexizer idx)
			throws IOException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(String.format(PATH_PATTERN, DBName, DBCollectionName,
					"metadata.json")));
			StringBuilder sb = new StringBuilder();
			sb.append("{ \"indexes\" : ");
			sb.append(idx.getMetaData(DBName,DBCollectionName));
			sb.append(" }");
			System.out.println("creating meta file with following info:" + sb.toString());
			bw.write(sb.toString());
			bw.newLine();
		} finally {
			if (bw != null) bw.close();
		}
	}
}
