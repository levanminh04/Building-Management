package com.javaweb.repository;

import com.javaweb.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Integer>, PagingAndSortingRepository<FileEntity, Integer> {
    List<FileEntity> findByBuildingid(Long id);
}
