package com.example.test.task.components;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemItem {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    private String url; // Для папок поле равнно null.

    @Column(nullable = false)
    private Instant date;

    private String parentId; // id родительской папки

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SystemItemType type;

    private int size;



    private List<SystemItem> children; // Для файлов поле равно null.





}
