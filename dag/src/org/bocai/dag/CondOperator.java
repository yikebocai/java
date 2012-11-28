/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package org.bocai.dag;

/**
 * 条件操作符，决定规则中多个条件的结果合并逻辑
 * @author yikebocai@gmail.com Nov 21, 2012 7:18:27 PM
 */
public enum CondOperator {
    AND,//所有的条件为真，才返回真
    OR; //只要有一个条件为，就返回真

}
