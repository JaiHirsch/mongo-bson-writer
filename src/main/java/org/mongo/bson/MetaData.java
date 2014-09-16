package org.mongo.bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MetaData {
   private DBObject meta;

   public MetaData(int v, String key, int dir, String name, String ns) {
      meta = new BasicDBObject();
      meta.put("v", v);
      meta.put("key", new BasicDBObject(key, dir));
      meta.put("name", name);
      meta.put("ns", ns);

   }

   public DBObject getMetaData() {
      return meta;
   }

   public String toString() {
      return meta.toString();
   }
}
