package com.bithumb.tide.engine.aggregate;

import com.bithumb.tide.engine.dto.RequestTaskCommand;
import com.bithumb.tide.engine.dto.TaskRequestedEvent;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@NoArgsConstructor
public class TaskAggregate {

    @AggregateIdentifier
    private String id;
    private String path;
    private String requestBody;

    @CommandHandler
    public TaskAggregate(RequestTaskCommand command) {
        log.info("ComandHandling {}", command);
        apply(new TaskRequestedEvent(command.getId(), command.getPath(), command.getRequestBody()));
    }

    @EventSourcingHandler
    public void on(TaskRequestedEvent event) {
        log.info("EventSourcing {}", event);
        this.id = event.getId();
        this.path = event.getPath();
        this.requestBody = event.getRequestBody();
    }
}
