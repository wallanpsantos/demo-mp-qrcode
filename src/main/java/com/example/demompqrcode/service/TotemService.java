package com.example.demompqrcode.service;

import com.example.demompqrcode.client.GoogleFeign;
import com.example.demompqrcode.client.MercadoPagoFeign;
import com.example.demompqrcode.dto.in.OrderDetailsDto;
import com.example.demompqrcode.dto.in.OrderStoreDto;
import com.example.demompqrcode.dto.out.ItemDto;
import com.example.demompqrcode.dto.out.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class TotemService {

    private static final String POS_ID = "POSTOTEM001";
    private static final String USER_ID = "1481636739";
    private static final String TOKEN = "Bearer TEST-105948482427385-091923-6da7fcb314517a7cee96dee919c54ab5-1481636739";
    private static final String URL_NOTIFICATION = "https://eoywrutussrdfbi.m.pipedream.net/";
    private static final ZonedDateTime DURATION_QR_CODE = ZonedDateTime.now().plusHours(1);

    private final MercadoPagoFeign mercadoPagoFeign;
    private final GoogleFeign googleFeign;

    public OrderStoreDto createOrder(OrderDto orderDto) {
        String externalReference = UUID.randomUUID().toString();

        log.info("EXTERNAL REFERENCE TO CREATE ORDER: " + externalReference);

        orderDto.setExternalReference(externalReference);

        updateItemTotalAmount(orderDto);
        updateOrderTotalAmount(orderDto);

        orderDto.setTitle("Atendimento via Totem");
        orderDto.setDescription("Pedido realizado via auto atendimento Totem");
        orderDto.setExpirationDate(DURATION_QR_CODE);
        orderDto.setNotificationUrl(URL_NOTIFICATION);

        return mercadoPagoFeign.create(TOKEN, USER_ID, POS_ID, orderDto).getBody();
    }

    public OrderDetailsDto getOrderPayment(String externalReference) {
        log.info("Search order with externalReference: " + externalReference);
        return Objects.requireNonNull(mercadoPagoFeign.getOrderDetails(TOKEN, externalReference).getBody()).getElements().get(0);
    }

    private void updateOrderTotalAmount(OrderDto orderDto) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (ItemDto item : orderDto.getItems()) {
            totalAmount = totalAmount.add(item.getTotalAmount());
        }
        orderDto.setTotalAmount(totalAmount);
    }

    public void updateItemTotalAmount(OrderDto orderDto) {
        for (ItemDto itemDto : orderDto.getItems()) {
            itemDto.setTotalAmount(itemDto.getUnitPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
        }
    }

    public byte[] getImageQRCode(String data) {
        log.info("The data to be generated to image QR Code: " + data);

        // Tamanho da imagem em Pixel
        String imageWidthHeight = "500x500";
        // Especifica um código QR.
        String imageType = "qr";
        // Recuperação de até 30% dos dados perdidos.
        String recovery = "H";

        return googleFeign.getImageQrCode(imageWidthHeight, imageType, data, recovery);

    }
}
