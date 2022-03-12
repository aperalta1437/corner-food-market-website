package com.cornerfoodmarketwebsite.business.dto.request.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AdministratorLoginForm {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
