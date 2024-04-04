package com.acetutoring.api.services;

import com.acetutoring.api.dto.CustomerQueryDto;

import java.util.List;

public interface CustomerQueryService {
    CustomerQueryDto createCustomerQuery(CustomerQueryDto customerQueryDto);

    CustomerQueryDto getCustomerQueryById(Long customerQueryId);

    List<CustomerQueryDto> getAllCustomerQueries();

    void deleteCustomerQueryById(Long customerQueryId);
}
