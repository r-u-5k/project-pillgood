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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Subscription {
	@Id
	@Column(name = "subscription_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_id_SEQ")
	@SequenceGenerator(name = "subscription_id_SEQ", sequenceName = "subscription_id_SEQ", allocationSize = 1)
	private Long id;
	private Long price;
	private Long discount;
	private Long status;
	private Date subscriptionCreatedat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
