package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.CustomerQueryDto;
import com.acetutoring.api.services.CustomerQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/customerQueries")
public class CustomerQueryController {
    private CustomerQueryService customerQueryService;

    @GetMapping
    public ResponseEntity<List<CustomerQueryDto>> getAllCustomerQueries(){
        return ResponseEntity.ok(customerQueryService.getAllCustomerQueries());
    }

    @GetMapping("{customerQueryId}")
    public ResponseEntity<CustomerQueryDto> getCustomerQueryById(
            @PathVariable Long customerQueryId){
        return ResponseEntity.ok(customerQueryService.getCustomerQueryById(customerQueryId));
    }

    @DeleteMapping("/deleteCustomerQuery/{customerQueryId}")
    public String deleteCustomerQueryById(@PathVariable Long customerQueryId){
        customerQueryService.deleteCustomerQueryById(customerQueryId);
        return "Customer query deleted successfully.";
    }
}
