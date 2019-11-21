package com.ditraacademy.travelagency.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/user")
    public void adduser(@RequestBody User user){
        userServices.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getusers(){
        List<User> Userlist=userServices.getUsers();
        return Userlist;
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> userupdate (@PathVariable int id,@RequestBody User user){
       return userServices.updateUser(id,user);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
         return userServices.removeUser(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> UserGet(@PathVariable int id){
        return userServices.getUserById(id);
    }

}