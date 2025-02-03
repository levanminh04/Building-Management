package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Transactional
@RestController
@RequestMapping(value = "/api/building")
@RequiredArgsConstructor
public class BuildingAPI {


    private final BuildingService buildingService;

    private final AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public ResponseEntity<?> addOrUpdateBuilding(BuildingDTO buildingDTO) throws IOException {

        try{
            buildingService.addOrUpdateBuilding(buildingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<String> deleteBuilding(@PathVariable List<Long> ids){
        if (ids.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\":\"No IDs provided\"}");
        }
        buildingService.deleteBuildings(ids);
        return ResponseEntity.ok("{\"message\":\"success\"}");   // bên FE lỡ có dòng dataType:"JSON" rồi, tức là mong đợi kiểu JSON đợc gửi về client, nếu để void thì bên client sẽ bị lỗi vì nó không nhận được về kiểu JSON như mong đợi, cho nên là cứ thêm cái responseEntity cho nó êm
    }                                                                 // hoặc bỏ  dataType:"JSON" là dùng void được

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO responseDTO = buildingService.loadStaffs(id);
        return responseDTO;
    }

    @PostMapping("/assignment")
    public ResponseEntity<String> assignBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        assignmentBuildingService.addAssignmentBuildingEntity(assignmentBuildingDTO);
        return ResponseEntity.ok("{\"message\":\"success\"}");   // bên FE lỡ có dòng dataType:"JSON" rồi, tức là mong đợi kiểu JSON đợc gửi về client, nếu để void thì bên client sẽ bị lỗi vì nó không nhận được về kiểu JSON như mong đợi, cho nên là cứ thêm cái responseEntity cho nó êm
    }
}
