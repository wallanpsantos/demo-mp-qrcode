package com.example.demompqrcode.controller;


import com.example.demompqrcode.dto.in.OrderDetailsDto;
import com.example.demompqrcode.dto.in.OrderStoreDto;
import com.example.demompqrcode.dto.out.OrderDto;
import com.example.demompqrcode.dto.out.QrCode;
import com.example.demompqrcode.service.TotemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/totem")
@RequiredArgsConstructor
public class TotemController {

    private final TotemService totemService;

    @PostMapping
    public ResponseEntity<OrderStoreDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(totemService.createOrder(orderDto));
    }

    @GetMapping("/{externalReference}")
    public ResponseEntity<OrderDetailsDto> findOrderPayment(@PathVariable String externalReference) {
        return ResponseEntity.ok(totemService.getOrderPayment(externalReference).getElements().get(0));
    }

    @PostMapping(value = "/qr-code", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> showQrCode(@RequestBody QrCode qrCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(totemService.getImageQRCode(qrCode), headers, HttpStatus.OK);
    }
}
