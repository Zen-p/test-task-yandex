package com.example.test.task.components.schemas;


import com.example.test.task.components.enums.SystemItemType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemItemImport {

    @NotNull(message = "ID cannot be null")
    @Size(min = 1, max = 255, message = "ID length must be between 1 and 255 characters")
    private String id;

    @Column(name = "url")
    @Size(max = 255, message = "URL length must not exceed 255 characters")
    private String url;

    private String parentId;

    @Enumerated(value = EnumType.STRING)
    private SystemItemType type;

    @Column(name = "size")
    @Min(value = 1, message = "Size must be non-negative")
    private Integer size;

}
