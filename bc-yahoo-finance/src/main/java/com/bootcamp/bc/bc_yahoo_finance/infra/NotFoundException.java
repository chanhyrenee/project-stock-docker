package com.bootcamp.bc.bc_yahoo_finance.infra;

public class NotFoundException extends BusinessRuntimeException {

  public NotFoundException() {
    super(SysCode.NOT_FOUND);
  }
}
