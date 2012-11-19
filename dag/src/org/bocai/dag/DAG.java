package org.bocai.dag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * directed acyclic graph
 * 
 * @author Administrator
 * 
 */
public class DAG {
	private List<DagNode> roots = new ArrayList<DagNode>();// 根节点可能有多个
	private Map<String, DagNode> map = new HashMap<String, DagNode>();

	public List<DagNode> getRoots() {
		return roots;
	}

	public void setRoots(List<DagNode> roots) {
		this.roots = roots;
	}

	public Map<String, DagNode> getMap() {
		return map;
	}

	public void setMap(Map<String, DagNode> map) {
		this.map = map;
	}

	public void addNode(String parent, String child) {

		DagNode parentNode = new DagNode();
		parentNode.setName(parent);
		parentNode.addChild(child);
		map.put(parent, parentNode);

		DagNode childNode = new DagNode();
		childNode.setName(child);
		map.put(child, childNode);
	}

	public boolean found(String parent) {
		if (parent != null) {
			if (map.get(parent) != null)
				return true;
		}
		return false;
	}

	public void attachNode(String parent, String child) {
		if (parent != null) {
			DagNode node = map.get(parent);
			if (node != null) {
				node.addChild(child);

				if (map.get(child) == null) {
					DagNode childNode = new DagNode();
					childNode.setName(child);
					map.put(child, childNode);
				}
			}
		}

	}

	public void merge(DAG dag) {
		if (dag != null) {
			Iterator itr = dag.getMap().entrySet().iterator();
			while (itr.hasNext()) {
				Entry entry = (Entry) itr.next();
				String name = (String) entry.getKey();
				DagNode node = (DagNode) entry.getValue();

				if (found(name)) {
					attachNode(name, node.getChildren());
					dag.remove(name);
				}
			}
		}

	}

	private void remove(String name) {
		map.remove(name);

	}

	private void attachNode(String parent, Set<String> children) {
		if (parent != null && children != null) {
			DagNode node = map.get(parent);
			if (node != null) {
				Iterator itr = children.iterator();
				while (itr.hasNext()) {
					node.addChild((String) itr.next());
				}
			}
		}
	}

	public int size() {

		return map.size();
	}

}
