package com.example.test.task.components.schemas;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class SystemItemImportRequest {
    private List<SystemItemImport> items;
    private Instant updateDate;
}
