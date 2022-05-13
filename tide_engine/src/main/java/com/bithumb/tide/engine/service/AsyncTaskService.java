package com.bithumb.tide.engine.service;

import com.bithumb.tide.engine.dto.RequestTaskCommand;
import com.bithumb.tide.engine.dto.TaskRequestedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncTaskService {

    private final CommandGateway commandGateway;
    private static final ObjectMapper mapper = new ObjectMapper();

    public String publishTask(String requestPath, JsonNode request) {
        String id = UUID.randomUUID().toString();
        return commandGateway.sendAndWait(
                RequestTaskCommand.builder()
                        .id(id)
                        .path(requestPath)
                        .requestBody(request.toString())
                        .build()
        );
    }

    @EventHandler
    public void on(TaskRequestedEvent event) throws JsonProcessingException {
        log.info("EventHandler {}", event);

        WebClient client = WebClient.create("http://localhost:8082");
        client.post()
                .uri(event.getPath())
                .body(Mono.just(mapper.readTree(event.getRequestBody())), JsonNode.class)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

    }
}
