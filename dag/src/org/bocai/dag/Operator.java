/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package org.bocai.dag;

/**
 * 操作符，把左值和右值做为参数进行布尔运算，例如：age > 20
 * 
 * @author yikebocai@gmail.com Nov 21, 2012 7:09:11 PM
 */
public enum Operator {
    STRING_EQUAL("="), STRING_NOT_EQUAL("!="), INT_EQUAL("=="), INT_GREAT_THAN(">"), INT_LITTLE_THAN("<");

    private String value;

    private Operator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
