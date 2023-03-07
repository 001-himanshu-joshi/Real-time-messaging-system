package com.Geekster.ChatApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_table")
public class Chat {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    @ManyToOne
    @JoinColumn(name = "sender_userId")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_userId")
    private User receiver;
    @Column(name = "message")
    private String message;
    @Column(name = "created_Date")
    private Timestamp createdDate;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusId;


}
