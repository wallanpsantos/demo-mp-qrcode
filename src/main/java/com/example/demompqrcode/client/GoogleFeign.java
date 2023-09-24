package com.example.demompqrcode.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-google-chart", url = "https://chart.googleapis.com")
@Headers({
        "Content-Type: application/json; charset=utf-8",
        "Accept: application/json; charset=utf-8"
})
public interface GoogleFeign {

    @GetMapping("/chart")
    byte[] getImageQrCode(@RequestParam("chs") String chs,
                          @RequestParam("cht") String cht,
                          @RequestParam("chl") String data,
                          @RequestParam("chld") String chld);
}
