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

            // Add Sender to Receiver's teamMates
            Query queryReceiver = new Query();
            queryReceiver.addCriteria(Criteria.where("username").is(Receiver));
            Update updateReceiver = new Update();
            updateReceiver.addToSet("teamMates", Sender);
            mongoTemplate.updateFirst(queryReceiver, updateReceiver, User.class);

            // Add Receiver to Sender's teamMates
            Query querySender = new Query();
            querySender.addCriteria(Criteria.where("username").is(Sender));
            Update updateSender = new Update();
            updateSender.addToSet("teamMates", Receiver);
            mongoTemplate.updateFirst(querySender, updateSender, User.class);

            // Remove Receiver from Sender's pendingList
            Update removePending = new Update();
            removePending.pull("pendingList", Receiver);
            mongoTemplate.updateFirst(querySender, removePending, User.class);
        }






}
