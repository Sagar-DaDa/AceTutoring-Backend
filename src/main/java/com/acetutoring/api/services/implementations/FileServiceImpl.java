package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.AceFileDto;
import com.acetutoring.api.entities.AceFile;
import com.acetutoring.api.mapper.AceFileMapper;
import com.acetutoring.api.repositories.FileRepo;
import com.acetutoring.api.services.FileService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepo fileRepo;

    @Value("${project.timetableBaseUrl}")
    private String fileBaseUrl;

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();
        String newGeneratedFileName = randomId.concat(name.substring(name.lastIndexOf(".")));

        String filePath = path + File.separator + newGeneratedFileName;

        File newPath = new File(path);

        if(!newPath.exists()){
            newPath.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return newGeneratedFileName;
    }

    @Override
    public InputStream getImageResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;

        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
    }

    @Override
    public String uploadTimetable(String path, MultipartFile file) throws IOException {
        String newGeneratedFileName = "timetable.pdf";

        String filePath = path + File.separator + newGeneratedFileName;

        File newPath = new File(path);

        if(!newPath.exists()){
            newPath.mkdir();
        }

        Path destinationPath = Paths.get(filePath);
        if (Files.exists(destinationPath)) {
            Files.delete(destinationPath);
        }

        Files.copy(file.getInputStream(), destinationPath);

        Optional<AceFile> fileOptional = fileRepo.findByName("timetable");
        fileOptional.ifPresent(aceFile -> fileRepo.deleteByName("timetable"));

        return newGeneratedFileName;
    }

    @Override
    public InputStream getTimetable(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }

    @Override
    public AceFileDto createTimetable(AceFileDto aceFileDto) {
        return AceFileMapper.mapToAceFileDto(fileRepo.save(AceFileMapper.mapToAceFile(aceFileDto)));
    }

    @Override
    public AceFileDto getLatestTimetable() {
        return AceFileMapper.mapToAceFileDto(fileRepo.findLatestFile().orElseThrow());
    }


}
