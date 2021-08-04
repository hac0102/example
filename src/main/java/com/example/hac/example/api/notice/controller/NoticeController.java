package com.example.hac.example.api.notice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    @GetMapping("/notice")
    public ModelAndView getNotice() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("notice/notice");
        mv.addObject("noticeData", "공지사항 정보");
        return mv;
    }
}
