package com.example.hac.example.api.notice.controller;

import com.example.hac.example.api.notice.model.NoticeModel;
import com.example.hac.example.api.notice.model.TodoModel;
import com.example.hac.example.api.notice.service.NoticeService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NoticeController {


    private final NoticeService noticeService;

//    @GetMapping("/notice")
//    public ModelAndView getNotice(NoticeModel noticeModel) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("notice/notice");
//        mv.addObject("noticeData", "공지사항 정보");
//        mv.addObject("noticeData", noticeService.getNoticeList(noticeModel));
//        return mv;
//    }

    @GetMapping("/notice")
    public ModelAndView getNotice(@ModelAttribute TodoModel todoModel,
                                  @RequestParam(required = false, defaultValue = "1") int pageNum,
                                  ModelAndView mv) {
        log.info("pageNum ::: {}", pageNum);

        PageInfo<TodoModel> data = new PageInfo<>(noticeService.getNoticeList2(pageNum), 10);
        mv.addObject("noticeData", data);
        mv.setViewName("notice/notice");
        return mv;
    }
}
