package com.caramelwaffle.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseData {
    private String statusCode;
    private String message;
}
