package org.bocai.dag;

import java.util.ArrayList;
import java.util.List;

public class Rule {
	private List<String> conditions=new ArrayList<String>();

	public List<String> getConditions() {
		return conditions;
	}

	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}

	public void addCondition(String condition) {
		conditions.add(condition);
	}
	
	
}
