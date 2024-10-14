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
@Table(name = "order_item")
public class OrderItem {
    @Id
    @Column(name = "order_item_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_no_SEQ")
    @SequenceGenerator(name = "order_item_no_SEQ", sequenceName = "order_item_no_SEQ", allocationSize = 1)
    private Long orderItemNo;
    
    private Integer orderItemQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item item;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_no")
    private Orders orders;

    // Getters and setters
}
