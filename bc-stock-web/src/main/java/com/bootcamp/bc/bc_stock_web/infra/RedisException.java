package com.bootcamp.bc.bc_stock_web.infra;

public class RedisException extends BusinessRuntimeException {

  public RedisException() {
    super(SysCode.REDIS_ERROR);
  }
  
}
