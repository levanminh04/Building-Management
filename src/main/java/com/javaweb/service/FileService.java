package com.javaweb.service;

import com.javaweb.entity.FileEntity;

import java.util.List;
import java.util.Map;

public interface FileService {
    void save(Map result);
    List<FileEntity> findByBuildingId(Long id);
}
