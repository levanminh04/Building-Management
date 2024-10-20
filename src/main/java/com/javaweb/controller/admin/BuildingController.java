package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Transactional
@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingRepository buildingRepository;

//    Khi bạn sử dụng @ModelAttribute trên một phương thức hoặc tham số của phương thức trong controller,
//    Spring sẽ tự động liên kết (bind) dữ liệu từ request (thường là từ form) đến đối tượng đó.
//    Điều này rất hữu ích khi bạn có form HTML với nhiều trường nhập liệu (input fields),
//    và muốn tất cả dữ liệu form đó được gán vào một đối tượng Java tương ứng.

    @GetMapping(value = "/admin/building-list")    // "/admin/building-list" là trang sẽ hiển thị cho người dùng thấy khi nhấp vào liên kết
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/building_list"); // đây không phải là URL. Nó là tên view mà Spring Boot sẽ sử dụng để tìm tệp giao diện và hiển thị cho người dùng.
        mav.addObject("modelSearch" , buildingSearchRequest);

        List<BuildingSearchResponse> responseList = buildingService.findAll(buildingSearchRequest);
        if(buildingSearchRequest != null) {
            mav.addObject("buildingList", responseList);
        }
        mav.addObject("staffList", userService.getStaffs());
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }


    //ModelAndView chỉ nhận api với 2 cấp thôi ( tức là 2 cái dấu '/' như dưới (ví dụ /a/b), thêm một cấp (/a/b/c) là nó ko send được

    // bắt buộc phải sử dụng Get nếu không sẽ bị lỗi, lí do là vì
    // Khi người dùng nhấp vào liên kết trong trình duyệt, trình duyệt sẽ GỬI YÊU CẦU GET.
    // Nếu bạn không có phương thức được định nghĩa cho yêu cầu GET, bạn sẽ nhận được lỗi 405.
    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute BuildingDTO buildingDTO ,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/building_edit");
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }



    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/building_edit");


        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        if(buildingEntity.getRentAreaEntities() != null)
        {
            List<RentAreaEntity> rentAreaEntity = buildingEntity.getRentAreaEntities();
            List<String> rentAreaList = new ArrayList<>();
            for (RentAreaEntity it : rentAreaEntity) {
                rentAreaList.add(it.getValue().toString());
            }
            buildingDTO.setRentArea(String.join(",", rentAreaList));
        }
        if(buildingEntity.getType() != null && buildingEntity.getType() != "")
        {
            String[] s = buildingEntity.getType().split(",");
            List<String> a = Arrays.asList(s);
            buildingDTO.setTypeCode(a);
        }


        mav.addObject("buildingEdit", buildingDTO);    // ném cái buildingEdit về để khi bấm vào nút sửa tòa nhà thì các data đã có trước đó phải được hiện lên field
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }


}
