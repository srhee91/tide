package com.bithumb.tide.engine.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Value;

@Value
public class TaskRequestedEvent {
    String id;
    String path;
    String requestBody;
}
