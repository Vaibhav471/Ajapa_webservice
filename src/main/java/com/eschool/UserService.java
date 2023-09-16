package com.eschool;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eschool.beans.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class UserService {
    @Autowired
    private UserRepository urepo;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
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
    
    public List<User> findUsersByStatus(int status) {
        String jpql = "SELECT u FROM User u WHERE u.status = :status";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("status", status);
        return query.getResultList();
    }
    
    public User getUserByEmailOrMobileNumberAndPassword(String identifier, String password) {
        return userRepository.findByEmailOrMobileNumberAndPassword(identifier, password);
    }
    
   
    
}
