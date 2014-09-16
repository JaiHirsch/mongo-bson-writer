package org.simple.mysql;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MySqlDirectRunner {

   public static void main(String[] args) throws SQLException, IOException {
      File d = new File("dump");
      d.mkdir();

      File t = new File("dump/test");
      t.mkdir();

      MySqlDao dao = new MySqlDao("jdbc:mysql://localhost/test?");
      dao.exportMySqlToBSON("select * from foo", "dump/test/foo.bson");
      dao.close();

   }
}
