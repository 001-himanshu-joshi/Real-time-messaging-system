package com.Geekster.ChatApplication.controller;

import ch.qos.logback.core.model.Model;
import com.Geekster.ChatApplication.model.User;
import com.Geekster.ChatApplication.service.UserService;
import com.Geekster.ChatApplication.utils.UserValidator;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserValidator userValidator;

    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestBody String user){
        JSONObject jsonObject = userValidator.validator(user);
        User newUser = null;
        if(jsonObject.isEmpty()){
            newUser = userValidator.setUser(user);
        }else {
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User is saved with user Id "+ userService.postUser(newUser), HttpStatus.CREATED);
    }
    @GetMapping("getUserById")
    public ResponseEntity getUserById(@Nullable @RequestParam Integer userId){
        JSONArray jsonArray = userService.getById(userId);
        return new ResponseEntity<>(jsonArray.toString(), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity loginSystem(@RequestBody String userData){
        JSONObject json = new JSONObject(userData);
        JSONObject isValid = userValidator.validateLogin(json);
        if(isValid.isEmpty()){
            String userName = json.getString("userName");
            String password = json.getString("password");
            JSONObject response = userService.login(userName, password);
            if(response.has("Error")){
                return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(response.toString(), HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity update(@PathVariable String userId, @RequestBody String userData){
        JSONObject isValid = userValidator.validator(userData);
        User user = null;
        if(isValid.isEmpty()){
            user = userValidator.setUser(userData);
            JSONObject jsonObject = userService.putUser(user, userId);
            if(jsonObject.has("Error")){
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User is updated", HttpStatus.OK);
    }


    @DeleteMapping(value = "/deleteUser/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId){
        userService.deleteUserByUserId(userId);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

}
