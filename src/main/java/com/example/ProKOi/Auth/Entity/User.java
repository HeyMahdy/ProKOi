package com.example.ProKOi.Auth.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private ObjectId objectId;

    @Indexed(unique = true)
    private String username;
    private String password;
    private String role;



}