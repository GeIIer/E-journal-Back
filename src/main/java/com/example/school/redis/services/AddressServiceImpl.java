package com.example.school.redis.services;

import com.example.school.redis.data.Address;
import com.example.school.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service
public class AddressServiceImpl implements AddressService {
    private final Jedis jedis;

    public AddressServiceImpl() {
        this.jedis = new Jedis();
    }

    @Override
    @Transactional
    public Address save(Address address) {
        try {
            if (address.getStreet() == null || address.getCity() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Street and city must be not null");
            }
            address.setId(UUID.randomUUID().toString());
            jedis.set(Address.class.getSimpleName()+":"+address.getId()+":city", address.getCity());
            jedis.set(Address.class.getSimpleName()+":"+address.getId()+":street", address.getStreet());
            return address;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    public Address findById(String id) {
        Address address = new Address();
        String city = jedis.get(Address.class.getSimpleName()+":"+id+":city");
        String street = jedis.get(Address.class.getSimpleName()+":"+id+":street");
        if (city == null || street == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        address.setCity(city);
        address.setStreet(street);
        address.setId(id);
        return address;
    }

    @Override
    public List<Address> findAll() {
        Set<String> keys = jedis.keys("Address:*");
        List<Address> result = new ArrayList<>();
        Set<String> dataKeys = new HashSet<>();
        for (String key : keys) {
            String[] data = key.split(":");
            String id = data[1];
            if (!dataKeys.contains(id)) {
                dataKeys.add(id);
                String city = jedis.get(Address.class.getSimpleName()+":"+id+":city");
                String street = jedis.get(Address.class.getSimpleName()+":"+id+":street");
                result.add(new Address(id, city, street));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public Address update(Address address) {
        try {
            if (address.getId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be not null");
            }
            if (address.getStreet() == null || address.getCity() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Street and city must be not null");
            }
            address.setId(address.getId());
            jedis.set(Address.class.getSimpleName()+":"+address.getId()+":city", address.getCity());
            jedis.set(Address.class.getSimpleName()+":"+address.getId()+":street", address.getStreet());
            return address;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<ResponseUtil> deleteById(String id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be not null");
        }
        long count = jedis.del(Address.class.getSimpleName()+":"+id+":city",
                Address.class.getSimpleName()+":"+id+":street");
        if (count > 0) {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Address deleted"));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
