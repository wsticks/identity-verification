package com.williams.identityverification.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantResponse {

    private String merchantId;
    private String merchantSerial;
    private String email;
    private String merchantName;
    private String mobileNumber;
    private String createdAt;
    private String updatedAt;
}
