package com.microservices.inventory_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.inventory_service.dto.InventoryRequest;
import com.microservices.inventory_service.dto.InventoryResponse;
import com.microservices.inventory_service.model.Inventory;
import com.microservices.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryResponse create(InventoryRequest request) {
        Inventory inventory = Inventory.builder()
                .skuCode(request.skuCode())
                .quantity(request.quantity())
                .build();

        inventoryRepository.save(inventory);

        return new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity());
    }

    public List<InventoryResponse> all() {
        return inventoryRepository.findAll()
            .stream()
            .map(inventory -> new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity()))
            .toList();
    }

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
