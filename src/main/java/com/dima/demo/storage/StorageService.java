package com.dima.demo.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.dima.demo.exception.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;
    private final StorageRepository storageRepository;

    private final AmazonS3 s3Client;

    public StorageService(StorageRepository storageRepository, AmazonS3 s3Client) {
        this.storageRepository = storageRepository;
        this.s3Client = s3Client;
    }

    public Storage uploadFile(MultipartFile file) {
        file.getContentType();
        System.out.println(file.getSize());
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File fileObj = convertMultiPartFileToFile(file);

        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        String serverBaseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        Storage storageFile = new Storage(
                file.getOriginalFilename(),
                fileName,
                file.getContentType(),
                serverBaseUrl+"/api/v1/file/download/"+fileName,
                serverBaseUrl+"/api/v1/file/download/"+fileName,
                file.getSize()
                );
        storageRepository.save(storageFile);
        fileObj.delete();
        return storageFile;
    }


    public ResponseEntity downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            ObjectMetadata metadata = s3Object.getObjectMetadata();
            String contentType = metadata.getContentType();
            ByteArrayResource resource = new ByteArrayResource(content);
            return ResponseEntity
                    .ok()
                    .contentLength(content.length)
                    .header("Content-type", contentType)
                    .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }
    public Storage getFileById(Long fileId) {
        return storageRepository.findById(fileId).orElseThrow(()->new ApiRequestException("File not found"));
    }



    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file");
        }
        return convertedFile;
    }
}
