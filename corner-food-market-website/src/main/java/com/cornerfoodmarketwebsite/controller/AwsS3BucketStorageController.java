package com.cornerfoodmarketwebsite.controller;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.cornerfoodmarketwebsite.business.service.AwsS3BucketStorageService;
import com.cornerfoodmarketwebsite.business.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/client")
public class AwsS3BucketStorageController {
    private final AwsS3BucketStorageService awsS3BucketStorageService;
    private final ExceptionLogService exceptionLogService;

    @Autowired
    public AwsS3BucketStorageController(AwsS3BucketStorageService awsS3BucketStorageService, ExceptionLogService exceptionLogService) {
        this.awsS3BucketStorageService = awsS3BucketStorageService;
        this.exceptionLogService = exceptionLogService;
    }

    @GetMapping(value = "/{client-domain-name}/images/{image-category}/{image-file-name}")
    public ResponseEntity<byte[]> getPublicImage(@PathVariable(value = "client-domain-name") String clientDomainName,
                                               @PathVariable(value = "image-category") String imageCategory,
                                                          @PathVariable(value = "image-file-name") String imageFileName) {
        try {
            String imageFilePathName = "api/client-specific/" + clientDomainName + "/images/" + imageCategory + "/" + imageFileName;
            byte[] imageBytes = this.awsS3BucketStorageService.downloadFile(imageFilePathName);
//            ByteArrayResource byteArrayResource = new ByteArrayResource(imageBytes);
//            return ResponseEntity.ok().contentLength(imageBytes.length)
//                    .header("Content-Type", "Application/Octet-Stream")
//                    .header("Content-Disposition", "Attachment; Filename=\"" + imageFileName + "\"")
//
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch (AmazonS3Exception amazonS3Exception) {
            this.exceptionLogService.logException(amazonS3Exception);
            amazonS3Exception.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            this.exceptionLogService.logException(exception);
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
