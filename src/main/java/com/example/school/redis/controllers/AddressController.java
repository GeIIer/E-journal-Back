package com.example.school.redis.controllers;

import com.example.school.redis.data.Address;
import com.example.school.redis.services.AddressService;
import com.example.school.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/redis/address")
@Tag(name = "Контроллер для Address Redis")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    public List<Address> getAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable String id) {
        return addressService.findById(id);
    }

    @PostMapping()
    public Address createAddress(@RequestBody Address Address) {
        return addressService.save(Address);
    }

    @PutMapping()
    public Address updateAddress(@RequestBody Address Address) {
        return addressService.update(Address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUtil> deleteAddress(@PathVariable String id) {
        return addressService.deleteById(id);
    }
}
