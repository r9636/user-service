package com.user.service.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Error {
    private ZonedDateTime zonedDateTime;
    private String message;
    private String status;
    private int code;
}
