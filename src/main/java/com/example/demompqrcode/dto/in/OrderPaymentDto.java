package com.example.demompqrcode.dto.in;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class OrderPaymentDto {

    private String id;
    private BigDecimal totalPaidAmount;
    private String currencyId;
    private String status;
    private String operationType;
    private ZonedDateTime dateApproved;
}
