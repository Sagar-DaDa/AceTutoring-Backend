package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.NoticeDto;
import com.acetutoring.api.entities.Notice;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.NoticeMapper;
import com.acetutoring.api.repositories.NoticeRepo;
import com.acetutoring.api.services.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private NoticeRepo noticeRepo;

    @Override
    public NoticeDto createNotice(NoticeDto noticeDto) {
        return NoticeMapper.mapToNoticeDto(
                noticeRepo.save(NoticeMapper.mapToNotice(noticeDto))
        );
    }

    @Override
    public NoticeDto getNoticeById(Long noticeId) {
        Notice foundNotice = noticeRepo.findById(noticeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Notice not found. Invalid notice ID: "+noticeId
                )
        );
        return NoticeMapper.mapToNoticeDto(foundNotice);
    }

    @Override
    public List<NoticeDto> getAllNotice() {
        return noticeRepo.findAll()
                .stream()
                .map(NoticeMapper::mapToNoticeDto)
                .toList();
    }

    @Override
    public NoticeDto updateNoticeById(Long noticeId, NoticeDto noticeDto) {
        return null;
    }

    @Override
    public void deleteNoticeById(Long noticeId) {
        Notice foundNotice = noticeRepo.findById(noticeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Notice not found. Invalid notice ID: "+noticeId
                )
        );
        noticeRepo.delete(foundNotice);
    }
}
