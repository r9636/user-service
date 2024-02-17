package com.user.service.repo;

import com.user.service.entity.Address;
import com.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, String> {
    Optional<Address> findByAddressId(String addressId);
}
