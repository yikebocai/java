package org.bocai.dag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * directed acyclic graph
 * 
 * @author yikebocai@gmail.com
 */
public class DAG {

    private Set<Integer>          roots = new HashSet<Integer>();         // 根节点可能有多个
    private Map<Integer, DagNode> map   = new HashMap<Integer, DagNode>();

    public Set<Integer> getRoots() {
        return roots;
    }

    public void setRoots(Set<Integer> roots) {
        this.roots = roots;
    }

    public Map<Integer, DagNode> getMap() {
        return map;
    }

    public void setMap(Map<Integer, DagNode> map) {
        this.map = map;
    }

    public void addNode(Condition parent, Condition child) {

        DagNode parentNode = new DagNode();
        parentNode.setCondition(parent);
        parentNode.addChild(child.getId());
        map.put(parent.getId(), parentNode);

        // 如果没有父节点，把它放到roots列表中
        roots.add(parent.getId());

        DagNode childNode = new DagNode();
        childNode.setCondition(child);
        map.put(child.getId(), childNode);

        // 如果有父节点，把它从roots中删除
        roots.remove(child.getId());
    }

    public boolean found(Integer parentId) {
        if (parentId != null) {
            if (map.get(parentId) != null) return true;
        }
        return false;
    }

    public void attachNode(Integer parentId, Condition child) {
        Set<Condition> children = new HashSet<Condition>();
        children.add(child);
        attachNode(parentId, children);
    }

    public void attachNode(Integer parentId, Set<Condition> children) {
        if (parentId != null) {
            DagNode node = map.get(parentId);
            if (node != null) {

                Iterator itr = children.iterator();
                while (itr.hasNext()) {
                    Condition child = (Condition) itr.next();
                    if (child != null) {
                        Integer id = child.getId();
                        node.addChild(id);
                        if (map.get(id) == null) {
                            DagNode childNode = new DagNode();
                            childNode.setCondition(child);
                            map.put(id, childNode);
                        }

                        // 如果有父节点，把它从roots中删除
                        roots.remove(id);
                    }
                }
            }
        }

    }

    public void merge(DAG dag) {
        if (dag != null) {
            Iterator itr = dag.getMap().entrySet().iterator();
            while (itr.hasNext()) {
                Entry entry = (Entry) itr.next();
                Integer id = (Integer) entry.getKey();
                DagNode node = (DagNode) entry.getValue();

                // 如果能在新的图中找到，则把这个节点合并到新的图中，并删除它及它的子节点在老图中的存储
                if (found(id)) {
                    Iterator itr2 = node.getChildren().iterator();
                    while (itr2.hasNext()) {
                        Integer childId = (Integer) itr2.next();
                        attachNode(id, dag.getMap().get(childId).getCondition());
                        dag.remove(childId);
                    }

                    dag.remove(id);
                }
            }
        }

    }

    private void remove(Integer condId) {
        map.remove(condId);

    }

    public int size() {

        return map.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Entry entry = (Entry) itr.next();
            Integer condId = (Integer) entry.getKey();
            DagNode node = (DagNode) entry.getValue();
            sb.append(node.getCondition()).append("->").append(node.getChildren()).append("\n");
        }

        return sb.toString();
    }

    /**
     * 遍历图，找到所有匹配的节点，从根节点开始，深度遍历，如果有不匹配的节点即可回溯
     * 
     * @param fact
     * @return
     */
    public Set<Integer> traverse(Fact fact) {
        Set<Integer> matched = new HashSet<Integer>();

        Iterator itr = roots.iterator();
        while (itr.hasNext()) {
            Integer next = (Integer) itr.next();
            traverse2(matched, next,fact);
        }
        return matched;
    }

    /**
     * @param matched
     * @param itr
     */
    private void traverse2(Set<Integer> matched, Integer id,Fact fact) {
        DagNode node = map.get(id);

        if (node != null && node.getCondition().match(fact)) {
            
            matched.add(id);

            Set<Integer> children = node.getChildren();
            if (children != null && children.size() > 0) {
                Iterator itr = children.iterator();
                while (itr.hasNext()) {
                    // 递归
                    Integer next = (Integer) itr.next();
                    if (next != null) {
                        traverse2(matched, next, fact);
                    }
                }
            }
        }
    }
}
