package com.bootcamp.bc.bc_yahoo_finance.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Protocol {
  HTTPS("https"), //
  HTTP("http"), //
  SSH("ssh"),;

  private String protocal;

}
