package com.bootcamp.bc.bc_yahoo_finance.infra;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
  private String code;

  public BusinessException(SysCode code) {
    super(code.getDesc());
    this.code = code.getCode();
  }
}
