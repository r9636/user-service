package com.user.service.aspect;

import com.user.service.dto.response.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@Slf4j
@Aspect
@Configuration
public class AspectConfiguration {

    @Before("execution(public * com.user.service.resource.impl.UserResourceImpl.createUser(..))")
    public void logBeforeCreateUser(JoinPoint joinPoint){
      log.info("Create User request {}", Arrays.stream(joinPoint.getArgs()).findFirst());
    }

    @AfterReturning(pointcut = "execution(public * com.user.service.resource.impl.UserResourceImpl.createUser(..))",
            returning = "response")
    public void logAfterReturning(JoinPoint joinPoint, ResponseEntity<UserResponseDTO> response){
        log.info("Created User {}", response.getBody());
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object logAroundMethodForAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("User Retrieval for userId - {}", Arrays.stream(proceedingJoinPoint.getArgs()).findFirst());
        ResponseEntity<UserResponseDTO> returnedObject = (ResponseEntity<UserResponseDTO>) proceedingJoinPoint.proceed();
        log.info("User retrieved - {}", returnedObject.getBody());
        return returnedObject;
    }
}
