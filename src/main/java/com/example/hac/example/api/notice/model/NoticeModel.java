package com.example.hac.example.api.notice.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class NoticeModel {
    private int noticeId;
    private int platformType;
    private String email;
    private String name;
    private String title;
    private String content;
    private int isDeleted;
    private int isUsed;
    private int isTop;
    private String createdUserId;
    private LocalDateTime createDate;
    private String modifiedUserId;
    private LocalDateTime modifiedDate;
//    private boolean isisEnc;


    public NoticeModel(int noticeId, int platformType, String email, String  name, String title, String content,
                       int isDeleted, int isUsed, int isTop, String createdUserId, LocalDateTime createDate,
                       String modifiedUserId, LocalDateTime modifiedDate) {
        this.noticeId = noticeId;
        this.platformType = platformType;
//        this.email = isEnc ? enc(email) : dec(email);
        this.email = email;
        this.name = name;
//        this.name = isEnc ? enc(name) : dec(name);
        this.title = title;
        this.content = content;
        this.isDeleted = isDeleted;
        this.isUsed = isUsed;
        this.isTop = isTop;
        this.createdUserId =createdUserId;
        this.createDate = createDate;
        this.modifiedUserId = modifiedUserId;
        this.modifiedDate = modifiedDate;
    }
}
