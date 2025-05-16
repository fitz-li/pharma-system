package com.sanofi.pharma.exception;

import com.sanofi.pharma.dto.common.ApiResponse;
import com.sanofi.pharma.dto.common.ErrorInfo;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ApiResponse<?> handleApiException(ApiException ex) {
        return new ApiResponse<>(null, null, new ErrorInfo(ex.getType(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse<?> handleException(Exception ex) {
        return new ApiResponse<>(null, null, new ErrorInfo("INTERNAL_ERROR", "Internal Error"));
    }
}
