package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.service.AwsS3BucketStorageService;
import javassist.bytecode.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/client-specific")
public class AwsS3BucketStorageController {
    private final AwsS3BucketStorageService awsS3BucketStorageService;

    @Autowired
    public AwsS3BucketStorageController(AwsS3BucketStorageService awsS3BucketStorageService) {
        this.awsS3BucketStorageService = awsS3BucketStorageService;
    }

    @GetMapping(value = "/{request-domain}/images/items/{image-file-name}")
    public ResponseEntity<byte[]> getItemImage(@PathVariable(value = "request-domain") String requestDomain,
                                                          @PathVariable(value = "image-file-name") String imageFileName) {
        try {
            System.out.println("Hello Worldddd");
            String imageFilePathName = "api/client-specific/" + requestDomain + "/images/items/" + imageFileName;
            byte[] imageBytes = this.awsS3BucketStorageService.downloadFile(imageFilePathName);
//            ByteArrayResource byteArrayResource = new ByteArrayResource(imageBytes);
//            return ResponseEntity.ok().contentLength(imageBytes.length)
//                    .header("Content-Type", "Application/Octet-Stream")
//                    .header("Content-Disposition", "Attachment; Filename=\"" + imageFileName + "\"")
//
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
