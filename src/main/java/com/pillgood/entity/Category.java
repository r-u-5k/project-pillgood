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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_no_seq")
    @SequenceGenerator(name = "category_no_seq", sequenceName = "category_no_seq", allocationSize = 1,initialValue = 1)
    private Long no;
    @Enumerated(EnumType.STRING)
    private Symptom symptom;
    @Enumerated(EnumType.STRING)
    private Type type;
    
    public enum Symptom{
    	혈관,
    	장,
    	피부,
    	눈,
    	두뇌,
    	피로,
    	관절,
    	면역력,
    	모발,
    	여성건강,
    	남성건강,
    	마음건강,
    	구강관리
    }
    
    public enum Type{
    	건강기능식품,
    	건강식품,
    	건강용품
    }
}

