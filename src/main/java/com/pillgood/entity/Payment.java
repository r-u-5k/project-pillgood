package com.pillgood.entity;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "payment")
public class Payment {
	
    @Id
    @Column(name = "payment_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_no_SEQ")
    @SequenceGenerator(name = "payment_no_SEQ", sequenceName = "payment_no_SEQ", allocationSize = 1)
    private Long no;
    private String paymentKey;
    private Long orderNumber;
    private String orderName;
    private String method;
    private Long totalAmount;
    private String status;
    private String requestedAt;
    private String approvedAt;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Orders> orders;

}
