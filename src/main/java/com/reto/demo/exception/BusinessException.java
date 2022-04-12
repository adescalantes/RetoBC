package com.reto.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -366586152338523944L;
    private int code;
    private String errorMsg;

}
