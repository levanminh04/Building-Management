package com.javaweb.service.impl;

import com.javaweb.entity.FileEntity;
import com.javaweb.repository.FileRepository;
import com.javaweb.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    @Override
    public void save(Map result)  {
        FileEntity fileEntity = FileEntity
                .builder()
                .name(result.get("original_filename").toString())
                .fileUrl(result.get("url").toString())
                .fileId(result.get("public_id").toString())
                .build();
        fileRepository.save(fileEntity);
    }

    @Override
    public List<FileEntity> findByBuildingid(Long id) {
        return fileRepository.findByBuildingid(id);
    }
}
