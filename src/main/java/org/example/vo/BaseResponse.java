package org.example.vo;

import lombok.Data;
import org.example.enums.ErrorCode;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -3765212815755606365L;

    Integer code;

    String msg;

//    Boolean success;

    protected T data;

    public BaseResponse(ErrorCode errorCode) {
        this.code = errorCode.getKey();
        this.msg = errorCode.getDescription();
    }

    public void changeCode(ErrorCode errorCode) {
        this.code = errorCode.getKey();
        this.msg = errorCode.getDescription();
    }

}
