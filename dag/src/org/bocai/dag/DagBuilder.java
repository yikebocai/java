package org.bocai.dag;

import java.util.List;
import java.util.Map;

/**
 * 构建有向无环图
 * @author bocai
 *
 */
public class DagBuilder {
	public static Forest build(List<Rule> rules){
		Forest forest=new Forest();
	
		
		for(Rule rule:rules){
			List<String> conditions=rule.getConditions();
			for(int i=0;i<conditions.size();i++){
				String child=null;
				if(i+1<conditions.size()){
					child=conditions.get(i+1);
				}
				
				forest.addNode(conditions.get(i),child); 
			}
		}
		
		forest.merge();
		
		
		return forest;
	}
	
	public static Map<String,Integer> traverse(Forest forest,Fact fact){
		
		return null;
	}
}
