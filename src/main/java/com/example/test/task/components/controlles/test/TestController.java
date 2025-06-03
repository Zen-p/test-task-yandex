package com.example.test.task.components.controlles.test;


import com.example.test.task.components.enums.SystemItemType;
import com.example.test.task.components.schemas.SystemItemImport;
import com.example.test.task.components.schemas.SystemItemImportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    // Суть этого контроллера заключается в том, чтобы отправлять post-запросы по нужному адресу.
    // В течение непродолжительного времени интернета не было, а без него postman не работает,
    // потому я сделал этот контроллер и обращался к нему через терминал (curl ... )
    // прошу строго не судить
    // 👉🏼👈🏼

    @GetMapping("/send-request")
    public void sendReqest () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<SystemItemImport> items = new ArrayList<>();

        items.add(
                SystemItemImport.builder()
                .id("элемент_0")
                .parentId(null)
                .size(0)
                .url("/элемент_0")
                .type(SystemItemType.FOLDER)
                .build()
        );

        items.add(
                SystemItemImport.builder()
                        .id("элемент_1_1")
                        .parentId("элемент_0")
                        .size(0)
                        .url("/элемент_0/элемент_1_1")
                        .type(SystemItemType.FOLDER)
                        .build()
        );

        items.add(
                SystemItemImport.builder()
                        .id("элемент_1_2")
                        .parentId("элемент_0")
                        .size(0)
                        .url("/элемент_0/элемент_1_2")
                        .type(SystemItemType.FOLDER)
                        .build()
        );

        items.add(
                SystemItemImport.builder()
                        .id("элемент_2_1")
                        .parentId("элемент_1_1")
                        .size(0)
                        .url("/элемент_0/элемент_1_2/элемент_2_1")
                        .type(SystemItemType.FOLDER)
                        .build()
        );

        items.add(
                SystemItemImport.builder()
                        .id("элемент_2_1_1")
                        .parentId("элемент_2_1")
                        .size(300)
                        .url("/элемент_0/элемент_1_2/элемент_2_1/элемент_2_1_1")
                        .type(SystemItemType.FILE)
                        .build()
        );

        items.add(
                SystemItemImport.builder()
                        .id("элемент_3_1")
                        .parentId("элемент_2_1")
                        .size(0)
                        .url("/элемент_0/элемент_1_2/элемент_2_1/элемент_3_1")
                        .type(SystemItemType.FOLDER)
                        .build()
        );

        items.add(
                SystemItemImport.builder()
                        .id("элемент_3_1_1")
                        .parentId("элемент_3_1")
                        .size(4354)
                        .url("/элемент_0/элемент_1_2/элемент_2_1/элемент_3_1/элемент_3_1_1")
                        .type(SystemItemType.FILE)
                        .build()
        );

        items.add(
                SystemItemImport.builder()
                        .id("элемент_3_1_2")
                        .parentId("элемент_3_1")
                        .size(745647434)
                        .url("/элемент_0/элемент_1_2/элемент_2_1/элемент_3_1/элемент_3_1_2")
                        .type(SystemItemType.FILE)
                        .build()
        );

        items.add(
                SystemItemImport.builder()
                        .id("элемент_4_1")
                        .parentId("элемент_3_1")
                        .size(0)
                        .url("/элемент_0/элемент_1_2/элемент_2_1/элемент_3_1/элемент_4_1")
                        .type(SystemItemType.FOLDER)
                        .build()
        );


        SystemItemImportRequest requestBody = SystemItemImportRequest.builder()
                .items(items)
                .updateDate(Instant.now())
                .build();

        HttpEntity<SystemItemImportRequest> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8080/imports",
                request,
                String.class);

        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
    }

    @GetMapping("/check-if-updated")
    public void checkIfUpdated () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<SystemItemImport> items = new ArrayList<>();

        items.add(
                SystemItemImport.builder()
                        .id("элемент_3_1")
                        .parentId("элемент_2_1")
                        .size(0)
                        .url("/элемент_0/элемент_1_2/элемент_2_1/элемент_3_1")
                        .type(SystemItemType.FOLDER)
                        .build()
        );

        SystemItemImportRequest requestBody = SystemItemImportRequest.builder()
                .items(items)
                .updateDate(Instant.now()) // Установка настоящего времени
                .build();

        HttpEntity<SystemItemImportRequest> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8080/imports",
                request,
                String.class);

        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
    }

}
