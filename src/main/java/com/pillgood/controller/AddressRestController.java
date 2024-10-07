package com.pillgood.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.AddressDto;
import com.pillgood.service.AddressService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressRestController {

    private final AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDto createdAddress = addressService.createAddress(addressDto);
        return ResponseEntity.ok(createdAddress);
    }

    @PutMapping("/update")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto) {
        AddressDto updatedAddress = addressService.updateAddress(addressDto);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/delete/{addressNo}")
    public ResponseEntity<String> deleteAddress(@PathVariable(name = "addressNo") Long addressNo) {
        addressService.deleteAddress(addressNo);
        return ResponseEntity.ok("삭제성공");
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{addressNo}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable(name = "addressNo") Long addressNo) {
        AddressDto address = addressService.getAddressById(addressNo);
        return ResponseEntity.ok(address);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressDto>> getAddressByUserId(@PathVariable(name = "userId") Long userId) {
        List<AddressDto> address = addressService.getAddressByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }
}