package com.lucio.api.shopping.shopping_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Document(collection = "shop")
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
}