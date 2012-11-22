package org.bocai.dag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DagNode {

    private Condition    condition;
    private Set<Integer> children = new HashSet<Integer>();

   
    
    public Set<Integer> getChildren() {
        return children;
    }

    
    public void setChildren(Set<Integer> children) {
        this.children = children;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition node) {
        this.condition = node;
    }

    public void addChild(Integer child) { 
        children.add(child);

    }

    public void addChildren(Set<Integer> children) {
        this.children.addAll(children);

    }
  

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(condition);

        if (children != null && children.size() > 0) {
            sb.append("->(");
            Iterator itr = children.iterator();
            while (itr.hasNext()) {
                sb.append((Integer) itr.next()).append(" ");
            }
            sb.append(")");
        }
        return sb.toString();
    }

}
