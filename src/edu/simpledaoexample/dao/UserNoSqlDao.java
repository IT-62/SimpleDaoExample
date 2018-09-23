package edu.simpledaoexample.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.simpledaoexample.entities.User;
import org.bson.Document;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;


public class UserNoSqlDao extends UserDao {
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    private MongoCollection<Document> users;
    public final String NOSQL_MONGO_URL = "mongodb://<host>:<port>";
    public UserNoSqlDao() {
        mongoClient = MongoClients.create(NOSQL_MONGO_URL);
        if(mongoClient != null) {
            mongoDatabase = mongoClient.getDatabase("<db_name>");
            users = mongoDatabase.getCollection("<table_name>");
        }
    }
    @Override
    public void insert(User entity) {
        if(users != null) {
            users.insertOne(toDocument(entity));
        }
    }

    @Override
    public User getById(int id) {
        if(users != null) {
            return fromDocument(users.find(eq("id", id)).first());
        }
        return null;
    }

    @Override
    public void update(User entity) {
        if(users != null) {
            users.updateOne(eq("id", entity.getId()), toDocument(entity));
        }
    }

    @Override
    public void delete(User entity) {
        if(users != null) {
            users.deleteOne(eq("id", entity.getId()));
        }
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> res = new ArrayList<>();
        if(users != null) {
            Iterator it = users.find().iterator();
            while (it.hasNext()) {
                res.add(fromDocument((Document) it.next()));
            }
        }
        return res;
    }

    public User fromDocument(Document document) {
        return new User((int) document.get("id")
                , (String) document.get("username"), (String) document.get("password"));
    }

    public Document toDocument(User user) {
        Document doc = new Document("id", user.getId())
                .append("username", user.getUsername())
                .append("password", user.getPassword());
        return doc;
    }

    @Override
    public void closeCon() {
        mongoClient.close();
    }
}
