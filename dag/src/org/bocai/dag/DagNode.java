package org.bocai.dag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 图中的节点，左值和操作符相等的条件放在一节点中，做优化处理
 * 
 * @author yikebocai@gmail.com Nov 27, 2012 5:53:15 PM
 */
public class DagNode {

    private Set<Condition> conditions = new HashSet<Condition>();
    private Set<String>    parents    = new HashSet<String>();
    private Set<String>    children   = new HashSet<String>();
    private Set<Integer>   matched    = new HashSet<Integer>();

    public Set<String> getChildren() {
        return children;
    }

    public void addCondition(Condition cond) {
        conditions.add(cond);
    }

    public void addParent(String parent) {
        parents.add(parent);

    }

    public void addChild(String child) {
        children.add(child);

    }

    public void addChildren(Set<String> children) {
        this.children.addAll(children);

    }

    public Set<Condition> getConditions() {
        return conditions;
    }

    public Set<Integer> getMatched() {
        return matched;
    }

    public void addMatched(Integer condId) {
        matched.add(condId);
    }

    public void addMatched(Set<Integer> condIds) {
        matched.addAll(condIds);
    }

    public String getKey() {
        if (conditions != null && conditions.size() > 0) {
            Iterator itr = conditions.iterator();
            if (itr.hasNext()) {
                Condition cond = (Condition) itr.next();
                return cond.getPrefix();
            }
        }

        return DagUtil.DUMMY_ROOT_KEY;
    }

    public String getLeft() {
        if (conditions != null && conditions.size() > 0) {
            Iterator itr = conditions.iterator();
            if (itr.hasNext()) {
                Condition cond = (Condition) itr.next();
                return cond.getLeft();
            }
        }

        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (children != null && children.size() > 0) {
            sb.append("(");
            Iterator itr2 = children.iterator();
            while (itr2.hasNext()) {
                sb.append((String) itr2.next()).append(" ");
            }
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * 默认把所有条件都扫一遍
     * 
     * @param fact
     * @return
     */
    public boolean match(Fact fact) {
        boolean matched = false;

        Iterator itr = conditions.iterator();
        while (itr.hasNext()) {
            Condition condition = (Condition) itr.next();
            if (condition.match(fact)) {
                addMatched(condition.getId());
                matched = true;
            }
        }

        return matched;
    }

}
