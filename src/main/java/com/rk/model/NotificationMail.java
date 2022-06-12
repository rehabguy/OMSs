package com.rk.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NotificationMail {
    private String from;
    private String to;
    private String content;
    private String subject;
}
