package com.example.test.task.components.services;


import com.example.test.task.components.repositories.SystemItemRepository;
import com.example.test.task.components.schemas.SystemItemImportRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class SystemItemService {

    private SystemItemRepository systemItemRepository;


    public static void importElements (SystemItemImportRequest request) {
        
    }

}
