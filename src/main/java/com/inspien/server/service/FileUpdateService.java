package com.inspien.server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileUpdateService {

    @Value("${url.directory}")
    private String serverPath;

    public void uploadFile(MultipartFile file, String uploadPath) throws IOException {
        int lastIndex = uploadPath.lastIndexOf('/');
        String directoryPath = uploadPath.substring(0, lastIndex);
        String filename = file.getOriginalFilename();
        Path targetPath = Path.of(serverPath + directoryPath, filename);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public void deleteFile(String filePath) {
        File file = new File(serverPath+filePath);
        file.delete();
    }

    public void uploadDirectory(String directoryPath) {
        File folder = new File(serverPath+directoryPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void deleteDirectory(String directoryPath) {
        File folder = new File(serverPath+directoryPath);{
            if(folder.exists()){
                folder.delete();
            }
        }
    }


}
