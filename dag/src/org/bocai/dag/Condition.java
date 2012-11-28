/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package org.bocai.dag;

import java.lang.reflect.Field;

/**
 * 条件，是指一个布尔表达式，规则的最小组成单元
 * 
 * @author yikebocai@gmail.com Nov 21, 2012 7:04:30 PM
 */
public class Condition {

    private Integer  id;
    private String   left;
    private String   right;
    private Operator operator;

    public Condition(Integer id, String left, Operator operator, String right) {
        this.id = id;
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
 
    public String getPrefix(){
        return left+"-"+operator.name();
    }
    
    public String toString() {
        return "(" + id + ":" + left + " " + operator.getValue() + " " + right + ")";
    }

    /**
     * @param fact
     * @return
     */
    public boolean match(Fact fact) {
        if (fact != null) {
            try {
                Field declaredField = fact.getClass().getDeclaredField(left);
                declaredField.setAccessible(true);
                String value = (String) declaredField.get(fact);

                switch (operator) {
                    case STRING_EQUAL:
                        return value != null && value.equals(right);
                    case STRING_NOT_EQUAL:
                        return value != null && !value.equals(right);
                    case INT_EQUAL:
                        return value != null && Integer.parseInt(value) == Integer.parseInt(right);
                    case INT_GREAT_THAN:
                        return value != null && Integer.parseInt(value) > Integer.parseInt(right);
                    case INT_LITTLE_THAN:
                        return value != null && Integer.parseInt(value) < Integer.parseInt(right);
                    default:
                        return false;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
