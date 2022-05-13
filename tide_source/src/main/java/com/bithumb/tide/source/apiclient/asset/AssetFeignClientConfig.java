package com.bithumb.tide.source.apiclient.asset;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger.Level;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class AssetFeignClientConfig {
    @Bean
    Level loggerLevel() {
        return Level.FULL;
    }

//    @Bean
//    public Decoder feignDecoder() {
//        HttpMessageConverter jacksonConverter = createMappingJackson2HttpMessageConverter();
//        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
//        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
//    }
//
//    private MappingJackson2HttpMessageConverter createMappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(createObjectMapper());
//        return converter;
//    }
//
//    private ObjectMapper createObjectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper;
//    }
}
