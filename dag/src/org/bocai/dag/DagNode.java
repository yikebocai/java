package org.bocai.dag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DagNode {
	private Set<String> children=new HashSet<String>();
	private String name;

	 
	public Set<String> getChildren() {
		return children;
	}

	public void setChildren(Set<String> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addChild(String child) { 
		children.add(child);
		
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append(name).append("->(");
		Iterator itr=children.iterator();
		while(itr.hasNext()){
			sb.append((String)itr.next()).append(" ");
		}
		sb.append(")");
		return sb.toString();
	}

}
