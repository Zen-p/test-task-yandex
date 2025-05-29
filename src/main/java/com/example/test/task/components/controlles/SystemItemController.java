package com.example.test.task.components.controlles;


import com.example.test.task.components.SystemItem;
import com.example.test.task.components.SystemItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SystemItemController {

    private SystemItemRepository repository;

    @GetMapping("/")
    public void test () {
        repository.save(new SystemItem());
        repository.save(new SystemItem());
        repository.save(new SystemItem());
    }
}
