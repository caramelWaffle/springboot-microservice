package com.caramelwaffle.loan.repository;

import com.caramelwaffle.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    Optional<LoanEntity> findByMobileNumber(String mobileNumber);
}
