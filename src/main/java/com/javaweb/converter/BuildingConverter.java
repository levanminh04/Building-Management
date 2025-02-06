package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.FileEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.buildingType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.CloudinaryService;
import com.javaweb.service.FileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BuildingConverter {


    private final ModelMapper modelMapper;

    private final RentAreaConverter rentAreaConverter;

    private final CloudinaryService cloudinaryService;

    private final BuildingRepository buildingRepository;

    private final FileService fileService;

    @Transactional
    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) throws IOException {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
        buildingEntity.setRentAreaEntities(rentAreaConverter.toRentAreaEntityList(buildingDTO, buildingEntity));

        // Lưu building trước để có ID
        buildingEntity = buildingRepository.save(buildingEntity);

        // Upload file và tạo FileEntity
        List<MultipartFile> files = buildingDTO.getFiles();

        if (files != null) {
            for (MultipartFile file : files) {
                Map result = cloudinaryService.uploadFile(file);
                FileEntity fileEntity = FileEntity.builder()
                        .name(result.get("original_filename").toString())
                        .fileUrl(result.get("url").toString())
                        .fileId(result.get("public_id").toString())
                        .buildingid(buildingEntity.getId()) // 🔥 Đảm bảo ID không null
                        .buildingEntity(buildingEntity)
                        .build();
                buildingEntity.getFileEntities().add(fileEntity);
            }
        }
        else{
            List<FileEntity> fileEntityList = fileService.findByBuildingid(buildingDTO.getId());
            buildingEntity.getFileEntities().addAll(fileEntityList);
        }


        // Lưu lại building để cập nhật danh sách fileEntities
        return buildingRepository.save(buildingEntity);
    }

    public BuildingResponse toBuildingResponse(BuildingEntity buildingEntity) {
        BuildingResponse buildingResponse = modelMapper.map(buildingEntity, BuildingResponse.class);
        List<String> url = new ArrayList<>();
        for (FileEntity fileEntity : buildingEntity.getFileEntities()) {
            url.add(fileEntity.getFileUrl());
        }
        buildingResponse.setImage_url(url);
        if(!url.isEmpty())
        {
            buildingResponse.setAvt(url.get(0));
        }
        if(!buildingEntity.getDistrict().isEmpty())
        {
            String district = buildingEntity.getDistrict();
            String districtName = districtCode.fromCode(district);
            buildingResponse.setDistrict(districtName);
        }
        List<String> typecode = List.of(buildingEntity.getType().split(","));
        String tmp = "";
        for(String typeCode : typecode){
            tmp += buildingType.fromCode(typeCode) + ",";
        }
        buildingResponse.setType(tmp.substring(0, tmp.length()-1)); // loại dấu "," cuối
        return buildingResponse;
    }

}
