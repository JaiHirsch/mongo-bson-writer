package org.mongo.bson;

import java.io.File;

public class Dumps {

   public static final String PATH_PATTERN = "dump/%s/%s.%s";

   public static void createDumpDirectories(String dbName) {
      File dumpDir = new File("dump");

      if (!dumpDir.exists()) {
         dumpDir.mkdir();
      }
      File dbDir = new File("dump/" + dbName);
      if (!dbDir.exists()) {
         dbDir.mkdir();
      }
   }

   public static void removeDumpDirectories(String dbName) {
      File dbDir = new File("dump/" + dbName);
      if (dbDir.exists()) {
         for (File x : dbDir.listFiles()) {
            x.delete();
         }
         dbDir.delete();
      }
      File dumpDir = new File("dump");
      if (dumpDir.exists())
         dumpDir.delete();
   }
}
