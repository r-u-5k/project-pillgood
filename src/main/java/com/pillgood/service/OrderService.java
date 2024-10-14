package com.pillgood.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.OrderItemDto;
import com.pillgood.dto.OrdersDto;
import com.pillgood.entity.Address;
import com.pillgood.entity.Cart;
import com.pillgood.entity.Item;
import com.pillgood.entity.OrderItem;
import com.pillgood.entity.Orders;
import com.pillgood.entity.User;
import com.pillgood.exception.UserNotFoundException;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.AddressRepository;
import com.pillgood.repository.CartRepository;
import com.pillgood.repository.ItemRepository;
import com.pillgood.repository.OrdersRepository;
import com.pillgood.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

	private final OrdersRepository ordersRepository;
	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	private final ItemRepository itemRepository;
	private final AddressRepository addressRepository;

	public OrdersDto createOrder(OrdersDto orderDto) {

		if (orderDto.getOrderItems() == null || orderDto.getOrderItems().isEmpty()) {
			throw new RuntimeException("Order items are empty");
		}
		//주문정보로 유저뽑기
		User user = userRepository.findById(orderDto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		//주문생성
		Orders order = new Orders();

		//주문정보에서 오더아이템 뽑기
		OrderItemDto orderItemDto = orderDto.getOrderItems().get(0);
		//아이템번호로 데이터베이스에서 아이템 뽑기 
		Item item = itemRepository.findById(orderItemDto.getItem().getNo())
				.orElseThrow(() -> new RuntimeException("Item not found"));
		//오더아이템 생성
		OrderItem orderItem = new OrderItem();
		//아이템 pk세팅
		orderItem.setItem(item);
		//수량세팅
		orderItem.setOrderItemQty(orderItemDto.getOrderItemQty());
		//주문 pk세팅
		orderItem.setOrders(order);
		//레파지토리에 오더아이템 넣기위한 작업
		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add(orderItem);

		order.setUser(user);
		order.setOrderItems(orderItems);
		order.setOrdersAddress(orderDto.getOrdersAddress());
		order.setOrdersAddressDetail(orderDto.getOrdersAddressDetail());
		order.setOrdersPhone(orderDto.getOrdersPhone());
		order.setOrdersPrice(orderDto.getOrdersPrice());
		order.setOrdersName(orderDto.getOrdersName());
		order.setOrdersZipcode(orderDto.getOrdersZipcode());
		
		Orders savedOrder = ordersRepository.save(order);
		//주소 데이터베이스에 설정
		System.out.println(">>>>>>>>>>>>>>"+savedOrder);
		Address address = new Address();
		address.setAddress(savedOrder.getOrdersAddress());
		address.setAddressDetail(savedOrder.getOrdersAddressDetail());
		address.setName(savedOrder.getOrdersName());
		address.setPhone(savedOrder.getOrdersPhone());
		address.setUser(user);
		address.setZipcode(savedOrder.getOrdersZipcode());
		addressRepository.save(address);

		return ToDtoMapper.toOrdersDto(savedOrder);
	}

	public OrdersDto createOrderFromCart(OrdersDto ordersDto) {
		User user = userRepository.findById(ordersDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<Cart> carts = cartRepository.findByCartNoIn(ordersDto.getCartItems());
		/*List<Cart> carts = cartRepository.findByUser(user);*/
	
		Orders order = new Orders();
		order.setUser(user);
		List<OrderItem> orderItems = new ArrayList<>();
		for (Cart cart : carts) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItem(cart.getItem());
			orderItem.setOrderItemQty(cart.getCartQty());
			orderItem.setOrders(order);
			orderItems.add(orderItem);
		}
		order.setOrdersAddress(ordersDto.getOrdersAddress());
		order.setOrdersAddressDetail(ordersDto.getOrdersAddressDetail());
		order.setOrdersPhone(ordersDto.getOrdersPhone());
		order.setOrdersPrice(ordersDto.getOrdersPrice());
		order.setOrdersZipcode(ordersDto.getOrdersZipcode());
		order.setOrdersName(ordersDto.getOrdersName());
		order.setOrderItems(orderItems);
		Orders savedOrder = ordersRepository.save(order);
		Address address = new Address();
		address.setAddress(savedOrder.getOrdersAddress());
		address.setAddressDetail(savedOrder.getOrdersAddressDetail());
		address.setName(savedOrder.getOrdersName());
		address.setPhone(savedOrder.getOrdersPhone());
		address.setUser(user);
		address.setZipcode(savedOrder.getOrdersZipcode());
		addressRepository.save(address);

		// 장바구니 비우기
		cartRepository.deleteAll(carts);
		System.out.println("orderDto = "+ordersDto);
		System.out.println("order = "+order);
		return ToDtoMapper.toOrdersDto(savedOrder);
	}

	
	//주문전체조회
	public List<OrdersDto> orderDtoList(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<Orders> ordersList = user.getOrders();
		// Orders 엔티티를 OrderDto로 변환하여 반환
		return ordersList.stream().map(ToDtoMapper::toOrdersDto) // 메소드 참조를 사용하여 Orders를 OrdersDto로 변환
				.collect(Collectors.toList());
	}

	//주문한개 상세내역조회

	public OrdersDto getOrderDetail(Long orderId) {
		Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		return ToDtoMapper.toOrdersDto(order);
	}

	//주문내역 삭제
	public void deleteOrder(Long orderId) {
		ordersRepository.deleteById(orderId);
	}
	
	
	


}
