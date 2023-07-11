package com.williams.identityverification.service;

import com.williams.identityverification.util.API;
import com.williams.identityverification.util.API2;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class SpotifyConsumeService {

    private API api;

    private API2 api2;

    public Object getArtistInfo(String artistId){
        Object obj = api.getTransaction(artistId);
        log.info("Check on service layer{}",obj);
        return obj;
    }

    public Object createArtistInfo(Object artistId){
        Object obj = api.processPayment(artistId);
        log.info("Check on service layer{}",obj);
        return obj;
    }

    public Object getArtistById(String artistId) throws IOException {
        Object obj = api2.getById(artistId);
//        log.info("Check on service layer123{}",obj);
        return obj;
    }
}
