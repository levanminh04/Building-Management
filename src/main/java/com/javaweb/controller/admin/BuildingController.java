package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserServiceImpl;
import com.javaweb.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Transactional
@RequiredArgsConstructor
@Controller(value="buildingControllerOfAdmin")
public class BuildingController {


    private final ModelMapper modelMapper;

    private final UserServiceImpl userServiceImpl;

    private final BuildingService buildingService;

    private final BuildingRepository buildingRepository;

    private final UserDetailsService userDetailsService;

    private final CookieUtils cookieUtils;
//    Khi bạn sử dụng @ModelAttribute trên một phương thức hoặc tham số của phương thức trong controller,
//    Spring sẽ tự động liên kết (bind) dữ liệu từ request (thường là từ form) đến đối tượng đó.
//    Điều này rất hữu ích khi bạn có form HTML với nhiều trường nhập liệu (input fields),
//    và muốn tất cả dữ liệu form đó được gán vào một đối tượng Java tương ứng.

    @GetMapping(value = "/admin/building-list")    // "/admin/building-list" là trang sẽ hiển thị cho người dùng thấy khi nhấp vào liên kết
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/building_list"); // đây không phải là URL. Nó là tên view mà Spring Boot sẽ sử dụng để tìm tệp giao diện và hiển thị cho người dùng.

        List<String> roles = SecurityUtils.getAuthorities();
        Long userId = null;

        // Chỉ set `staffId` nếu role không phải là MANAGER
        if (!roles.contains("ROLE_MANAGER")) {
            userId =  cookieUtils.getUserId(request);
            buildingSearchRequest.setStaffid(userId); // MANAGER thì hiển thị tất cả các toà nhà hiện có, nếu là STAFF thì chỉ hiển thị các tòa nhà mà nó được giao
        }

        // Tìm kiếm danh sách tòa nhà
        List<BuildingSearchResponse> responseList = buildingService.findAll(buildingSearchRequest);

        mav.addObject("modelSearch" , buildingSearchRequest); //  gửi ngược lại đến frontend để gán lên các field của form, tác dụng là sau khi người dùng bấm tìm kiếm thì các field vẫn được giữ nguyên mà ko phải nhập lại từ đầu
        mav.addObject("buildingList", responseList);
        mav.addObject("staffList", userServiceImpl.getStaffs());
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
        mav.addObject("buildingEdit", buildingDTO); // // trả về 1 cái rỗng thôi vì đây là add not update, tại vì bên FE đang có 1 cái modelAttribute="buildingEdit" vì vậy bắt buộc phải trả về cho nó 1 cái model, model này dùng cho việc update building
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
