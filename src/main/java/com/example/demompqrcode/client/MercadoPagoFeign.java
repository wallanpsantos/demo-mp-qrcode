package com.example.demompqrcode.client;

import com.example.demompqrcode.dto.in.ElementDto;
import com.example.demompqrcode.dto.in.OrderStoreDto;
import com.example.demompqrcode.dto.out.OrderDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mercado-pago", url = "https://api.mercadopago.com")
@Headers({
        "Content-Type: application/json; charset=utf-8",
        "Accept: application/json; charset=utf-8"
})
public interface MercadoPagoFeign {


    @PostMapping(value = "/instore/orders/qr/seller/collectors/{user_id}/pos/{pos_id}/qrs", produces = "application/json")
    ResponseEntity<OrderStoreDto> create(@RequestHeader(name = "Authorization") String token,
                                         @PathVariable(name = "user_id") String userId,
                                         @PathVariable(name = "pos_id") String posId,
                                         @RequestBody OrderDto orderDto);

    @GetMapping(value = "/merchant_orders", produces = "application/json")
    ResponseEntity<ElementDto> getOrderDetails(@RequestHeader(name = "Authorization") String token,
                                               @RequestParam("external_reference") String externalReference);
}
