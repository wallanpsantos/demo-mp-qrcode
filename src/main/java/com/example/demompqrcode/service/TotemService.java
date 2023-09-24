package com.example.demompqrcode.service;

import com.example.demompqrcode.client.GoogleFeign;
import com.example.demompqrcode.client.MercadoPagoFeign;
import com.example.demompqrcode.dto.in.ElementDto;
import com.example.demompqrcode.dto.in.OrderStoreDto;
import com.example.demompqrcode.dto.out.ItemDto;
import com.example.demompqrcode.dto.out.OrderDto;
import com.example.demompqrcode.dto.out.QrCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
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

        System.out.println("ExternalReference to create order: " + externalReference);

        orderDto.setExternalReference(externalReference);

        updateItemTotalAmount(orderDto);
        updateOrderTotalAmount(orderDto);

        orderDto.setTitle("Atendimento via Totem");
        orderDto.setDescription("Pedido realizado via auto atendimento Totem");
        orderDto.setExpirationDate(DURATION_QR_CODE);
        orderDto.setNotificationUrl(URL_NOTIFICATION);

        return mercadoPagoFeign.create(TOKEN, USER_ID, POS_ID, orderDto).getBody();
    }

    public ElementDto getOrderPayment(String externalReference) {
        return mercadoPagoFeign.getOrderDetails(TOKEN, externalReference).getBody();
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

    public byte[] getImageQRCode(QrCode qrCode) {
        // Tamanho da imagem em Pixel
        String imageWidthHeight = "500x500";
        // Especifica um código QR.
        String imageType = "qr";
        // Os dados a serem codificados.
        String data = qrCode.getData();
        // Recuperação de até 30% dos dados perdidos.
        String recovery = "H";

        return googleFeign.getImageQrCode(imageWidthHeight, imageType, data, recovery);

    }
}
