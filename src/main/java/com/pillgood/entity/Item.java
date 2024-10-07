package com.pillgood.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_no_seq")
    @SequenceGenerator(name = "item_no_seq", sequenceName = "item_no_seq", allocationSize = 1,initialValue = 1)
    private Long no;
    private String brand;
    private String name;
    private Long price;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "")
    @ToString.Exclude
    private Category category;
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    @Builder.Default
    @OrderBy("img ASC")
    private List<ItemImage> itemImageList = new ArrayList<>();
	@Override
	public String toString() {
		return "Item [no=" + no + ", brand=" + brand + ", name=" + name + ", price=" + price + ", description="
				+ description + ", category=" + category + ", itemImageList="+itemImageList+", reviewList="+reviewList+"]\n";
	}
	
	@OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
	@Builder.Default
	private List<Review> reviewList = new ArrayList<>();
    
    
    
}