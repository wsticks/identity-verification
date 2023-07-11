package com.williams.identityverification.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantRequest {

    @Email
    private String email;
    @NotNull
    private String merchantName;
    @Pattern(regexp = "^\\d{10}$")
    private String mobileNumber;
}
