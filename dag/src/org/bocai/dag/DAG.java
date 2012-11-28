package org.bocai.dag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * directed acyclic graph
 * 
 * @author yikebocai@gmail.com
 */
public class DAG {

    private Map<Integer, DagNode> map = new HashMap<Integer, DagNode>();

    public DAG(List<Rule> rules) {
        // 添加一个虚拟根节点，避免构建多个图
        DagNode dummyRootNode = new DagNode();
        Condition cond = new Condition(0, "1", Operator.STRING_EQUAL, "1");
        dummyRootNode.setCondition(cond);
        map.put(0, dummyRootNode);

        // 构建有向无环图
        for (Rule rule : rules) {
            List<Condition> conditions = rule.getConditions();
            for (int i = 0; i < conditions.size(); i++) {
                Condition parent = conditions.get(i);
                switch (rule.getCondOperator()) {
                    case AND:
                        Condition child = null;
                        if (i + 1 < conditions.size()) {
                            child = conditions.get(i + 1);
                        }

                        addNode(parent, child);
                        break;
                    case OR:
                        addNode(parent, null);
                }

            }
        }
    }

    public Map<Integer, DagNode> getMap() {
        return map;
    }

    public void setMap(Map<Integer, DagNode> map) {
        this.map = map;
    }

    private DagNode getRootNode() {
        return map.get(0);
    }

    public void addNode(Condition parent, Condition child) {
        // 至少要有一个条件
        if (parent != null) {
            DagNode parentNode = map.get(parent.getId());

            // 构建节点，建立父子关系
            if (parentNode == null) {
                parentNode = new DagNode();
                parentNode.setCondition(parent);

                // 如果parent没有父节点，把它放到虚假根节点下面
                DagNode rootNode = getRootNode();
                rootNode.addChild(parent.getId());
                map.put(parent.getId(), parentNode);
            }

            if (child != null) {
                parentNode.addChild(child.getId());

                DagNode childNode = map.get(child.getId());
                if (childNode == null) {
                    childNode = new DagNode();
                    childNode.setCondition(child);
                }
                childNode.addParent(parent.getId());
                map.put(child.getId(), childNode);
            }

        }
    }

    public boolean found(Integer id) {
        if (id != null) {
            if (map.get(id) != null) return true;
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
                    }
                }
            }
        }

    }

    /**
     * 遍历图，找到所有匹配的节点，从根节点开始，深度遍历，如果有不匹配的节点即可回溯
     * 
     * @param fact
     * @return
     */
    public Set<Integer> traverse(Fact fact) {
        Set<Integer> matched = new HashSet<Integer>();

        traverse2(matched, 0, fact);

        return matched;
    }

    private void traverse2(Set<Integer> matched, Integer id, Fact fact) {
        DagNode node = map.get(id);

        if (id == 0 || node != null && node.getCondition().match(fact)) {

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
}
