package com.pillgood.entity;
import java.time.LocalDateTime;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Override
	public String toString() {
		return "Review [no=" + no + ", title=" + title + ", content=" + content + ", star=" + star + ", reviewDate="
				+ reviewDate +", user="+user+ "]";
	}

	@Id
    @Column(name = "review_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_no_SEQ")
    @SequenceGenerator(name = "review_no_SEQ", sequenceName = "review_no_SEQ", allocationSize = 1)
    private Long no;
    private String title;
    private String content;
    private Long star;
    //private Long reviewType;
    private Date reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    @ToString.Exclude
    private Item item;

    // Getters and setters
}
