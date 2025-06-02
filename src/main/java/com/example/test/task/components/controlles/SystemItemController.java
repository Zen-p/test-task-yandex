package com.example.test.task.components.controlles;


import com.example.test.task.components.schemas.Error;
import com.example.test.task.components.schemas.SystemItemImportRequest;
import com.example.test.task.components.services.SystemItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SystemItemController {

    private SystemItemService systemItemService;

    @PostMapping("/imports")
    public ResponseEntity<?> importItem (@RequestBody SystemItemImportRequest request) {
        try {
            systemItemService.importElements(request);
            return ResponseEntity.ok("Вставка или обновление прошли успешно.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(new Error(400, "Validation Failed"));
        }

    }




}
