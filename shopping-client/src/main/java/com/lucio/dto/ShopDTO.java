package com.lucio.dto;

import java.util.List;



import lombok.Data;

@Data
public class ShopDTO {
    private String userIdentifier;
    private List<ItemDTO> items;
}