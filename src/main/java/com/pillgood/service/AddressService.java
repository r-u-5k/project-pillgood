package com.pillgood.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.AddressDto;
import com.pillgood.entity.Address;
import com.pillgood.entity.User;
import com.pillgood.exception.UserNotFoundException;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.AddressRepository;
import com.pillgood.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AddressService {

	private final AddressRepository addressRepository;
	private final UserRepository userRepository;

	public AddressDto createAddress(AddressDto addressDto) {
		Address address = toAddressEntity(addressDto);
		System.out.println(">>>>>>>>>>>>>>>>>>asdajsldakdj"+address);
		Address savedAddress = addressRepository.save(address);
		return ToDtoMapper.toAddressDto(savedAddress);
	}

	public AddressDto updateAddress(AddressDto addressDto) {
		Address address = addressRepository.findById(addressDto.getAddressNo())
				.orElseThrow(() -> new RuntimeException("배송지를 찾을 수 없습니다"));
		address.setName(addressDto.getName());
		address.setPhone(addressDto.getPhone());
		address.setZipcode(addressDto.getZipcode());
		address.setAddress(addressDto.getAddress());
		address.setAddressDetail(addressDto.getAddressDetail());
		address.setRequest(addressDto.getRequest());
		address.setDefaultAddress(addressDto.getDefaultAddress());

		Address updatedAddress = addressRepository.save(address);
		return ToDtoMapper.toAddressDto(updatedAddress);
	}

	public void deleteAddress(Long addressNo) {
		addressRepository.deleteById(addressNo);
	}

	public List<AddressDto> getAllAddresses() {
		List<Address> addresses = addressRepository.findAll();
		return addresses.stream().map(ToDtoMapper::toAddressDto).collect(Collectors.toList());
	}

	public AddressDto getAddressById(Long addressNo) {
		Address address = addressRepository.findById(addressNo)
				.orElseThrow(() -> new RuntimeException("해당 주소를 찾을 수 없습니다."));
		return ToDtoMapper.toAddressDto(address);
	}

	private Address toAddressEntity(AddressDto addressDto) {
		Address address = new Address();
		User user = userRepository.findById(addressDto.getUserId())
				.orElseThrow(() -> new UserNotFoundException("유저정보가 없습니다"));
		address.setName(addressDto.getName());
		address.setPhone(addressDto.getPhone());
		address.setZipcode(addressDto.getZipcode());
		address.setAddress(addressDto.getAddress());
		address.setAddressDetail(addressDto.getAddressDetail());
		address.setRequest(addressDto.getRequest());
		address.setDefaultAddress(addressDto.getDefaultAddress());
		address.setUser(user);
		return address;
	}

	public List<AddressDto> getAddressByUserId(Long userId) {
		List<Address> address = addressRepository.findByUserId(userId);
		return address.stream().map(ToDtoMapper::toAddressDto).collect(Collectors.toList());
	}

}
