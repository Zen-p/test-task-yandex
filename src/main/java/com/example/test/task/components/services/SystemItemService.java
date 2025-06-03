package com.example.test.task.components.services;


import com.example.test.task.components.enums.SystemItemType;
import com.example.test.task.components.repositories.SystemItemRepository;
import com.example.test.task.components.schemas.Error;
import com.example.test.task.components.schemas.SystemItem;
import com.example.test.task.components.schemas.SystemItemImport;
import com.example.test.task.components.schemas.SystemItemImportRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Scope("singleton")
@AllArgsConstructor
public class SystemItemService {

    private SystemItemRepository repository;



    public  void importElements (SystemItemImportRequest request) {

        // validation
        for (SystemItemImport item : request.getItems()) {

            SystemItem systemItem = repository.findById(item.getId()).orElse(new SystemItem());

            if (systemItem.getType() != null && systemItem.getType() != item.getType()) {
                throw new IllegalArgumentException("Cannot change element type");
            }
            systemItem.setId(item.getId());
            systemItem.setType(item.getType());
            systemItem.setUrl(item.getType() == SystemItemType.FILE ? item.getUrl() : null);
            if (systemItem.getType() == SystemItemType.FOLDER && systemItem.getUrl() != null) {
                throw new IllegalArgumentException("Folder must not have URL");
            }



            systemItem.setSize(item.getType() == SystemItemType.FILE ? item.getSize() : systemItem.getSize());

            if (item.getParentId() != null ) {
                SystemItem parent = repository.findById(item.getParentId())
                        .orElseThrow(() -> new IllegalArgumentException("Parent not found: " + item.getParentId()));
                if (parent.getType() != SystemItemType.FOLDER) {
                    throw new IllegalArgumentException("Parent must be a folder");
                }
                systemItem.setParentId(item.getParentId());
            } else {
                systemItem.setParentId(null);
            }

            systemItem.setDate(request.getUpdateDate());

            repository.save(systemItem);

        }

    }

    public ResponseEntity deleteItem (String id) {
        try {
            SystemItem itemToDelete = repository.findById(id).orElse(null);
            repository.delete(itemToDelete);
            return ResponseEntity.ok("Удаление успешно");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new Error(404, "Item not found"));
        }

    }

    public ResponseEntity getItem (String id) {
        try {
            SystemItem item = repository.findById(id).orElse(null);
            if (item == null) return ResponseEntity.status(404).body(new Error(404, "Item not found"));
            return ResponseEntity.status(200).body(item);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(new Error(400, "Validation Failed"));
        }

    }


}
