package com.inspien.server.controller;

import com.inspien.server.service.FileUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    private final FileUpdateService fileUpdateService;

    public FileUploadController(FileUpdateService fileUpdateService) {
        this.fileUpdateService = fileUpdateService;
    }

    @PostMapping("/fileUpload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("filepath") String filePath) {
        try {
            fileUpdateService.uploadFile(file, filePath);
            return ResponseEntity.ok(" 파일을 성공적으로 업데이트 하였습니다." );
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("파일업로드 실패: " + ex.getMessage());
        }
    }

    @PostMapping("/direcotryUpload")
    public ResponseEntity<String> mkdir(@RequestPart("mkdir") String filePath) {
        try {
            fileUpdateService.uploadDirectory(filePath);
            return ResponseEntity.ok(" 폴더를 성공적으로 업데이트 하였습니다." );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("폴더생성 실패: " + ex.getMessage());
        }
    }

    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestPart("file") String filePath) {
        try{
            fileUpdateService.deleteFile(filePath);
            return ResponseEntity.ok("파일이 성공적으로 삭제되었습니다.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("파일삭제 실패: " + ex.getMessage());
        }
    }

    @PostMapping("/deleteDirectory")
    public ResponseEntity<String> deleteDir(@RequestPart("mkdir") String filePath) {
        try{
            fileUpdateService.deleteDirectory(filePath);
            return ResponseEntity.ok("폴더를 성공적으로 삭제되었습니다.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("폴더삭제 실패: " + ex.getMessage());
        }
    }
}
