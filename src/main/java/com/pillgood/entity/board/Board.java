package com.pillgood.entity.board;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "board")
public class Board {
    @Id
    @Column(name = "board_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_board_no_SEQ")
    @SequenceGenerator(name = "board_board_no_SEQ", sequenceName = "board_board_no_SEQ", allocationSize = 1)
    private Long boardNo;
    private String boardTitle;
    @Column(length = 1000)
    private String boardContent;
    
    @Enumerated(EnumType.STRING)
    private BoardType boardType;
    
    @Enumerated(EnumType.STRING)
    private BoardMenu boardMenu;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date boardDate;
    
    @PrePersist
    protected void onCreate() {
    	boardDate = new Date();
    }


}
