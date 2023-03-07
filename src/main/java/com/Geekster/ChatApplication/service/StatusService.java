package com.Geekster.ChatApplication.service;

import com.Geekster.ChatApplication.dao.StatusRepository;
import com.Geekster.ChatApplication.model.Status;
import com.Geekster.ChatApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;
    public int postStatus(Status newStatus) {
        int statusId = statusRepository.save(newStatus).getStatusId();
        return statusId;
    }

}
