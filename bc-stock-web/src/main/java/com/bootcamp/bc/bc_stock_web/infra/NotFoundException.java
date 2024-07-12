package com.bootcamp.bc.bc_stock_web.infra;

public class NotFoundException extends BusinessRuntimeException {

  public NotFoundException() {
    super(SysCode.NOT_FOUND);
  }
}
