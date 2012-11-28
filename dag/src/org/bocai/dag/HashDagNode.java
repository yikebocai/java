/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package org.bocai.dag;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 把多个相似的条件（左值和操作符相等）进行合并，使用Hash来快速判断。比如有三个条件分别为 (a = "a"),(a = "b"),(a =
 * "c"),把右值放入Hash表，来一个Fact(a="b")时，一次查询就能判断出三个条件是否成立
 * 
 * @author yikebocai@gmail.com Nov 27, 2012 11:38:37 PM
 */
public class HashDagNode extends DagNode {

    private Map<String, Set<Integer>> map = new HashMap<String, Set<Integer>>();

    public void addCondition(Condition cond) {
        if (cond != null) {
            super.addCondition(cond);

            String right = cond.getRight();
            if (right != null) {
                Set<Integer> ids = map.get(right);
                if (ids == null) {
                    ids = new HashSet<Integer>();
                }
                ids.add(cond.getId());

                map.put(right, ids);
            }
        }
    }

    public boolean match(Fact fact) {
        if (fact != null) {
            String left = getLeft();
            if (left != null) {
                try {
                    Field declaredField = fact.getClass().getDeclaredField(left);
                    declaredField.setAccessible(true);
                    String value = (String) declaredField.get(fact);

                    Set<Integer> ids = map.get(value);
                    if (ids != null && ids.size() > 0) {
                        addMatched(ids);
                        return true;
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
        }

        return false;
    }

}
