package com.williams.identityverification.model.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {

    @Id
    @Column(length = 50,nullable = false)
    private String merchantId;
    private String merchantSerial;
    private String email;
    private String merchantName;
    private String mobileNumber;
    @Column(name = "createdAt", updatable=false , length=30)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updatedAt" , length=30)
    @UpdateTimestamp
    private Timestamp updatedAt;
}
