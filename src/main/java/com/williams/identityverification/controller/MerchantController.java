package com.williams.identityverification.controller;

import com.williams.identityverification.model.request.MerchantRequest;
import com.williams.identityverification.model.response.MerchantResponse;
import com.williams.identityverification.service.base.MerchantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("merchants")
public class MerchantController {

    private final MerchantService service;

    @PostMapping()
    public ResponseEntity<MerchantResponse> createMerchant(@RequestBody @Valid MerchantRequest request){
        MerchantResponse response = service.createMerchant(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{serialNo}")
    public ResponseEntity<MerchantResponse> getMerchant(@PathVariable String serialNo){
        MerchantResponse response = service.getMerchantBySerialNumber(serialNo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
