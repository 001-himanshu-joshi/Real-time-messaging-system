package com.Geekster.ChatApplication.utils;

import com.Geekster.ChatApplication.dao.StatusRepository;
import com.Geekster.ChatApplication.dao.UserRepository;
import com.Geekster.ChatApplication.model.Status;
import com.Geekster.ChatApplication.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


@Component
public class UserValidator {

    @Autowired
    StatusRepository statusRepository;
    @Autowired
    UserRepository userRepository;

    public  JSONObject validator(String userData){
        JSONObject json = new JSONObject(userData);
        JSONObject errolList = new JSONObject();
        if(json.has("userName")){
            String userName = json.getString("userName");
            if(!json.has("isUpdate")){
                List<User> newUserList = userRepository.findByUsername(userName);
                if(!newUserList.isEmpty()){
                    errolList.put("userName", "This user already exists ");
                    return errolList;
                }
            }
        }else{
            errolList.put("userName", "Type correct UserName");
        }

        if(json.has("email")){
            String email = json.getString("email");
            if(!RegexValidations.isValidEmail(email)){
                errolList.put("email", "Email format is not correct");
            }
        }else{
            errolList.put("email", "Type Correct Email");
        }

//        if(json.has("password")){
//            String password = json.getString("password");
//            if(!isValidPassword(password)){
//                errolList.put("password", "Password format is not correct");
//            }
//        }else{
//            errolList.put("password", "Type Correct password");
//        }


        if(json.has("phoneNumber")){
            String phoneNumber = json.getString("phoneNumber");
            if(!RegexValidations.isValidPhoneNumber(phoneNumber)){
                errolList.put("phoneNumber", "phoneNumber format is not correct");
            }
        }else{
            errolList.put("phoneNumber", "Type Correct phoneNumber");
        }

        if(!json.has("userFirstName")){
            errolList.put("userFirstName", "Type Correct UserName");
        }

        return errolList;
    }
    public User setUser(String user) {
        JSONObject json = new JSONObject(user);
        User userForm = new User();
        userForm.setUserName(json.getString("userName"));
        userForm.setEmail(json.getString("email"));
        userForm.setPassword(json.getString("password"));
        userForm.setPhoneNumber(json.getString("phoneNumber"));
        userForm.setUserFirstName(json.getString("userFirstName"));
        if(json.has("userLastName")){
            userForm.setUserLastName(json.getString("userLastName"));
        }
        if(json.has("age")){
            userForm.setAge(json.getInt("age"));
        }
        if(json.has("gender")){
            userForm.setGender(json.getString("gender"));
        }
        userForm.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Status status = statusRepository.findById(1).get();
        userForm.setStatus(status);
        return userForm;
    }

    public JSONObject validateLogin(JSONObject jsonObject) {
        JSONObject errorList = new JSONObject();
        if(!jsonObject.has("userName")){
            errorList.put("userName", "Missing Parameter");
        }
        if(!jsonObject.has("password")){
            errorList.put("password", "Missing Parameter");
        }
        return errorList;
    }

}
