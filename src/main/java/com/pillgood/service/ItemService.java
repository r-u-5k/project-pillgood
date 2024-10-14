package com.pillgood.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pillgood.dto.ItemDto;
import com.pillgood.dto.ItemImageDto;
import com.pillgood.entity.Category;
import com.pillgood.entity.Item;
import com.pillgood.entity.ItemImage;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.CategoryRepository;
import com.pillgood.repository.ItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {

	private final CategoryRepository categortRepository;
	private final ModelMapper modelMapper;
	private final ItemRepository itemRepository;

	// 아이템 전체 조회
	public List<ItemDto> getAllItems() {
		List<Item> itemList = itemRepository.findAllByOrderByNameDesc();
		System.out.println(itemList);
		List<ItemDto> itemListDto = itemList.stream()
				.map(ToDtoMapper::toItemDto)
				.collect(Collectors.toList());
		
		return itemListDto;
	}
	
	// 필리 브랜드 아이템 조회
	public List<ItemDto> getitemsByPilly() {
		List<Item> itemList = itemRepository.findByBrand("필리");
		System.out.println(itemList);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		List<ItemDto> itemListDto = itemList.stream()
				.map(ToDtoMapper::toItemDto)
				.collect(Collectors.toList());
		
		return itemListDto;
	}

	// 아이템 검색 결과 리스트 조회
	public List<ItemDto> searchItems(String itemName) {
		List<Item> searchItems = itemRepository.findByNameContainingIgnoreCase(itemName);
		List<ItemDto> searchItemsDto = searchItems.stream()
	            .map(ToDtoMapper::toItemDto)  
	            .collect(Collectors.toList());
		return searchItemsDto;
	}

	// 타입카테고리별 아이템 리스트 조회
	public List<ItemDto> findItemsByCategoryType(Category.Type categoryType) throws Exception {
		//Category category = categortRepository.findById(categoryId)
		//		.orElseThrow(() -> new CartNotFoundException("카테고리없음"));
		List<Item> categoryByItemList = itemRepository.findByCategory_Type(categoryType);
		System.out.println(categoryByItemList);
		List<ItemDto> categoryByItemDtoList = categoryByItemList.stream()
	            .map(ToDtoMapper::toItemDto)  
	            .collect(Collectors.toList());
		System.out.println(categoryByItemDtoList);
		return categoryByItemDtoList;
	}
	
	// 심톰카테고리별 아이템 리스트 조회
	public List<ItemDto> findItemsByCategorySymptom(Category.Symptom categorySymptom) throws Exception {
		//Category category = categortRepository.findById(categoryId)
		//		.orElseThrow(() -> new CartNotFoundException("카테고리없음"));
		List<Item> categoryByItemList = itemRepository.findByCategory_Symptom(categorySymptom);
		System.out.println(categoryByItemList);
		List<ItemDto> categoryByItemDtoList = categoryByItemList.stream()
				.map(ToDtoMapper::toItemDto)  
				.collect(Collectors.toList());
		System.out.println(categoryByItemDtoList);
		return categoryByItemDtoList;
	}
	
	// 아이템 상세 조회
	public ItemDto findItem(Long itemNo) throws Exception {
		//Category category = categortRepository.findById(categoryId)
		//		.orElseThrow(() -> new CartNotFoundException("카테고리없음"));
		Item item = itemRepository.findByNo(itemNo);
		System.out.println();
		ItemDto itemDto = ToDtoMapper.toItemDto(item);
		return itemDto;
	}

	
	
}
