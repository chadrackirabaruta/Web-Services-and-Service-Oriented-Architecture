package com.programmingtechie.orderservice.service;

import com.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(dto -> mapToEntity(dto, order))
                .toList();
        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToEntity(OrderLineItemsDto orderLineItemsDto, Order order) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setOrder(order);
        orderLineItems.setProductId(orderLineItemsDto.getId());  // Assuming productId is a property of OrderLineItems
        orderLineItems.setRemark(orderLineItemsDto.getRemark());
        orderLineItems.setCustomerName(orderLineItemsDto.getCustomerName());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }

    public List<Order> getOrdersByProduct(Long productId) {
        return orderRepository.findOrdersByOrderLineItemsList_ProductId(productId);
    }
}
