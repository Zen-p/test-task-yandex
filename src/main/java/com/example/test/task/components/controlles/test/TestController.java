package com.example.test.task.components.controlles.test;


import com.example.test.task.components.enums.SystemItemType;
import com.example.test.task.components.schemas.SystemItemImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/send-request")
    public void sendReqest () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SystemItemImport importItem = SystemItemImport.builder()
                .id("file_1")
                .parentId("folder_1")
                .size(234)
                .url("/folder_1")
                .type(SystemItemType.FILE)
                .build();

        HttpEntity<SystemItemImport> request = new HttpEntity<>(importItem, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8080/imports",
                request,
                String.class);

        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
    }

}
