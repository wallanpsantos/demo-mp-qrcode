package com.example.demompqrcode.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Classe Utils responsavel por converter String para
 * um Array de Byte e gerar imagem QR Code.
 * Em Conjunto com MediaType.IMAGE_PNG
 */
//TODO Não remover essa classe, será útil quando API do Google ficar fora.
public class QrCodeUtils {

    private QrCodeUtils() {
    }

    public static byte[] generatedImageQrCode(String data) throws IOException {

        URL url = new URL(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            byte[] chunk = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }
        }

        return outputStream.toByteArray();
    }
}
