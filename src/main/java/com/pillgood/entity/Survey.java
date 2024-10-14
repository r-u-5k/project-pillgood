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
@Table(name = "survey")
public class Survey {
	@Id
	@Column(name = "survey_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "survey_no_SEQ")
	@SequenceGenerator(name = "survey_no_SEQ", sequenceName = "survey_no_SEQ", allocationSize = 1)
	private Long no;
	private String height;
	private String weight;
	private String symptomType;
	private String key;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
