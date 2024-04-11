package com.acetutoring.api.services.implementations;

import com.acetutoring.api.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
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
}
