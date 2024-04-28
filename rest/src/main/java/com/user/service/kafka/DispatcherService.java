package com.user.service.kafka;

import com.user.service.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DispatcherService {
    public void process(String payload){
        System.out.println("Dispatcher service called another service for processing payload " + payload);
    }

    public void processOrder(Order payload) {
        System.out.println("Received Order " + payload);
    }
}
