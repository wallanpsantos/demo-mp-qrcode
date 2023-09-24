package com.example.demompqrcode.dto.in;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ItemDto {

    private String id;
    private String categoryId;
    private String currencyId;
    private String description;
    private String title;
    private Integer quantity;
    private BigDecimal unitPrice;

}
