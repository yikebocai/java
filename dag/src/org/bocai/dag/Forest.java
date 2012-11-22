package org.bocai.dag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Forest {

    private List<DAG> graphs = new ArrayList<DAG>();

    public List<DAG> getGraphs() {
        return graphs;
    }

    public void setGraphs(List<DAG> graphs) {
        this.graphs = graphs;
    }

    // 首先在已有的图中查找，是否有该节点存在，如果存在，就把子节点连接到父节点上
    public void addNode(Condition parent, Condition child) {
        boolean isFound = false;

        if (parent != null && child != null) {
            Integer parentId = parent.getId();
            for (DAG dag : graphs) {
                if (dag.found(parentId)) { 
                    dag.attachNode(parentId, child);

                    isFound = true;
                    break;
                }
            }

            // 如果找不到，新创建一个图，并添加一个新的节点进去
            if (!isFound) {
                DAG dag = new DAG();
                dag.addNode(parent, child);
                graphs.add(dag);
            }
        }
    }

    public void merge() {
        for (int i = 1; i < graphs.size(); i++) {
            DAG dag = graphs.get(i);
            for (int j = 0; j < i; j++) {
                DAG dag2 = graphs.get(j);
                dag2.merge(dag);
            }

            if (dag.size() == 0) {
                graphs.remove(i--);
            }
        }

    }

    /**
     * 遍历森林，返回命中规则的ID列表
     * 
     * @param forest
     * @param fact
     * @return
     */
    public Set<Integer> traverse(Fact fact) {
        Set<Integer> matched = new HashSet<Integer>();

        for (DAG dag : graphs) {
            matched.addAll(dag.traverse(fact));
        }
        return matched;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graphs.size(); i++) {
            DAG dag = graphs.get(i);
            sb.append(dag.toString()).append("\n\n");

        }

        return sb.toString();
    }

}
