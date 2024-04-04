package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.CustomerQueryDto;
import com.acetutoring.api.entities.CustomerQuery;

public class CustomerQueryMapper {
    public static CustomerQueryDto mapToCustomerQueryDto(CustomerQuery customerQuery){
        return new CustomerQueryDto(
                customerQuery.getId(),
                customerQuery.getFullName(),
                customerQuery.getEmail(),
                customerQuery.getMobile(),
                customerQuery.getMessage(),
                customerQuery.getCreatedAt()
        );
    }

    public static CustomerQuery mapToCustomerQuery(CustomerQueryDto customerQueryDto){
        return new CustomerQuery(
                customerQueryDto.getId(),
                customerQueryDto.getFullName(),
                customerQueryDto.getEmail(),
                customerQueryDto.getMobile(),
                customerQueryDto.getMessage(),
                customerQueryDto.getCreatedAt()
        );
    }
}
