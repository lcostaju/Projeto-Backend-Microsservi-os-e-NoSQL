package com.lucio.api.shopping.shopping_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.lucio.dto.ShopDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "shop")
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    @Id
    private String id;

    @Field("user_identifier")
    private String userIdentifier;

    @Field("date")
    private LocalDateTime date;

    @Field("items")
    private List<Item> items;

    @Field("total")
    private Double total;

    // public static Shop fromDTO(ShopDTO shopDTO){
    //     List<Item> items = shopDTO.getItems().stream()
    //     .map(Item :: fromDTO)
    //     .collect(Collectors.toList());
    // }
}