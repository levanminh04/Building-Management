package com.javaweb.service.impl;

import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.RentAreaRepository;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RentAreaServiceImpl implements RentAreaService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private RentAreaConverter rentAreaConverter;



    @Override
    public void deleteByBuildings(List<Long> ids) {
        rentAreaRepository.deleteByBuildingIdIn(ids);
    }



    @Override
    public void addRentArea(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId()).get();  // ở đoạn trước set id cho buildingDTO thì ở đây mới buildingDTO.getid() được
        rentAreaRepository.deleteByBuildingId(buildingEntity.getId()); // đối với tường hợp cập nhật thì trước khi cập nhật thì phải xóa bỏ cái cũ đi không là nó sẽ hiểu là đang thêm mới đấy, tranhs việc trùng lặp
                                                                        // nếu là thêm mới tòa nhà thì không cần xóa, xóa cũng chả ảnh hưởng gì vì tòa nhà mới thêm đấy  đã có đâu mà xóa
        String[] rentArea = buildingDTO.getRentArea().split(",");

        for(String val : rentArea ) {
            RentAreaEntity rentAreaEntity = rentAreaConverter.toRentAreaEntity(buildingDTO, Long.valueOf(val)); // converter xong thì rentAreaEntity này nó không có id cho nên mới phải xóa trước đó đấy
            rentAreaRepository.save(rentAreaEntity);  // khi này ko có id nên save() nó tự hiểu là đang thêm mới, có id thì nó tự hiểu là đang cập nhật
        }
    }
}

