package org.bocai.dag;

import java.util.ArrayList;
import java.util.List;

public class Forest {
	private List<DAG> graphs = new ArrayList<DAG>();

	public List<DAG> getGraphs() {
		return graphs;
	}

	public void setGraphs(List<DAG> graphs) {
		this.graphs = graphs;
	}

	public void addNode(String parent, String child) {
		//首先在已有的图中查找，是否有该节点存在，如果存在，就把子节点连接到父节点上
		boolean isFound=false;//
		for (DAG dag : graphs) {
			if (dag.found(parent)) {
				dag.attachNode(parent,child);
				isFound=true;
				break;
			} 
		}
		
		//如果找不到，新创建一个图，并添加一个新的节点进去
		if(!isFound){
			DAG dag = new DAG();
			dag.addNode(parent, child); 
			graphs.add(dag);
		}
	}

	public void merge() {
		 for(int i=1;i<graphs.size();i++){
			 DAG dag=graphs.get(i);
			 for(int j=0;j<i;j++){
				 DAG dag2=graphs.get(j);
				 dag2.merge(dag);
			 }
			 
			 if(dag.size()==0){
				 graphs.remove(i--);
			 }
		 }
		
	}

}
