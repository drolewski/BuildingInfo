package com.put.buildinginfo.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@SpringBootTest
@Ignore
public class ConnectToMongoDbTest {
    @Test
    public void mongoDBClientTest() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        DB newDb = mongoClient.getDB("NewTest");
        assertThat(newDb, is(notNullValue()));
    }

    @Test(expected = Exception.class)
    public void shouldNotBeAbleToUseMongoDBClientAfterClose() throws UnknownHostException{
        MongoClient mongoClient = new MongoClient();
        mongoClient.close();
        mongoClient.getDB("SomeDatabase").getCollection("coll").insert(new BasicDBObject("field", "value"));
    }

    @Test
    public void shouldTakeCollectionFromDatabase() throws Exception{
        MongoClient mongoClient = new MongoClient();
        DB newDb = mongoClient.getDB("NewTest");
        DBCollection collection = newDb.getCollection("newCollectionTest");
        assertThat(collection, is(notNullValue()));
    }
}
