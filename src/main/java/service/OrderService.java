package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import model.Cart;
import model.Order;
import model.status.OrderStatus;
import repository.CartRepository;
import repository.OrderRepository;
import repository.PaymentRepository;
import service.dtos.OrderDto;
import service.dtos.OrderItemDto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@Transactional
public class OrderService {
    @Inject
    OrderRepository orderRepository;
    @Inject
    PaymentRepository paymentRepository;
    @Inject
    CartRepository cartRepository;
    public List<OrderDto> findAll() {
        log.debug("Request to get all Orders");
        return this.orderRepository.findAll().stream().
                map(OrderService::mapToDto)
                .collect(Collectors.toList());
    }
    public OrderDto findById(Long id) {
        log.debug("Request to get Order : {}", id);
        return this.orderRepository.findById(id)
                .map(OrderService::mapToDto).orElse(null);
    }
    public List<OrderDto> findAllByUser(Long id) {
        return this.orderRepository.findByCartCustomerId(id)
                .stream().map(OrderService::mapToDto).collect(Collectors.
                        toList());
    }
    public OrderDto create(OrderDto orderDto) {
        log.debug("Request to create Order : {}", orderDto);
        Long cartId = orderDto.getCart().getId();
        Cart cart = this.cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException(
                        "The Cart with ID[" + cartId + "] was not found !"));
        return mapToDto(this.orderRepository.save(new Order(BigDecimal.ZERO,
                OrderStatus.CREATION, null, null,
                AddressService.createFromDto(orderDto.getShipmentAddress()),
                Collections.emptySet(), cart)));
    }
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Order with ID[" + id + "] cannot be found!"));
        Optional.ofNullable(order.getPayment())
                .ifPresent(paymentRepository::delete);
        orderRepository.delete(order);
    }
    public boolean existsById(Long id) {
        return this.orderRepository.existsById(id);
    }
    public static OrderDto mapToDto(Order order) {
        Set<OrderItemDto> orderItems = order.getOrderItems()
                .stream().map(OrderItemService::mapToDto).
                collect(Collectors.toSet());
        return new OrderDto(
                order.getId(),
                order.getPrice(),
                order.getStatus().name(),
                order.getShipped(),
                order.getPayment() != null ? order.getPayment().getId() : null,
                AddressService.mapToDto(order.getShipmentAddress()),
                orderItems,
                CartService.mapToDto(order.getCart())
        );
    }
}