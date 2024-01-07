package com.practice.service;

import com.practice.entity.FileData;
import com.practice.entity.FileSystemData;
import com.practice.repository.FileDataRepository;
import com.practice.repository.StorageRepository;
import com.practice.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH="/Users/javatechie/Desktop/MyFIles/";

    @Autowired
    private StorageRepository storageRepository;


    //upload the file
    public String fileUpload(MultipartFile file) throws IOException {
        FileData fileData = storageRepository.save(FileData.builder().
                name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtil.compressImage(file.getBytes()))
                .build());

        if(fileData != null){
            return "File uploaded successfully " + file.getOriginalFilename();
        }else {
            return null;
        }

    }

    //download the file
    public byte[] downloadFile(String fileName){
        Optional<FileData> compressedFile = storageRepository.findByName(fileName);
        byte[] file = FileUtil.decompressImage(compressedFile.get().getFileData());
        return file;

    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileSystemData fileData=fileDataRepository.save(FileSystemData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileSystemData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

}
