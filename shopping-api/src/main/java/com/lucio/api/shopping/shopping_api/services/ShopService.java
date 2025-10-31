package com.lucio.api.shopping.shopping_api.services;

import com.lucio.api.shopping.shopping_api.models.Shop;
import com.lucio.api.shopping.shopping_api.models.Item;
import com.lucio.api.shopping.shopping_api.repositories.ShopRepository;
import com.lucio.dto.ShopDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public Shop saveShop(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setUserIdentifier(shopDTO.getUserIdentifier());
        shop.setDate(LocalDateTime.now());
        List<Item> items = shopDTO.getItems() == null ? Collections.emptyList() : shopDTO.getItems().stream()
            .map(dto -> new Item(dto.getProductIdentifier(), dto.getPrice()))
            .toList();
        shop.setItems(items);
        shop.setTotal(items.stream().mapToDouble(Item::getPrice).sum());
        return shopRepository.save(shop);
    }

    public List<Shop> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        for (Shop shop : shops) {
            if (shop.getItems() == null) {
                shop.setItems(Collections.emptyList());
            }
        }
        return shops;
    }

    public Optional<Shop> getShopById(String id) {
        return shopRepository.findById(id);
    }

    public List<Shop> getShopsByUser(String userIdentifier) {
        return shopRepository.findByUserIdentifier(userIdentifier);
    }

    public List<Shop> getShopsByProductIdentifier(String productIdentifier) {
        return shopRepository.findAll().stream()
            .filter(shop -> shop.getItems() != null && shop.getItems().stream()
                .anyMatch(item -> item.getProductIdentifier().equals(productIdentifier)))
            .toList();
    }

    public List<Shop> getShopsByDate(LocalDateTime start, LocalDateTime end) {
        List<Shop> shops = shopRepository.findByDateBetween(start, end);
        for (Shop shop : shops) {
            if (shop.getItems() == null) {
                shop.setItems(Collections.emptyList());
            }
        }
        return shops;
    }

    public List<Shop> getShopsByFilter(LocalDateTime start, LocalDateTime end, Double minValue) {
        return shopRepository.findByDateBetween(start, end).stream()
            .filter(shop -> shop.getTotal() != null && shop.getTotal() >= minValue)
            .toList();
    }
}
