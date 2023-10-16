package com.reportapp.demo.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {
    private HttpStatus code;
    private String message;

}
