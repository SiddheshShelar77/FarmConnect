package com.farmconnect.repository;

import com.farmconnect.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
