package com.williams.identityverification.service;

import com.williams.identityverification.exception.ConflictException;
import com.williams.identityverification.exception.NotFoundException;
import com.williams.identityverification.model.model.Merchant;
import com.williams.identityverification.model.request.MerchantRequest;
import com.williams.identityverification.model.response.MerchantResponse;
import com.williams.identityverification.repository.MerchantRepository;
import com.williams.identityverification.service.base.MerchantService;
import com.williams.identityverification.util.CustomResponseCode;
import com.williams.identityverification.util.EncrytionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@AllArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository repository;
    private final ModelMapper mapper;

    public MerchantResponse createMerchant(MerchantRequest request){
        log.info("Merchant Request ......{}",request);
        Merchant savedMerchant = repository.findMerchantByEmail(request.getEmail());
        if (savedMerchant != null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,"Merchant already exist");
        }
        Merchant merchant = mapper.map(request,Merchant.class);
        generateProductSerialNumber(merchant);
        merchant =repository.save(merchant);
        return mapper.map(merchant, MerchantResponse.class);
    }

    public MerchantResponse getMerchantBySerialNumber(String serialNo){
        Merchant savedMerchant = repository.findMerchantByMerchantId(serialNo);
        if (savedMerchant == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " Merchant does not exist");
        }
        return mapper.map(savedMerchant,MerchantResponse.class);
    }

    private void generateProductSerialNumber(Merchant request) {
        String rawKey = request.getMerchantName() + request.getMobileNumber() + new Date() + Math.random();
        String encodedKey = EncrytionUtil.hashWithSha256(rawKey);
        request.setMerchantId("Serial-" + encodedKey.substring(0, 20));
        log.info("Merchant Serial no is generated");
    }
}
