package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.converter.CustomerSearchResponseConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerSearchBuilderConverter customerSearchBuilderConverter;

    private final CustomerSearchResponseConverter customerSearchResponseConverter;

    private final BuildingRepository buildingRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public CustomerEntity insert(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
        return customerEntity;
    }


    @Transactional // Nếu CustomerEntity của bạn có các quan hệ (relationship) kiểu @OneToMany, @ManyToOne, hoặc @ManyToMany với lazy fetching (FetchType.LAZY), bạn cần @Transactional để đảm bảo dữ liệu liên quan được tải đầy đủ khi truy cập.
    @Override
    public List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest) {
        CustomerSearchBuilder customerSearchBuilder = customerSearchBuilderConverter.toCustomerSearchBuilder(customerSearchRequest);
        List<CustomerEntity> customerEntities = customerRepository.findAll(customerSearchBuilder);

        List<CustomerSearchResponse> customerSearchResponses = new ArrayList<>();
        for(CustomerEntity customerEntity : customerEntities) {
            customerSearchResponses.add(customerSearchResponseConverter.toCustomerSearchResponse(customerEntity));
        }

        return customerSearchResponses;
    }

    @Override
    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseDTO loadSfaffs(Long id) {

        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        List <UserEntity> staffs = customerEntity.getUsers();
        List <UserEntity> AllStaffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List <StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for(UserEntity staff : AllStaffs) {
            StaffResponseDTO staffResponseDTO  = new StaffResponseDTO();
            if(staffs.contains(staff)) {
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTO.setFullName(staff.getFullName());
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTOS.add(staffResponseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("Staff List");
        return responseDTO;
    }

    @Transactional
    @Override
    public void deleteCustomers(List<Long> ids) {
        customerRepository.deleteByIdIn(ids);
    }

    @Transactional
    @Override
    public void addOrUpdateCustomer(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }
}
