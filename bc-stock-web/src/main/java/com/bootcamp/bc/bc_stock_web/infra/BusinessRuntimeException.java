package com.bootcamp.bc.bc_stock_web.infra;

import lombok.Getter;

@Getter
public class BusinessRuntimeException extends RuntimeException {
  private String code;

  public BusinessRuntimeException(SysCode code) {
    super(code.getDesc());
    this.code = code.getCode();
  }
}
