package com.eschool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eschool.beans.User;

@Service
public class UserService {
    @Autowired
    private UserRepository urepo;
    public User getUserByEmailAndPassword(String email, String password) {
        return urepo.findByEmailAndPassword(email, password);
    }
    public User getUserByEmail(String email) {
        return urepo.findByEmail(email);
    }
    // This function should have void return type
    public User saveUser(User user) {
        return urepo.save(user);
    }
}
