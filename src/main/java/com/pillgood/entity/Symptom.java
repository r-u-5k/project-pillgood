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
@Table(name = "symptom")
public class Symptom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "symptom_no_SEQ")
	@SequenceGenerator(name = "symptom_no_SEQ", sequenceName = "symptom_no_SEQ", allocationSize = 1)
	@Column(name = "symptom_no")
	private Long symptomNo;
	
	private String symptomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_no")
	private Survey survey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_no")
	private Item item;

}
