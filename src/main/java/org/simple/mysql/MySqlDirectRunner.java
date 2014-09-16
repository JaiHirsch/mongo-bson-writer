package org.simple.mysql;

import java.io.IOException;
import java.sql.SQLException;

import org.mongo.bson.Dumps;
import org.mongo.bson.MetaDataWriter;

public class MySqlDirectRunner {

   public static void main(String[] args) throws SQLException, IOException {
      Dumps.createDumpDirectories("test");
      MySqlDao dao = new MySqlDao("jdbc:mysql://localhost/test?");
      dao.exportMySqlToBSON("select * from foo", "dump/test/foo.bson");
      MetaDataWriter.writeMetaDataFile("test", "foo", new MySqlIndexizer(dao));
      dao.close();

   }
}
