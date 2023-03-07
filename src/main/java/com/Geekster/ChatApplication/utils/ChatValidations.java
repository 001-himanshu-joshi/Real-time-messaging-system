package com.Geekster.ChatApplication.utils;

import com.Geekster.ChatApplication.dao.StatusRepository;
import com.Geekster.ChatApplication.dao.UserRepository;
import com.Geekster.ChatApplication.model.Chat;
import com.Geekster.ChatApplication.model.Status;
import com.Geekster.ChatApplication.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ChatValidations {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusRepository statusRepository;
    public JSONObject validateChat(JSONObject json){
        JSONObject errorList = new JSONObject();
        if(!json.has("sender")){
            errorList.put("Error", "Missing Parameter");
        }
        if(!json.has("receiver")){
            errorList.put("Error", "Missing Parameter");
        }
        if(json.has("message")){
            String message = json.getString("message");
            if(message.isEmpty() || message.isEmpty()){
                errorList.put("Error", "Message can't be Empty");
            }
        }else{
            errorList.put("Error", "Missing Parameter");
        }
        return errorList;
    }

    public Chat setChat(JSONObject json) {
        Chat chat = new Chat();
        int senderId = json.getInt("sender");
        int receiverId = json.getInt("receiver");
        String message = json.getString("message");
        int statusId = json.getInt("statusId");

        User sender = userRepository.findById(senderId).get();
        User receiver = userRepository.findById(receiverId).get();
        Status status = statusRepository.findById(statusId).get();

        chat.setSender(sender);
        chat.setReceiver(receiver);
        chat.setMessage(message);
        chat.setStatusId(status);
        chat.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        return chat;
    }
}
