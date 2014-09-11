package org.mongo.bson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.bson.BasicBSONEncoder;

import com.mongodb.DBObject;

public class BSONFileWriter {
	
	private final String pathname;
	private final BasicBSONEncoder encoder;

	public BSONFileWriter(String pathname) {
		this.pathname = pathname;
		this.encoder = new BasicBSONEncoder();
	}

	public void write(DBObject dbo) throws IOException {
		
		Files.write(Paths.get(pathname), encoder.encode(dbo),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		
	}

}
