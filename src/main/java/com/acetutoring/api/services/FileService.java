package com.acetutoring.api.services;

import com.acetutoring.api.dto.AceFileDto;
import com.acetutoring.api.entities.AceFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream getImageResource(String path, String fileName) throws FileNotFoundException;

    String uploadTimetable(String path, MultipartFile file) throws IOException;

    InputStream getTimetable(String path, String fileName) throws FileNotFoundException;

    AceFileDto createTimetable(AceFileDto aceFileDto);

    AceFileDto getLatestTimetable();

}
