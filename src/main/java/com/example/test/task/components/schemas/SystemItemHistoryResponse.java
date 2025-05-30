package com.example.test.task.components.schemas;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SystemItemHistoryResponse {

    private List<SystemItemHistoryUnit> items;

}
