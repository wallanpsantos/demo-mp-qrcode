package com.example.demompqrcode.dto.in;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(NON_EMPTY)
public class OrderDetailsDto {

    private String id;
    private String status;
    private String externalReference;
    private List<OrderPaymentDto> payments;

    @JsonProperty("collector")
    private MerchantDto merchant;

    private String notificationUrl;
    private ZonedDateTime dateCreated;
    private ZonedDateTime lastUpdated;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;

    @JsonProperty("payer")
    private CustomerDto customer;

    private List<ItemDto> items;
    private String orderStatus;

}
