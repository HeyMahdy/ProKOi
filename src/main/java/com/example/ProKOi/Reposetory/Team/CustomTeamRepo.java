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


    public boolean ifFriend (String Sender, String Receiver){

        Query query = new Query();
        query.addCriteria(
                Criteria.where("username").is(Sender) // Match User 2
                        .and("teamMates.username").is(Receiver) // Check if SpecificUser exists in teamMates
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



}
