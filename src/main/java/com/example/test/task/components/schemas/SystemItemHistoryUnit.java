package com.example.test.task.components.schemas;


import com.example.test.task.components.enums.SystemItemType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_item_history_unit")
public class SystemItemHistoryUnit {

    @Column(name = "id", nullable = false, updatable = false)
    @NotNull(message = "ID cannot be null")
    @Size(min = 1, max = 255, message = "ID length must be between 1 and 255 characters")
    private String id;

    @Column(name = "url", length = 255)
    @Size(max = 255, message = "URL length must not exceed 255 characters")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @NotNull(message = "Type cannot be null")
    private SystemItemType type;

    @Column(name = "date", nullable = false)
    @NotNull(message = "Date cannot be null")
    private Instant date;

    private String parentId;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SystemItem> children = new ArrayList<>();

    @Column(name = "size")
    @Min(value = 0, message = "Size must be non-negative")
    private Integer size;

}
