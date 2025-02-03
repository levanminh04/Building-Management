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
    public Map uploadFile(MultipartFile multipartFile) throws IOException { // Tải ảnh từ ứng dụng lên dịch vụ Cloudinary.
        File file = convert(multipartFile);
        try {
            // Chỉ upload file nếu file tồn tại
            if (file.exists()) {
                Map uploadParams = ObjectUtils.asMap("timestamp", System.currentTimeMillis() / 1000);
                System.out.println("Uploading file with params: " + uploadParams);
                Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                return result;
            } else {
                throw new RuntimeException("Temp file does not exist: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            // Log lỗi để debug nếu cần
            System.err.println("Error uploading file to Cloudinary: " + e.getMessage());
            throw e;
        } finally {
            // Xóa file chỉ khi chắc chắn không sử dụng nữa
            if (file.exists()) {
                boolean deleted = file.delete();
                System.out.println("Deleted temp file: " + file.getAbsolutePath() + " -> " + deleted);
            }
        }
//        File file = convert(multipartFile);
//        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//        return result;                              //        uploader().upload() Trả về một đối tượng kiểu Map chứa thông tin của file đã tải lên, bao gồm:
    }                                               //              URL của file (để có thể sử dụng trong ứng dụng).
                                                    //              ID của file trong Cloudinary.
                                                    //              Các metadata khác liên quan đến file.

    private File convert(MultipartFile multipartFile) throws IOException {
//        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename())); // Tạo một file tạm với tên lấy từ multipartFile.getOriginalFilename(), Objects.requireNonNull() đảm bảo không bị lỗi NullPointerException nếu file không có tên.
//        FileOutputStream fos = new FileOutputStream(file);         //  Mở một luồng ghi để ghi dữ liệu vào file vừa tạo.
//        fos.write(multipartFile.getBytes());                       //  Lấy nội dung file từ multipartFile và ghi vào 'file'.
//        fos.close();
//        return file;
        // Lấy thư mục tạm từ hệ thống
        // Chỉ định thư mục tạm khác, không cần quyền admin
        File tempDir = new File("C:\\Temp");
        if (!tempDir.exists()) {
            tempDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        // Đảm bảo file có tên hợp lệ và duy nhất
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
