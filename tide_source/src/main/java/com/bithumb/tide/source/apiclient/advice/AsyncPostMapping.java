package com.bithumb.tide.source.apiclient.advice;

import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;

public class AsyncPostMapping implements PostMapping {

    private String name;
    private String[] value;
    private String[] path;
    private String[] params;
    private String[] headers;
    private String[] consumes;
    private String[] produces;

    public AsyncPostMapping(
            String name,
            String[] value,
            String[] path,
            String[] params,
            String[] headers,
            String[] consumes,
            String[] produces
    ) {
        this.name = name;
        this.value = value;
        this.path = path;
        this.params = params;
        this.headers = headers;
        this.consumes = consumes;
        this.produces = produces;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String[] value() {
        return value;
    }

    @Override
    public String[] path() {
        return path;
    }

    @Override
    public String[] params() {
        return params;
    }

    @Override
    public String[] headers() {
        return headers;
    }

    @Override
    public String[] consumes() {
        return consumes;
    }

    @Override
    public String[] produces() {
        return produces;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return AsyncPostMapping.class;
    }
}
