package com.pillgood.entity;
import java.util.ArrayList;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "orders_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_no_SEQ")
    @SequenceGenerator(name = "orders_no_SEQ", sequenceName = "orders_no_SEQ", allocationSize = 1)
    private Long ordersNo;
    
    private String ordersName;
    private String ordersPhone;//
    @Column(name ="orders_zipcode")
    private String ordersZipcode;//
    private String ordersAddress;//
    private String ordersAddressDetail;//
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date ordersDate;
    private Long ordersPrice;//
    private String ordersImg;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_no")
    private Payment payment;

    @Default
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems= new ArrayList<>();//

    // 생성시 자동으로 현재 날짜와 시간을 설정하기 위한 메서드
    @PrePersist
    protected void onCreate() {
    	ordersDate = new Date();
    }

}
