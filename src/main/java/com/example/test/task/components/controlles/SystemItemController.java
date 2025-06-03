package com.example.test.task.components.controlles;


import com.example.test.task.components.schemas.Error;
import com.example.test.task.components.schemas.SystemItemImportRequest;
import com.example.test.task.components.services.SystemItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class SystemItemController {

    private SystemItemService systemItemService;

    @PostMapping("/imports")
    public ResponseEntity<?> importItem (@RequestBody SystemItemImportRequest request) {
        try {
            systemItemService.importElements(request);
            return ResponseEntity.ok("Вставка или обновление прошли успешно.");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(new Error(400, "Validation Failed"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem (@PathVariable String id) {
        try {
            return systemItemService.deleteItem(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(new Error(400, "Validation Failed"));
        }
    }

    @GetMapping("/nodes/{id}")
    public ResponseEntity<?> getItem (@PathVariable String id) {
        try {
            return systemItemService.getItem(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(new Error(400, "Validation Failed"));
        }
    }




}
