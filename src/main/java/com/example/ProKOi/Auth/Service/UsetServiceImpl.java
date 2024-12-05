package com.example.ProKOi.Auth.Service;


import com.example.ProKOi.Auth.Entity.User;
import com.example.ProKOi.Auth.Repostry.UserRepostry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsetServiceImpl implements UserService{

    @Autowired
    private UserRepostry userRepostry;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         User u = userRepostry.save(user);
         return u;
    }

}
