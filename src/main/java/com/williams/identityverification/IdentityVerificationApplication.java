package com.williams.identityverification;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Identity verification APP",version = "1.0",description = "This app verifies Identity"))
public class IdentityVerificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityVerificationApplication.class, args);
    }

}
