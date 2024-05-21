package com.example.school.redis.services;

import com.example.school.redis.data.Address;
import com.example.school.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {
    Address save(Address address);
    Address findById(String id);
    List<Address> findAll();
    Address update(Address address);
    ResponseEntity<ResponseUtil> deleteById(String id);
}
