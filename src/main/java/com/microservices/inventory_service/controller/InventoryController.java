package com.microservices.inventory_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.inventory_service.dto.InventoryRequest;
import com.microservices.inventory_service.dto.InventoryResponse;
import com.microservices.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse create(@RequestBody InventoryRequest request) {
        return inventoryService.create(request);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> all() {
        return inventoryService.all();
    }

    @GetMapping("/availability")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
}
