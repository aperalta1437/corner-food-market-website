package com.cornerfoodmarketwebsite.business.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AwsS3BucketStorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public void uploadFile(String objectKeyName, MultipartFile multipartFile) throws IOException {
        File file = convertMultipartFileToFile(multipartFile);
        s3Client.putObject(new PutObjectRequest(bucketName, objectKeyName, file));
        file.delete();
    }

    public byte[] downloadFile(String objectKeyName) throws IOException {
        S3Object s3Object = s3Client.getObject(bucketName, objectKeyName);
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        return IOUtils.toByteArray(s3ObjectInputStream);
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        return convertedFile;
    }

    public void deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
    }
}
