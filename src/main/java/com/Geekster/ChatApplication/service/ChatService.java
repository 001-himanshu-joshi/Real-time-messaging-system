package com.Geekster.ChatApplication.service;

import com.Geekster.ChatApplication.dao.ChatRepository;
import com.Geekster.ChatApplication.model.Chat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {


    @Autowired
    ChatRepository chatRepository;
    public int postChat(Chat chat) {
        Chat chat1 = chatRepository.save(chat);
        return chat1.getChatId();
    }

    public JSONObject getChatByUserId(int senderId){
        JSONObject responseObject = new JSONObject();
        List<Chat> chatList = chatRepository.getChatByUserId(senderId);
        if(!chatList.isEmpty()){
            responseObject.put("senderId", chatList.get(0).getSender().getUserId());
            responseObject.put("senderName", chatList.get(0).getSender().getUserFirstName());
        }
        JSONArray receivers = new JSONArray();
        for(Chat chat : chatList){
            JSONObject receiverObject = new JSONObject();
            receiverObject.put("receiverId", chat.getReceiver().getUserId());
            receiverObject.put("receiverName", chat.getReceiver().getUserFirstName());
            receiverObject.put("message", chat.getMessage());
            receivers.put(receiverObject);
        }
        responseObject.put("receivers", receivers);
        return responseObject;
    }

    public JSONObject getConversation(int userId1, int userId2){
        List<Chat> chatList = chatRepository.getAllConversations(userId1, userId2);
        JSONObject responseObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        for(Chat chat : chatList){
            JSONObject json = new JSONObject();
            json.put("chatId", chat.getChatId());
            json.put("timeStamp", chat.getCreatedDate());
            json.put("senderName", chat.getSender().getUserFirstName());
            json.put("message", chat.getMessage());
            jsonArray.put(json);
        }
        responseObject.put("All-Message", jsonArray);
        return responseObject;
    }

    public String deleteByID(String chatId) {
        if(null!=chatId && chatRepository.findById(Integer.valueOf(chatId)).isPresent()){
            chatRepository.deleteByID(Integer.valueOf(chatId));
            return "Chat is deleted from database";
        }else{
            return "Id not found";
        }
    }
}
