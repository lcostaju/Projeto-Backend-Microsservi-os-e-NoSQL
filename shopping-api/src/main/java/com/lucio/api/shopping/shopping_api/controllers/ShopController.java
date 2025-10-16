package com.lucio.api.shopping.shopping_api.controllers;

import com.lucio.api.shopping.shopping_api.models.Shop;
import com.lucio.api.shopping.shopping_api.models.dto.ShopDTO;
import com.lucio.api.shopping.shopping_api.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public ResponseEntity<Shop> saveShop(@RequestBody ShopDTO shopDTO) {
        Shop shop = shopService.saveShop(shopDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(shop);
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        return ResponseEntity.ok(shopService.getAllShops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable String id) {
        return shopService.getShopById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/shopByUser")
    public ResponseEntity<List<Shop>> getShopsByUser(@RequestParam String userIdentifier) {
        return ResponseEntity.ok(shopService.getShopsByUser(userIdentifier));
    }

    @GetMapping("/product/{productIdentifier}")
    public ResponseEntity<List<Shop>> getShopsByProductIdentifier(@PathVariable String productIdentifier) {
        return ResponseEntity.ok(shopService.getShopsByProductIdentifier(productIdentifier));
    }

    @GetMapping("/shopByDate")
    public ResponseEntity<List<Shop>> getShopsByDate(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(shopService.getShopsByDate(startDate, endDate));
    }

    @GetMapping("/report")
    public ResponseEntity<List<Shop>> getReportByDate(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(shopService.getShopsByDate(startDate, endDate));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Shop>> getShopsByFilter(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate, @RequestParam Double minValue) {
        return ResponseEntity.ok(shopService.getShopsByFilter(startDate, endDate, minValue));
    }
}
