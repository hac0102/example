package com.example.hac.example.api.notice.mapper;

import com.example.hac.example.api.notice.model.TodoModel;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {

    Page<TodoModel> selectNoticeList();
}
