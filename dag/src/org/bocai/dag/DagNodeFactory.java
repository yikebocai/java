/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package org.bocai.dag;

/**
 * 根据操作符类型来决定创建什么的数据对象，比如字符串相等可以用Hash子类，整形比较用Binary子类
 * 
 * @author yikebocai@gmail.com Nov 28, 2012 1:13:59 AM
 */
public class DagNodeFactory {

    public static DagNode create(Operator operator) {
        switch (operator) {
            case STRING_EQUAL:
                // case STRING_NOT_EQUAL:
                // case INT_EQUAL:
                return new HashDagNode();
//            case INT_GREAT_THAN:
//            case INT_LITTLE_THAN:
//                return new BinaryDagNode();
            default:
                return new DagNode();
        }
    }

}
