package com.bootcamp.bc.bc_yahoo_finance.infra;

public class RedisException extends BusinessRuntimeException {

  public RedisException() {
    super(SysCode.REDIS_ERROR);
  }
}
