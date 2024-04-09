package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.NoticeDto;
import com.acetutoring.api.services.NoticeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/notices")
public class NoticeController {
    private NoticeService noticeService;

    @GetMapping("{noticeId}")
    public ResponseEntity<NoticeDto> getNoticeById(@PathVariable Long noticeId){
        return ResponseEntity.ok(noticeService.getNoticeById(noticeId));
    }

    @GetMapping
    public ResponseEntity<List<NoticeDto>> getAllNotices(){
        return ResponseEntity.ok(noticeService.getAllNotice());
    }

    @PostMapping
    public ResponseEntity<NoticeDto> createNotice(@RequestBody NoticeDto noticeDto){
        return ResponseEntity.ok(noticeService.createNotice(noticeDto));
    }

    @DeleteMapping("{noticeId}")
    public String deleteNoticeById(@PathVariable Long noticeId){
        noticeService.deleteNoticeById(noticeId);
        return "Notice deleted successfully.";
    }
}
