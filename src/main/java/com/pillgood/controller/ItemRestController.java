package com.pillgood.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.ItemDto;
import com.pillgood.entity.Category;
import com.pillgood.entity.Item;
import com.pillgood.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ItemRestController {

    private final ItemService itemService;
    
    // 아이템 전체 조회
    @GetMapping("/api/items/all")
    public ResponseEntity<List<ItemDto>> getAllItems() {
    	List<ItemDto> items = itemService.getAllItems();
    	System.out.println(items);
    	return ResponseEntity.ok(items);
    }

    // 필리브랜드 아이템 조회
    @GetMapping("/api/items/home")
    public ResponseEntity<List<ItemDto>> getItemsByPilly() {
        List<ItemDto> items = itemService.getitemsByPilly();
        System.out.println(items);
        return ResponseEntity.ok(items);
    }

    // 타입카테고리별 아이템 조회
    @GetMapping("/api/items/category/type/{categoryType}")
    public ResponseEntity<List<ItemDto>> findItemsByCategoryType(@PathVariable(name = "categoryType") Category.Type categoryType) throws Exception {
        List<ItemDto> items = itemService.findItemsByCategoryType(categoryType);
        return ResponseEntity.ok(items);
    }
    
    // 심톰카테고리별 아이템 조회
    @GetMapping("/api/items/category/symptom/{categorySymptom}")
    public ResponseEntity<List<ItemDto>> findItemsByCategorySysmptom(@PathVariable(name = "categorySymptom") Category.Symptom categorySymptom) throws Exception {
    	List<ItemDto> items = itemService.findItemsByCategorySymptom(categorySymptom);
    	return ResponseEntity.ok(items);
    }
    
    // 아이템  상세 조회
    @GetMapping("/api/item/{itemNo}")
    public ResponseEntity<ItemDto> findItem(@PathVariable(name = "itemNo") Long itemNo) throws Exception {
    	ItemDto item = itemService.findItem(itemNo);
    	return ResponseEntity.ok(item);
    }
}
