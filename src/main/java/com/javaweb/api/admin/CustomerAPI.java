package com.javaweb.api.admin;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentCustomerService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionalService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerAPI {


    private final CustomerService customerService;

    private final CustomerConverter customerConverter;

    private final AssignmentCustomerService assignmentCustomerService;


    @PostMapping(value="/contact")
    public ResponseEntity<?> createCustomers(@RequestBody CustomerDTO customerDTO) {
        try{
            CustomerEntity customerEntity = customerConverter.toCustomerEntity(customerDTO);

            return ResponseEntity.ok(customerService.insert(customerEntity));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO responseDTO = customerService.loadSfaffs(id);
        return responseDTO;
    }

    @PostMapping("/assignment")
    public ResponseEntity<?> assignCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        try{
            assignmentCustomerService.assignCustomer(assignmentCustomerDTO.getCustomerId(), assignmentCustomerDTO.getStaffs());
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "giao khách hành thành công"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteCustomer(@PathVariable List<Long> ids){
        if (ids.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\":\"No IDs provided\"}");
        }
        customerService.deleteCustomers(ids);
        return ResponseEntity.ok("{\"message\":\"success\"}");   // bên FE lỡ có dòng dataType:"JSON" rồi, tức là mong đợi kiểu JSON đợc gửi về client, nếu để void thì bên client sẽ bị lỗi vì nó không nhận được về kiểu JSON như mong đợi, cho nên là cứ thêm cái responseEntity cho nó êm
    }

    @PostMapping
    public ResponseEntity<?> addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO) {
        try{
            customerService.addOrUpdateCustomer(customerConverter.toCustomerEntity(customerDTO));
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "thêm/cập nhật thành công"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
