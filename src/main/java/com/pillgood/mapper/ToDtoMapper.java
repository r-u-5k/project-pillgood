package com.pillgood.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.pillgood.dto.AdminDto;
import com.pillgood.dto.BoardDto;
import com.pillgood.dto.AddressDto;
import com.pillgood.dto.CartDto;
import com.pillgood.dto.ItemDto;
import com.pillgood.dto.ItemImageDto;
import com.pillgood.dto.OrderItemDto;
import com.pillgood.dto.OrdersDto;
import com.pillgood.dto.ReviewDto;
import com.pillgood.dto.UserDto;
import com.pillgood.entity.Address;
import com.pillgood.entity.Cart;
import com.pillgood.entity.Item;
import com.pillgood.entity.ItemImage;
import com.pillgood.entity.OrderItem;
import com.pillgood.entity.Orders;
import com.pillgood.entity.Review;
import com.pillgood.entity.User;
import com.pillgood.entity.board.Board;

public class ToDtoMapper {

	public static UserDto toUserDto(User user) {
		return UserDto.builder()
				.email(user.getEmail())
				.gender(user.getGender())
				.phone(user.getPhone())
				.password(user.getPassword())
				.name(user.getName())
				.role(user.getRole())
				.token(user.getToken())
				.provider(user.getProvider())
				.id(user.getId())
				.build();
	}
	
	public static CartDto toCartDto(Cart cart) {
		return CartDto.builder().
				cartId(cart.getCartNo())
				.cartQty(cart.getCartQty())
				.itemId(cart.getItem().getNo())
				.userId(cart.getUser().getId())
				.itemDto(ToDtoMapper.toItemDto(cart.getItem()))
				.build();
	}

	public static OrdersDto toOrdersDto(Orders orders) {
		return OrdersDto.builder().orderId(orders.getOrdersNo()).ordersPhone(orders.getOrdersPhone())
				.ordersZipcode(orders.getOrdersZipcode()).ordersAddress(orders.getOrdersAddress())
				.ordersAddressDetail(orders.getOrdersAddressDetail()).ordersPrice(orders.getOrdersPrice())
				.userId(orders.getUser().getId()).orderDate(orders.getOrdersDate())
				.orderItems(toOrderItemDtoList(orders.getOrderItems())) // OrderItem 리스트를 OrderItemDto 리스트로 변환
				.build();
	}

	public static OrderItemDto toOrderItemDto(OrderItem orderItem) {
		return OrderItemDto.builder().orderItemNo(orderItem.getOrderItemNo()).orderItemQty(orderItem.getOrderItemQty())
				.item(toItemDto(orderItem.getItem())).orderId(orderItem.getOrders().getOrdersNo()).build();
	}

	public static List<OrderItemDto> toOrderItemDtoList(List<OrderItem> orderItems) {
		return orderItems.stream().map(ToDtoMapper::toOrderItemDto).collect(Collectors.toList());
	}

	public static ItemDto toItemDto(Item item) {
		return ItemDto.builder().no(item.getNo())
				.brand(item.getBrand())
				.description(item.getDescription())
				.name(item.getName())
				.price(item.getPrice())
				.categorySymptom(item.getCategory().getSymptom())
				.categoryType(item.getCategory().getType())
				.itemImageDto(item.getItemImageList()
						.stream()
						.map(ToDtoMapper::toItemImageDto)
						.collect(Collectors.toList()))
				.reviewQty(Long.valueOf(item.getReviewList().size()))
				 .reviewAvg((item.getReviewList().stream()
		                    .mapToLong(Review::getStar)
		                    .average()
		                    .orElse(0.0))) 
				.build();
	}


	public static ItemImageDto toItemImageDto(ItemImage itemImage) {
		return ItemImageDto.builder()
				.img(itemImage.getImg())
				.url(itemImage.getUrl())
				.type(itemImage.getType())
				.build();
	}
	
	public static ReviewDto toReviewDto(Review review) {
		return ReviewDto.builder()
				.no(review.getNo())
				.name(review.getUser().getName())
				.content(review.getContent())
				.title(review.getTitle())
				.rating(review.getStar())
				.birthday(review.getUser().getBirthday())
				.itemDto(ToDtoMapper.toItemDto(review.getItem()))
				.date(review.getReviewDate())
				.gender(review.getUser().getGender())
				.build();
	}
	

	public static List<ItemImageDto> toItemImgDtoList(List<ItemImage> itemImgs) {
		return itemImgs.stream().map(ToDtoMapper::toItemImageDto).collect(Collectors.toList());
	}
	
	public static BoardDto toBoardDto (Board board) {
		
		return BoardDto.builder()
				.boardNo(board.getBoardNo())
				.boardTitle(board.getBoardTitle())
				.boardContent(board.getBoardContent())
				.boardType(board.getBoardType())
				.boardMenu(board.getBoardMenu())
				.boardDate(board.getBoardDate())
				.build();
	}
	
	/*
public static AdminDto toAdminDto (Admin admin) {
		
		return AdminDto.builder()
				.adminNo(admin.getAdminNo())
				.adminId(admin.getAdminId())
				.adminPassword(admin.getAdminPassword())
				.adminRole(admin.getAdminRole())
				.build();
	}
*/
    public static AddressDto toAddressDto(Address address) {
        return AddressDto.builder()
                .addressNo(address.getAddressNo())
                .name(address.getName())
                .phone(address.getPhone())
                .zipcode(address.getZipcode())
                .address(address.getAddress())
                .addressDetail(address.getAddressDetail())
                .request(address.getRequest())
                .defaultAddress(address.getDefaultAddress())
                .userId(address.getUser().getId())
                .build();
    }
}
