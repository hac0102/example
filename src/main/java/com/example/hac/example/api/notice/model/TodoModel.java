package com.example.hac.example.api.notice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class TodoModel {

    private int no;
    private int id;
    private String title;
    private String content;
    private LocalDateTime frstRegDate;
    private LocalDateTime lastChgDate;
}
