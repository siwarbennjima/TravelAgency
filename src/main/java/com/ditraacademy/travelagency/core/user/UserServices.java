package com.ditraacademy.travelagency.core.user;


import Utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;


    public ResponseEntity<?> addUser(User user) {
        if (user.getName()== null)
        {
            return new ResponseEntity<>(new ErrorResponseModel("user name required"), HttpStatus.BAD_REQUEST);
        }
        if (user.getName().length() < 3)
            return new ResponseEntity<>(new ErrorResponseModel("user name required"), HttpStatus.BAD_REQUEST) ;

        if (user.getAge()== null)
            return new ResponseEntity<>(new ErrorResponseModel("wrong user age"), HttpStatus.BAD_REQUEST);

        if (user.getAge() < 0)
            return  new ResponseEntity<>(new ErrorResponseModel("wrong user age"), HttpStatus.BAD_REQUEST) ;


       user = userRepository.save(user);
       return new ResponseEntity<>(user,HttpStatus.OK);
    }

    public List<User> getUsers() {
        List<User> userlist = userRepository.findAll();
        return userlist;
    }

    public ResponseEntity updateUser (int id, User updatedUser ) {

        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong user id "), HttpStatus.BAD_REQUEST);
         User databaseUser = userOptional.get();
         if (updatedUser.getName() != null)
             if (updatedUser.getName().length() < 3)
                 return new ResponseEntity<>(new ErrorResponseModel("wrong user name "), HttpStatus.BAD_REQUEST);
             else
                 databaseUser.setName(updatedUser.getName());
        if (updatedUser.getAge() != null)
            if (updatedUser.getAge() < 0)
                return new ResponseEntity<>(new ErrorResponseModel("wrong user age"), HttpStatus.BAD_REQUEST);
            else
                databaseUser.setAge(updatedUser.getAge());
         userRepository.save(databaseUser) ;
         return new ResponseEntity<>(HttpStatus.OK);


    }


    public ResponseEntity<?> removeUser (int id ){
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);

        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getUserById (int id ){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
                    return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
    }
}