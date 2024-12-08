package com.example.ProKOi.Reposetory.Team;
import com.example.ProKOi.Auth.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public class CustomTeamRepo {


    @Autowired
    private MongoTemplate mongoTemplate;


    public boolean ifFriend(String sender, String receiver) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("username").is(sender) // Match User A's document
                        .and("teamMates").is(receiver) // Check if User B's username exists in User A's teamMates list
        );

        User userWithTeammate = mongoTemplate.findOne(query, User.class);

        return userWithTeammate != null; // Return true if found, false otherwise
    }
    public boolean IsReqeustSent (String Sender, String Receiver){

        Query query = new Query();
        query.addCriteria(
                Criteria.where("username").is(Receiver) // Match User 2
                        .and("pendingList").is(Sender) // Check if SpecificUser exists in teamMates
        );
        User userWithTeammate = mongoTemplate.findOne(query, User.class);

        if(userWithTeammate != null){
            return true;
        }
        else {
            return false;
        }


    }

   public void addFriend(String Sender , String Receiver){
        Query query =  new Query();
        query.addCriteria(Criteria.where("username").is(Receiver));
        Update update = new Update();
        update.addToSet("pendingList", Sender);
        mongoTemplate.updateFirst(query, update, User.class);

    }

    public boolean ifUserExit(String username){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        User user = mongoTemplate.findOne(query, User.class);
        if(user != null){
            return true;
        }
        else {
            return false;
        }
    }

    public void Friend(String Sender , String Receiver){
        Query query =  new Query();
        query.addCriteria(Criteria.where("username").is(Receiver));
        Update update = new Update();
        update.addToSet("teamMates", Sender);
        mongoTemplate.updateFirst(query, update, User.class);

        Query query1 =  new Query();
        query1.addCriteria(Criteria.where("username").is(Sender));
        Update update1 = new Update();
        update1.addToSet("teamMates", Receiver);
        mongoTemplate.updateFirst(query, update, User.class);

        Query query3 = new Query();
        query3.addCriteria(Criteria.where("username").is(Sender));
        Update update3 = new Update();
        update3.pull("pendingList", Receiver);
        mongoTemplate.updateFirst(query, update, User.class);


    }



}
