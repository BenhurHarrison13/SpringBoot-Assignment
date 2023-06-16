package com.springboot.academicmanagemt.repository;

import com.springboot.academicmanagemt.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
