package com.javaweb.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.javaweb.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Override
    public Map uploadFile(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        try {
            if (file.exists()) {
                Map uploadParams = ObjectUtils.asMap("timestamp", System.currentTimeMillis() / 1000);
                System.out.println("Uploading file with params: " + uploadParams);
                Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                return result;
            } else {
                throw new RuntimeException("Temp file does not exist: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Error uploading file to Cloudinary: " + e.getMessage());
            throw e;
        } finally {
            if (file.exists()) {
                boolean deleted = file.delete();
                System.out.println("Deleted temp file: " + file.getAbsolutePath() + " -> " + deleted);
            }
        }
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File tempDir = new File("C:\\Temp");
        if (!tempDir.exists()) {
            tempDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        String sanitizedFileName = Objects.requireNonNull(multipartFile.getOriginalFilename())
                .replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
        File file = new File(tempDir, sanitizedFileName);
        System.out.println("Creating temp file at: " + file.getAbsolutePath());

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }
}
