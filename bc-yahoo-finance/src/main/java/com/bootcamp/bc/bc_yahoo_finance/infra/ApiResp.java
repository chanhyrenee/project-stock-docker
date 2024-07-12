package com.bootcamp.bc.bc_yahoo_finance.infra;

public class ApiResp<T> {
  private String code;
  private String msg;
  private T data;

  public String getCode() {
    return this.code;
  }

  public String getMsg() {
    return this.msg;
  }

  public T getdata() {
    return this.data;
  }

  public ApiResp(ApiRespBuilder<T> apiRespBuilder) {
    this.code = apiRespBuilder.code;
    this.msg = apiRespBuilder.msg;
    this.data = apiRespBuilder.data;
  }

  public static <T> ApiRespBuilder<T> builder() {
    return new ApiRespBuilder<>();
  }

  public static class ApiRespBuilder<T> {
    private String code;
    private String msg;
    private T data;

    public ApiRespBuilder<T> success() {
      this.code = SysCode.SUCCESS.getCode();
      this.msg = SysCode.SUCCESS.getDesc();
      return this;
    }

    public ApiRespBuilder<T> fail(SysCode syscode) {
      this.code = syscode.getCode();
      this.msg = syscode.getDesc();
      return this;
    }

    public ApiRespBuilder<T> message(String msg) {
      if (msg == null)
        throw new NullPointerException("message cannot be null.");
      this.msg = msg;
      return this;
    }

    public ApiRespBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public ApiResp<T> build() {
      return new ApiResp<>(this);
    }
  }
}
