package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.NoticeDto;
import com.acetutoring.api.entities.Notice;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoticeMapper {
    public static NoticeDto mapToNoticeDto(Notice notice){
        return new NoticeDto(
                notice.getId(),
                notice.getTitle(),
                notice.getMessage(),
                UserMapper.mapToUserDto(notice.getCreatedBy()),
                notice.getCreatedAt(),
                notice.getUpdatedAt()
        );
    }

    public static Notice mapToNotice(NoticeDto noticeDto){
        return new Notice(
                noticeDto.getId(),
                noticeDto.getTitle(),
                noticeDto.getMessage(),
                UserMapper.mapToUser(noticeDto.getCreatedBy()),
                noticeDto.getCreatedAt(),
                noticeDto.getUpdatedAt()
        );
    }
}
