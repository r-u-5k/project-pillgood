package com.pillgood.entity;
import org.apache.commons.lang3.builder.ToStringExclude;

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
import lombok.Builder.Default;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_no_SEQ")
    @SequenceGenerator(name = "address_no_SEQ", sequenceName = "address_no_SEQ", allocationSize = 1)
    private Long addressNo;
    private String name;
    private String phone;
    private String zipcode;
    private String address;
    private String addressDetail;
    private String request;
    
    @Default
    private Boolean defaultAddress=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
