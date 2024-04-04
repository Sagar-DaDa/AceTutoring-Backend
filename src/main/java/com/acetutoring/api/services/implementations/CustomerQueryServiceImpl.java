package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.CustomerQueryDto;
import com.acetutoring.api.entities.CustomerQuery;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.CustomerQueryMapper;
import com.acetutoring.api.repositories.CustomerQueryRepo;
import com.acetutoring.api.services.CustomerQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {
    private CustomerQueryRepo customerQueryRepo;

    @Override
    public CustomerQueryDto createCustomerQuery(CustomerQueryDto customerQueryDto) {
        return CustomerQueryMapper.mapToCustomerQueryDto(
                customerQueryRepo.save(CustomerQueryMapper.mapToCustomerQuery(customerQueryDto))
        );
    }

    @Override
    public CustomerQueryDto getCustomerQueryById(Long customerQueryId) {
        return CustomerQueryMapper.mapToCustomerQueryDto(
                customerQueryRepo.findById(customerQueryId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Customer query not found. Invalid customer query ID: " + customerQueryId
                                )
                        )
        );
    }

    @Override
    public List<CustomerQueryDto> getAllCustomerQueries() {
        return customerQueryRepo
                .findAll()
                .stream()
                .map(CustomerQueryMapper::mapToCustomerQueryDto)
                .toList();
    }

    @Override
    public void deleteCustomerQueryById(Long customerQueryId) {
        CustomerQuery foundCustomerQuery = customerQueryRepo.findById(customerQueryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Customer query not found. Invalid customer query ID: " + customerQueryId
                        )
                );
        customerQueryRepo.delete(foundCustomerQuery);
    }
}
