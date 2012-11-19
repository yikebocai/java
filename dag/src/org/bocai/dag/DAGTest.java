package org.bocai.dag;

import java.util.ArrayList;
import java.util.List;

public class DAGTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fact fact=new Fact();
		fact.setField1("C1");
		fact.setField2("C2");
		fact.setField3("C4");
		
		Rule rule1=new Rule();
		rule1.addCondition("C1");
		rule1.addCondition("C2");
		rule1.addCondition("C3");
		
		Rule rule2=new Rule();
		rule2.addCondition("C1");
		rule2.addCondition("C2");
		rule2.addCondition("C4");
		
		Rule rule3=new Rule();
		rule3.addCondition("C1");
		rule3.addCondition("C2");
		rule3.addCondition("C4");
		rule3.addCondition("C5");
		List<Rule> rules=new ArrayList<Rule>();
		rules.add(rule1);
		rules.add(rule2);
		rules.add(rule3);
		
		Forest forest=DagBuilder.build(rules);
		System.out.println(forest);

	}

}
