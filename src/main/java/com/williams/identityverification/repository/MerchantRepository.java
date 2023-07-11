package com.williams.identityverification.repository;

import com.williams.identityverification.model.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant,String> {

    Merchant findMerchantByEmail(String email);

    Merchant findMerchantByMerchantSerial(String serial);
}
