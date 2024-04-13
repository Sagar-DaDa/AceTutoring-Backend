package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.NoticeDto;
import com.acetutoring.api.entities.User;
import com.acetutoring.api.mapper.UserMapper;
import com.acetutoring.api.repositories.UserRepo;
import com.acetutoring.api.services.NoticeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserRepo userRepo;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = new User();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        user = userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found. Invalid user email."
                ));
        noticeDto.setCreatedBy(UserMapper.mapToUserDto(user));
        return ResponseEntity.ok(noticeService.createNotice(noticeDto));
    }

    @DeleteMapping("/deleteNotice/{noticeId}")
    public String deleteNoticeById(@PathVariable Long noticeId){
        noticeService.deleteNoticeById(noticeId);
        return "Notice deleted successfully.";
    }
}
