package com.lucio.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private String id;
    private String userIdentifier;
    private LocalDateTime date;
    private List<ItemDTO> items;
    private Double total;
}