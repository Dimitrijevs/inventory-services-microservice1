package com.microservices.inventory_service.dto;

public record InventoryRequest(Long id, String skuCode, Integer quantity) {

}
