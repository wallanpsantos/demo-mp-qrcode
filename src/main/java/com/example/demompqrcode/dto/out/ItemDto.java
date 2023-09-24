package com.example.demompqrcode.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(NON_EMPTY)
public class ItemDto {

    private static final  String UNIT_MEASURE = "unit";

    private String skuNumber;
    private String category;
    private String title;
    private String description;
    private Integer quantity;
    private String unitMeasure = UNIT_MEASURE;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;

}
