package com.caramelwaffle.accounts.mapper;

import com.caramelwaffle.accounts.model.CustomerData;
import com.caramelwaffle.accounts.entity.CustomerEntity;

public class CustomerMapper {
    public static CustomerData mapToCustomerData(CustomerEntity customerEntity, CustomerData customerData) {
        customerData.setName(customerEntity.getName());
        customerData.setEmail(customerEntity.getEmail());
        customerData.setMobileNumber(customerEntity.getMobileNumber());
        return customerData;
    }

    public static CustomerEntity mapToCustomerEntity(CustomerData customerData, CustomerEntity customerEntity) {
        customerEntity.setName(customerData.getName());
        customerEntity.setEmail(customerData.getEmail());
        customerEntity.setMobileNumber(customerData.getMobileNumber());
        return customerEntity;
    }
}
