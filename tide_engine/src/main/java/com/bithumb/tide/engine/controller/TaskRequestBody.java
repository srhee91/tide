package com.bithumb.tide.engine.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestBody {
    private String result;
}

//data class TakeAssetResponse(
//        val result: String,
//        )
