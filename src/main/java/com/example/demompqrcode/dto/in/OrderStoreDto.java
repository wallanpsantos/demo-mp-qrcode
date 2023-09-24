package com.example.demompqrcode.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStoreDto {

    @JsonProperty("in_store_order_id")
    private String id;

    @JsonProperty("qr_data")
    private String qrData;
}
