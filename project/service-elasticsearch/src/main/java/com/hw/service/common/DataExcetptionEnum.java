package com.hw.service.common;

import javax.xml.crypto.Data;

/**
 * Date: 2021/1/5
 *
 * @author WX964987
 */
public enum DataExcetptionEnum implements CommonStatusInterface {

    /**
     * 数据重复
     */
    DATA_IS_EXITS(70101, "数据重复");

    private int code;

    private String text;

    DataExcetptionEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }

}
