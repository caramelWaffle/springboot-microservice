package com.caramelwaffle.accounts.service.impl;

import com.caramelwaffle.accounts.constants.AccountsConstants;
import com.caramelwaffle.accounts.exception.ResourceNotFoundException;
import com.caramelwaffle.accounts.mapper.AccountMapper;
import com.caramelwaffle.accounts.model.AccountData;
import com.caramelwaffle.accounts.model.CustomerData;
import com.caramelwaffle.accounts.entity.AccountEntity;
import com.caramelwaffle.accounts.entity.CustomerEntity;
import com.caramelwaffle.accounts.exception.CustomerAlreadyExistException;
import com.caramelwaffle.accounts.mapper.CustomerMapper;
import com.caramelwaffle.accounts.repository.AccountRepository;
import com.caramelwaffle.accounts.repository.CustomerRepository;
import com.caramelwaffle.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerData customerData) {
        Optional<CustomerEntity> existedMobileNumberCustomer =
                customerRepository.findByMobileNumber(customerData.getMobileNumber());
        if (existedMobileNumberCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already registered with given mobileNumber " +
                    existedMobileNumberCustomer.get().getMobileNumber());
        }
        CustomerEntity customerEntity = CustomerMapper.mapToCustomerEntity(customerData, new CustomerEntity());
        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
        accountRepository.save(createNewAccount(savedCustomerEntity));
    }

    /**
     * @param customerEntity - Customer Object
     * @return the new account details
     */
    private AccountEntity createNewAccount(CustomerEntity customerEntity) {
        AccountEntity newAccountEntity = new AccountEntity();
        newAccountEntity.setCustomerId(customerEntity.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccountEntity.setAccountNumber(randomAccNumber);
        newAccountEntity.setAccountType(AccountsConstants.SAVINGS);
        newAccountEntity.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccountEntity;
    }

    @Override
    public CustomerData fetchAccountDetail(String mobileNumber) {
        CustomerEntity customerEntity = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "phoneNumber", mobileNumber)
        );
        AccountEntity accountEntity = accountRepository.findByCustomerId(customerEntity.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customerEntity.getCustomerId().toString())
        );
        CustomerData customerData = CustomerMapper.mapToCustomerData(customerEntity, new CustomerData());
        customerData.setAccountData(AccountMapper.mapToAccountsData(accountEntity, new AccountData()));
        return customerData;
    }

    @Override
    public boolean updateAccount(CustomerData customerData) {
        boolean isUpdated = false;
        AccountData accountData = customerData.getAccountData();
        if (accountData != null) {
            AccountEntity accountEntity = accountRepository.findById(accountData.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountData.getAccountNumber().toString())
            );
            AccountMapper.mapToAccounts(accountData, accountEntity);
            accountEntity = accountRepository.save(accountEntity);

            Long customerId = accountEntity.getCustomerId();
            CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomerEntity(customerData, customerEntity);
            customerRepository.save(customerEntity);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        CustomerEntity customerEntity = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "phoneNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customerEntity.getCustomerId());
        customerRepository.delete(customerEntity);
        return true;
    }
}