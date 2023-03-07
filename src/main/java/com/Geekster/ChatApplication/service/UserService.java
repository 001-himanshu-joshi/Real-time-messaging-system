package com.Geekster.ChatApplication.service;

import com.Geekster.ChatApplication.dao.StatusRepository;
import com.Geekster.ChatApplication.dao.UserRepository;
import com.Geekster.ChatApplication.model.Status;
import com.Geekster.ChatApplication.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusRepository statusRepository;


    public int postUser(User newUser) {
        User savedUser = userRepository.save(newUser);
        return savedUser.getUserId();
    }

    public JSONArray getById(Integer userId) {
        JSONArray jsonArray = new JSONArray();
        if(null!=userId){
            List<User> userList = userRepository.getAllUserByUserId(userId);
            for(User user : userList){
                JSONObject jsonObject = createGetResponse(user);
                jsonArray.put(jsonObject);
            }
        }else{
            List<User> userList = userRepository.getAllUser();
            for(User user : userList){
                JSONObject jsonObject = createGetResponse(user);
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }

    public JSONObject login(String userName, String password) {
        JSONObject response = new JSONObject();
        List<User> userList = userRepository.findByUsername(userName);
        if(userList.isEmpty()){
            response.put("errorMessage", "UserName doesn't Exists");
        }else{
            User userObj = userList.get(0);
            if(password.equals(userObj.getPassword())){
                response = createGetResponse(userObj);
            }else{
                response.put("errorMessage", "Enter corrected password");
            }
        }
        return response;
    }

    private JSONObject createGetResponse(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getUserId());
        jsonObject.put("userName", user.getUserName());
        jsonObject.put("userFirstName", user.getUserFirstName());
        jsonObject.put("userLastName", user.getUserLastName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("gender", user.getGender());
        jsonObject.put("phoneNumber", user.getPhoneNumber());
        jsonObject.put("createdDate", user.getCreatedDate());
        return jsonObject;
    }

    public JSONObject putUser(User user, String userId) {
        List<User> userList = userRepository.getAllUserByUserId(Integer.valueOf(userId));
        JSONObject jsonObject = new JSONObject();
        if(!userList.isEmpty()){
            User newUser = userList.get(0);
            newUser.setUserId(user.getUserId());
            newUser.setCreatedDate(user.getCreatedDate());
            newUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(newUser);
        }else{
            jsonObject.put("Error", "User doesn't exists");
        }
        return jsonObject;
    }

    public void deleteUserByUserId(int userId) {
        userRepository.deleteUserByUserId(userId);
    }
}
