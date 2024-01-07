package com.programmingtechie.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    private Long id;
    private String remark;  // Changed from Remark to remark
    private String customerName;  // Changed from customer_name to customerName
    private Integer quantity;
}
