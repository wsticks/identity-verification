package com.williams.identityverification.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource(value = "classpath:application.properties")
@Slf4j
public class API2 {

    @Autowired
    private RestTemplate template;
    HttpHeaders headers = null;
    ObjectMapper mapper = null;
    @Autowired
    private Environment env;

    Object response = null;
    String result = "";

    @PostConstruct
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("BQA8lL5xBM6jvs3g4SQ7G-JxlV-0uprBQHhza7Isd4qPjuGN05mujYdgvndcCinhUAZVtPRFdGf_nT_UeTHyA_UVOVa109tXaRQ_f87VE7jGwQcLRzY");
//        headers.add("Authorization","Bearer BQDGVh6H4vdGHW4xnH4TiasRv-xDAkuPVndreua1ZH_dKl9wXsqo-OvpsU3fCzerr_siS5PaHp5OMA1nCch8KwXdgRD1HNy725aoGU6Rjv-ZDoAwdKk");
        mapper = new ObjectMapper();
    }

    public Object getAll() throws IOException {
        String url = env.getProperty("payment.url");
        HttpEntity<String> entity = new HttpEntity<>("parameter",headers);
        ResponseEntity<String> responseEntity = template.exchange(url, HttpMethod.GET,entity,String.class);
//		ResponseEntity<String> responseEntity = template.exchange(
//				url,
//				HttpMethod.GET,
//				new HttpEntity<>(payment, headers),
//				String.class
//		);
        result = responseEntity.getBody();
        response = mapper.readValue(result, Object.class);
        return response;
    }

    public Object post(Object obj) throws IOException {
        String url = env.getProperty("payment.url");
        HttpEntity<String> entity = new HttpEntity<>("parameter",headers);
        ResponseEntity<String> responseEntity = template.exchange(url,HttpMethod.POST,entity,String.class);
        result = responseEntity.getBody();
        response = mapper.readValue(result, Object.class);
        return response;
    }

    public Object put(Object obj) throws IOException {
        String url = env.getProperty("payment.url");
        HttpEntity<String> entity = new HttpEntity<>("parameter",headers);
        ResponseEntity<String> responseEntity = template.exchange(url,HttpMethod.PUT,entity,String.class);
        result = responseEntity.getBody();
        response = mapper.readValue(result, Object.class);
        return response;
    }

    public Object getById(String id) throws IOException {
//        String url = env.getProperty("transaction.url" + id);
       String url ="https://api.spotify.com/v1/albums?ids=" +id;
        log.info("URL {}", url);
        HttpEntity<String> entity = new HttpEntity<>("parameter",headers);
        ResponseEntity<String> responseEntity = template.exchange(url,HttpMethod.GET,entity,String.class);
        result = responseEntity.getBody();
        response = mapper.readValue(result, Object.class);
        return response;
    }

    public Object delete(String id) throws IOException {
        String url = env.getProperty("payment.url" + id);
        HttpEntity<String> entity = new HttpEntity<>("parameter",headers);
        ResponseEntity<String> responseEntity = template.exchange(url,HttpMethod.DELETE,entity,String.class);
        result = responseEntity.getBody();
        response = mapper.readValue(result, Object.class);
        return response;
    }

//    Methode 2 for getById
//    public Object getById(String id ) throws IOException {
//        Map<String, String> map = new HashMap<>();
//        map.put("Content-Type","application/json");
//        map.put("Authorization","BQA8lL5xBM6jvs3g4SQ7G-JxlV-0uprBQHhza7Isd4qPjuGN05mujYdgvndcCinhUAZVtPRFdGf_nT_UeTHyA_UVOVa109tXaRQ_f87VE7jGwQcLRzY");
//        map.put("id",id);
////        String url = env.getProperty("payment.url" + id);
//        String url ="https://api.spotify.com/v1/albums?ids=" +id;
////        HttpEntity<String> entity = new HttpEntity<>("parameter",headers);
//        String responseEntity = template.getForObject(url,String.class,map);
//        result = responseEntity;
//        response = mapper.readValue(result, Object.class);
//        return response;
//    }
}
