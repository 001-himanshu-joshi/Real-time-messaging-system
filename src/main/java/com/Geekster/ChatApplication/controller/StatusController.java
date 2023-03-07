package com.Geekster.ChatApplication.controller;

import com.Geekster.ChatApplication.model.Status;
import com.Geekster.ChatApplication.model.User;
import com.Geekster.ChatApplication.service.StatusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/status")
public class StatusController {

    @Autowired
    StatusService statusService;
    @PostMapping("/saveStatus")
    public ResponseEntity saveStatus(@RequestBody String status){
        Status newStatus = setStatus(status);
        return new ResponseEntity<>("Status stored with ID "+ statusService.postStatus(newStatus), HttpStatus.CREATED);
    }

    private Status setStatus(String status) {
        JSONObject json = new JSONObject(status);
        Status statusForm = new Status();
        statusForm.setStatusName(json.getString("statusName"));
        statusForm.setDescription(json.getString("description"));
        return statusForm;
    }
}
