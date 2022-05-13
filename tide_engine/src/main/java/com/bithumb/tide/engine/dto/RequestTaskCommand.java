package com.bithumb.tide.engine.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@ToString
@Getter
public class RequestTaskCommand {
    @TargetAggregateIdentifier
    protected String id;
    protected String path;
    protected String requestBody;
}
