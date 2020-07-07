package com.meng.user.web.entity;

import com.meng.user.common.enums.ApiResponseEnum;
import lombok.Data;

/**
 * web层统一返回类型
 *
 * @author dujianwei
 * @create 2020-07-06
 */
@Data
public class ApiResponse {

    private int errCode = 0;

    private String errMsg;

    private Object data;

    public ApiResponse(){
    }

    public ApiResponse(Object data) {
        this.data = data;
    }

    public ApiResponse(ApiResponseEnum apiResponseEnum){
        this.errCode = apiResponseEnum.getErrCode();
        this.errMsg = apiResponseEnum.getErrMsg();
    }

}
