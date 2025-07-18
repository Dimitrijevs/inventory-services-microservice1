package com.microservices.inventory_service.dto;

public record InventoryResponse(Long id, String skuCode, Integer quantity) {

}
