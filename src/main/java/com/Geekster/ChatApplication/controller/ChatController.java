package com.Geekster.ChatApplication.controller;

import com.Geekster.ChatApplication.model.Chat;
import com.Geekster.ChatApplication.service.ChatService;
import com.Geekster.ChatApplication.utils.ChatValidations;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chat")
public class ChatController {

    @Autowired
    ChatValidations chatValidations;
    @Autowired
    ChatService chatService;

    @PostMapping("/postChat")
    public ResponseEntity saveChat(@RequestBody String chatData){
        JSONObject json = new JSONObject(chatData);
        JSONObject response = chatValidations.validateChat(json);
        if(response.isEmpty()){
            Chat chat = chatValidations.setChat(json);
            int chatId = chatService.postChat(chat);
            return new ResponseEntity<>("Chat is sent with chatId "+chatId, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-chat")
    public ResponseEntity<String> getChatsByUserId(@RequestParam int senderId) {
        JSONObject response = chatService.getChatByUserId(senderId);
        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

    @GetMapping("/get-conversation")
    public ResponseEntity<String> getConversationBetweenTwoUsers(@RequestParam int user1,@RequestParam int user2) {
        JSONObject response = chatService.getConversation(user1, user2);
        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByID/{chatId}")
    public ResponseEntity delete(@PathVariable int chatId){
        String message = chatService.deleteByID(String.valueOf(chatId));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
