//package com.user.service.kafka;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaHandler;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//@KafkaListener(id = "json", topics = "mytopic", groupId = "json", containerFactory = "jsonListenerContainerFactory")
//public class UserConsumer {
//    private final DispatcherService dispatcherService;
//
//    @KafkaHandler
//    public void consumeJson(Order payload){
//        log.info("Received message in json {}", payload);
//        dispatcherService.processOrder(payload);
//    }
//}
