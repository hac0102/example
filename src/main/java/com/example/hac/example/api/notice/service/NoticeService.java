package com.example.hac.example.api.notice.service;

import com.example.hac.example.api.notice.mapper.NoticeMapper;
import com.example.hac.example.api.notice.model.NoticeModel;
import com.example.hac.example.api.notice.model.TodoModel;
import com.example.hac.example.util.CipherUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {

    private final int leftLimit = 48;
    private final int rightLimit = 122;
    private final int targetStrigLength = 10;

    private final NoticeMapper noticeMapper;

    public String makeRanmdomAlpha() {
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStrigLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public List<NoticeModel> getNoticeList(NoticeModel noticeModel) {
        List<NoticeModel> noticeList = new ArrayList<>();
        CipherUtil cipherUtil = new CipherUtil();

        for (int i = 0; i < 51; i++) {
        noticeModel = NoticeModel.builder()
                .noticeId(i)
                .platformType(0)
//                .isisEnc(true)
                .email(cipherUtil.encrypt(makeRanmdomAlpha() + Integer.toString(i) + "@naver.com"))
//                .email(makeRanmdomAlpha() + Integer.toString(i) + "@naver.com")
                .name(cipherUtil.encrypt(makeRanmdomAlpha() + Integer.toString(i)))
//                .name(cipherUtil.encrypt(makeRanmdomAlpha() + Integer.toString(i)))
                .title("공지사항 테스트 제목 :: " + Integer.toString(i))
                .content("공지사항 테스트 내용 :: " + Integer.toString(i))
                .isDeleted(0)
                .isUsed(0)
                .isTop(0)
                .createdUserId("공지사항 등록자 " + Integer.toString(i))
                .createDate(LocalDateTime.now())
                .modifiedUserId("공지사항 등록자 " + Integer.toString(i))
                .modifiedDate(LocalDateTime.now())
                .build();

            noticeList.add(noticeModel);
        }



        for(var model : noticeList){

//            model = NoticeModel.builder()
//                    .email(cipherUtil.decrypt(model.getEmail()))
//                    .name(cipherUtil.decrypt(model.getName()))
//                    .build();
            model.setEmail( cipherUtil.decrypt(model.getEmail()) );
            model.setName( cipherUtil.decrypt(model.getName()) );

            log.info("model :: {}", model);
        }

        return  noticeList;
    }


    public Page<TodoModel> getNoticeList2(int pageNo) {
        PageHelper.startPage(pageNo, 10);
        return noticeMapper.selectNoticeList();
    }
}
