package com.bootcamp.bc.bc_yahoo_finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Interval {
  MINS_5("5 Minutes"), D("Daily"), W("Weekly"), M("Monthly");

  private String desc;

}
