package org.bocai.dag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 构建有向无环图
 * 
 * @author bocai
 */
public class DagUtil {

    public static Forest build(List<Rule> rules) {
        Forest forest = new Forest();

        for (Rule rule : rules) {
            List<Condition> conditions = rule.getConditions();
            for (int i = 0; i < conditions.size(); i++) {
                Condition child = null;
                if (i + 1 < conditions.size()) {
                    child = conditions.get(i + 1);
                }

                Condition parent = conditions.get(i);
                forest.addNode(parent, child);
            }
        }

        forest.merge();

        return forest;
    }

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
