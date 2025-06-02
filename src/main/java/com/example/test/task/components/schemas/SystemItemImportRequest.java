package com.example.test.task.components.schemas;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class SystemItemImportRequest {
    private List<SystemItemImport> items;
    private Instant updateDate;

    public Instant getUpdateDate () {
        return Instant.now();
    }
}
