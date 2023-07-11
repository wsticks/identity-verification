package com.williams.identityverification.service.base;

import com.williams.identityverification.model.request.MerchantRequest;
import com.williams.identityverification.model.response.MerchantResponse;

public interface MerchantService {
    public MerchantResponse createMerchant(MerchantRequest request);

    public MerchantResponse getMerchantBySerialNumber(String serialNo);

}
