package com.caramelwaffle.accounts.service;

import com.caramelwaffle.accounts.model.CustomerData;

public interface IAccountService {

    /**
     *
     * @param customerData - CustomerDto Object
     */
    void createAccount(CustomerData customerData);

    CustomerData fetchAccountDetail(String mobileNumber);

    boolean updateAccount(CustomerData customerData);

    boolean deleteAccount(String mobileNumber);
}
