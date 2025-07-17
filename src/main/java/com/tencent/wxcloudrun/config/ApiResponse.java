package com.tencent.wxcloudrun.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

@Data
public final class ApiResponse<T> {

  private Integer code;
  private String message;
  private T data;

  private ApiResponse(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
  
  public static ApiResponse ok() {
    return new ApiResponse(0, "", new HashMap<>());
  }

  public static ApiResponse ok(Object data) {
    return new ApiResponse(0, "", data);
  }

  public static ApiResponse error(String errorMsg) {
    return new ApiResponse(1, errorMsg, new HashMap<>());
  }

  public static ApiResponse ok4ErrMsg(String errorMsg) {
    if(StringUtils.isEmpty(errorMsg)){
      return ok();
    }
    return error(errorMsg);
  }
}
