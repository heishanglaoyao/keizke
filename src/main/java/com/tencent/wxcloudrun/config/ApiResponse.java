package com.tencent.wxcloudrun.config;

import lombok.Data;

import java.util.HashMap;

@Data
public final class ApiResponse<T> {

  private Integer code;
  private String errorMsg;
  private T data;

  private ApiResponse(int code, String errorMsg, T data) {
    this.code = code;
    this.errorMsg = errorMsg;
    this.data = data;
  }
  
  public static ApiResponse ok() {
    return new ApiResponse(0, "", new HashMap<>());
  }

  public static ApiResponse ok(Object data) {
    return new ApiResponse(0, "", data);
  }

  public static ApiResponse error(String errorMsg) {
    return new ApiResponse(0, errorMsg, new HashMap<>());
  }
}
