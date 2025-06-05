package com.example.test.task.components.schemas;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SystemItemHistoryResponse {

    private List<SystemItemHistoryUnit> items;

}
