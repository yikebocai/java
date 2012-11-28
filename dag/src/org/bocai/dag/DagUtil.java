package org.bocai.dag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 构建有向无环图
 * 
 * @author yikebocai@gmail.com
 */
public class DagUtil {

    /**
     * 根据命中的条件，得到匹配的规则列表
     * 
     * @param rules
     * @param conds
     * @return
     */
    public static List<Rule> matchRule(List<Rule> rules, Set<Integer> conds) {
        List<Rule> matched = new ArrayList<Rule>();

        for (Rule rule : rules) {
            if (rule.isTrue(conds)) {
                matched.add(rule);
            }
        }
        return matched;
    }

}
