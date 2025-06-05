package com.example.test.task.components.services;


import com.example.test.task.components.enums.SystemItemType;
import com.example.test.task.components.repositories.HistoryRepository;
import com.example.test.task.components.repositories.SystemItemRepository;
import com.example.test.task.components.schemas.*;
import com.example.test.task.components.schemas.Error;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Scope("singleton")
@AllArgsConstructor
@Slf4j
public class SystemItemService {

    private SystemItemRepository repository;
    private HistoryRepository historyRepository;

    @Transactional
    public  void importElements (SystemItemImportRequest request) {

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
            historyRepository.save(
                    SystemItemHistoryUnit.builder()
                            .id(systemItem.getId())
                            .url(systemItem.getUrl())
                            .type(systemItem.getType())
                            .date(systemItem.getDate())
                            .parentId(systemItem.getParentId())
                            .children(systemItem.getChildren())
                            .size(systemItem.getSize())
                            .build()
            );
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteItem (String id) {

        try {
            SystemItem itemToDelete = repository.findById(id).orElse(null);

            if (itemToDelete == null) return ResponseEntity.status(404).body(new Error(404, "Item not found"));

            repository.delete(itemToDelete);

            return ResponseEntity.status(200).body("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new Error(400, "Validation failed"));
        }

    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getItem (String id) {
        try {
            SystemItem item = repository.findById(id).orElse(null);

            if (item == null) return ResponseEntity.status(404).body(new Error(404, "Item not found"));

            return ResponseEntity.status(200).body(item);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new Error(400, "Validation Failed"));
        }

    }

    public ResponseEntity<?> getUpdates () {
        try {
            List<SystemItemHistoryUnit> historyUnits = historyRepository.findUpdates().orElse(null);

            if (historyUnits == null) return ResponseEntity.status(404).body(new Error(404, "Item not found"));

            return ResponseEntity.status(200).body(historyUnits);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new Error(400, "Validation Failed"));
        }
    }




}
