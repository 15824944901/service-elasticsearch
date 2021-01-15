package com.hw.service.exception;

import com.hw.service.common.CommonStatusInterface;
import com.hw.service.common.DataExcetptionEnum;

/**
 * 异常处理类
 * Date: 2020/12/22
 * @author WX964987
 */
public class ServiceException extends RuntimeException {


    private int code;

    public ServiceException(int code) {
        this.code = code;
    }

    /**
     * 构造方法
     *
     * @param status 状态码信息
     */
    public ServiceException(CommonStatusInterface status) {
        super(status.getText());
        this.code = status.getCode();
    }

    public int getCode() {
        return code;
    }
}
