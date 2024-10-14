package com.pillgood.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coupon")
public class Coupon {
	@Id
	@Column(name = "coupon_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_id_SEQ")
	@SequenceGenerator(name = "coupon_id_SEQ", sequenceName = "coupon_id_SEQ", allocationSize = 1)
	private Long id;
	private String name;
	private Long deductedPrice;
	private Date createdAt;
	private Date expiredDate;
	private Long validCondition;
	private Long status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	// Getters and setters
}