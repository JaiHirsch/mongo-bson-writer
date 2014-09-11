package org.mongo.bson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BsonWriter {

	private static String DBName;
	private static String DBCollectionName;
	private static String nameSpace;
	private static BSONFileWriter BSONWriter;
	
	private static final String PATH_PATTERN = "dump/%s/%s.%s";

	public static void main(String[] args) throws IOException {
		
		init(args[0]);
		createDumpDirectories("test");
		createMetaFile();

		writeBsonFile("test.foo");
	}

	private static void init(String nameSpace) {
		BsonWriter.nameSpace = nameSpace;
		String[] nameSpaceSplit = nameSpace.split("\\.");
		DBName = nameSpaceSplit[0];
		DBCollectionName = nameSpaceSplit[1];
		
		BSONWriter = new BSONFileWriter("");
		
	}

	private static void writeBsonFile(String nameSpace) throws IOException {
		
	}

	private static void createMetaFile() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(String.format(PATH_PATTERN, DBName,DBCollectionName,"metadata.json")));
		StringBuilder sb = new StringBuilder();
		sb.append("{ \"indexes\" : ");
		sb.append(ExistingMetaDataRetriever.getMetaData(nameSpace));
		sb.append(" }");
		System.out.println("creating meta file with following info:" + sb.toString());
		bw.write(sb.toString());
		bw.newLine();
		bw.close();
	}

	private static void createDumpDirectories(String dbName) {
		// dump/test
		File dumpDir = new File("dump");

		if (!dumpDir.exists()) {
			dumpDir.mkdir();
		}
		File dbDir = new File("dump/"+dbName);
		if (!dbDir.exists()) {
			dbDir.mkdir();
		}
	}
}