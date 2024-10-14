package com.pillgood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.Category;
import com.pillgood.entity.Item;



public interface ItemRepository extends JpaRepository<Item	, Long>{
	
	List<Item> findByNameContainingIgnoreCase(String name);
	
	List<Item> findByBrand(String brand);
	
	List<Item> findAllByOrderByNameDesc();
	
	List<Item> findByCategory_Type(Category.Type categoryType);
	
	List<Item> findByCategory_Symptom(Category.Symptom categorySymptom);
	
	Item findByNo(Long itemNo);

}
