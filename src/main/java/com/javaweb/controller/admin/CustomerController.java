package com.javaweb.controller.admin;


import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.StatusType;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.service.TransactionalService;
import com.javaweb.utils.CookieUtils;
import com.javaweb.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final IUserService userService;

    private final CookieUtils cookieUtils;

    private final TransactionalService transactionalService;

    @GetMapping(value = "/admin/customer-list")    // "/admin/building-list" là trang sẽ hiển thị cho người dùng thấy khi nhấp vào liên kết
    public ModelAndView buildingList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/customer_list"); // đây không phải là URL. Nó là tên view mà Spring Boot sẽ sử dụng để tìm tệp giao diện và hiển thị cho người dùng.


        Long userId = null;
        List<String> roles = SecurityUtils.getAuthorities();
        if(!roles.contains("ROLE_MANAGER")){
            userId =  cookieUtils.getUserId(request);
            customerSearchRequest.setStaffid(userId);
        }

        List<CustomerSearchResponse> responseList = customerService.findAll(customerSearchRequest);

        mav.addObject("modelSearch" , customerSearchRequest);
        mav.addObject("customerList" , responseList);
        mav.addObject("staffList", userService.getStaffs());
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView customerEdit(@PathVariable Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/customer_edit");
        CustomerEntity customerEntity = customerService.findById(id);
        mav.addObject("customerEdit" , customerEntity);
        mav.addObject("statusType", StatusType.statusTypes());
        mav.addObject("transactionType", TransactionType.transactonTypes());
        List<TransactionDTO> CSKH = transactionalService.findByCodeAndCustomerid("CSKH", id);
        List<TransactionDTO> DDX = transactionalService.findByCodeAndCustomerid("DDX", id);
        mav.addObject("CSKH", CSKH);
        mav.addObject("DDX", DDX);
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView addCustomer( HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/customer_edit");
        mav.addObject("customerEdit" , new CustomerEntity());   // trả về 1 cái rỗng thôi vì đây là add not update, tại vì bên FE đang có 1 cái modelAttribute="customerEdit" vì vậy bắt buộc phải trả về cho nó 1 cái model, model này dùng cho việc update customer
        mav.addObject("statusType", StatusType.statusTypes());
        return mav;
    }

}
