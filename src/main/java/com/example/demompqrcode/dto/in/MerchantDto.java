package com.example.demompqrcode.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {

    private String id;
    private String email;
    private String nickname;

}
