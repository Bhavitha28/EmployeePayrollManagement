package com.employee.payroll.service;

import com.employee.payroll.entities.dto.UserDto;
import com.employee.payroll.entities.model.User;
import com.employee.payroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public User login(String username, String password) throws AuthenticationException {
       User user =  userRepository.findByUserNameAndPassword(username,password);
       if(user==null){
           throw new AuthenticationException();
       }
       return user;
    }


    public User save(User user) {

        return userRepository.save(user);
    }


    /*public void save(User user){
        userRepository.save(user);
    }*/

    public User getByUsername(String username){
        return userRepository.findByUserName(username);
    }

    public User updateAdmin(User user) {
        return userRepository.save(user);

    }

}
