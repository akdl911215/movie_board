package com.jh.move_review.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
@RequestMapping("/movie/")
public class UploadController {

    @Value("${movie.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public void uploadFile(MultipartFile[] uploadFiles) {
        log.info("start");

        log.info("isEmpty : " + uploadFiles.length);
        for (MultipartFile uploadFile: uploadFiles) {
            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            log.info("originalName : " + originalName);
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName : " + fileName);
        }
    }
}
