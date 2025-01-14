package com.Tiago.rest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class RestModuleApplicationTests {

    @Test
    void contextLoads() {
       
    }

    @Configuration
    static class MockKafkaConfig {
        @Bean
        public KafkaTemplate<String, String> kafkaTemplate() {
            return Mockito.mock(KafkaTemplate.class);
        }
    }
}
