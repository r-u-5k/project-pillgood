package com.pillgood.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Entity
@AllArgsConstructor
@Builder
@Table(name = "item_image")
public class ItemImage {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_image_no_seq")
    @SequenceGenerator(name = "item_image_no_seq", sequenceName = "item_image_no_seq", allocationSize = 1,initialValue = 1)
    private Long no;
    private String img;
    private String url;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "item_no")
    @ToString.Exclude
    private Item item;
    
    public enum Type{
    	상품,
    	상품설명
    }
    
}