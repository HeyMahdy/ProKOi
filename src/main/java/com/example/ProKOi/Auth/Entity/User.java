package com.example.ProKOi.Auth.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    private ObjectId objectId;

    @Indexed(unique = true)
    private String username;
    private String password;
    private String email;
    private Set<User> teamMates = new HashSet<>();
    private Set<User> pendingList = new HashSet<>();




}