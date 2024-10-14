package com.pillgood.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = 1 WHERE user_id = ?")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_SEQ")
	@SequenceGenerator(name = "users_id_SEQ", sequenceName = "users_id_SEQ", allocationSize = 1)
	private Long id;
	@Column(unique = true)
	private String email;
	private String password;
	private String name;
	private Date birthday;
	private String gender;
	private String phone;
	@Default
	private String provider = "HOME";
	private String role;
	private String token;
	@Default
	private Boolean deleted = false;
	@Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Address> addresses = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Cart> carts = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Coupon> coupons = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Subscription> subscriptions = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Survey> surveys = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Orders> orders = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ChatRoom> chatRooms = new ArrayList<>();
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", birthday="
				+ birthday + ", gender=" + gender + ", phone=" + phone + ", provider=" + provider + ", role=" + role
				+ ", token=" + token + ", deleted=" + deleted + "]";
	}
}
