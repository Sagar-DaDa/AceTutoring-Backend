package com.acetutoring.api.services;

import com.acetutoring.api.dto.NoticeDto;

import java.util.List;

public interface NoticeService {

    NoticeDto createNotice(NoticeDto noticeDto);

    NoticeDto getNoticeById(Long noticeId);

    List<NoticeDto> getAllNotice();

    NoticeDto updateNoticeById(Long noticeId, NoticeDto noticeDto);

    void deleteNoticeById(Long noticeId);


}
