package com.bootcamp.bc.bc_stock_web.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResp<Void> notFoundExceptionHandler(NotFoundException e) {
    return ApiResp.<Void>builder().fail(SysCode.NOT_FOUND).data(null)
        .build();
  }

  @ExceptionHandler(NumberFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResp<Void> userIdInvalidExceptionHandler(NumberFormatException e) {
    return ApiResp.<Void>builder().fail(SysCode.USERID_INVALID).data(null)
        .build();
  }

  @ExceptionHandler(RestClientException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResp<Void> RestTemplateExceptionHandler(RestClientException e) {
    return ApiResp.<Void>builder().fail(SysCode.RESTTEMPLATE_ERROR)
        .data(null).build();
  }

  @ExceptionHandler(NoResourceFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResp<Void> InvalidUrlExceptionHandler(NoResourceFoundException e) {
    return ApiResp.<Void>builder().fail(SysCode.URL_ERROR).data(null)
        .build();
  }
  
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResp<Void> ExceptionHandler(Exception e) {
  return ApiResp.<Void>builder().message(e.getMessage()).build();
  }
  
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResp<Void> RuntimeExceptionHandler(RuntimeException e) {
  return ApiResp.<Void>builder().message(e.getMessage()).build();
  }
}
