package com.example.ProKOi.Reposetory.Team;

import com.example.ProKOi.Auth.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamrRepo extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);
     boolean existsByUsername(String username);
}
