package com.example.test.task.components.schemas;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Error {
    private int code;
    private String message;
}
