package org.bocai.dag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DAGTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Fact fact = new Fact();
        fact.setAmount("501");
        fact.setCountry("CN");
        fact.setMemberType("IFM");
        fact.setOs("Linux");
        fact.setLogin_count_today("10");

        Condition c1 = new Condition(1, "country", Operator.STRING_EQUAL, "CN");
        Condition c2 = new Condition(2, "amount", Operator.INT_GREAT_THAN, "500");
        Condition c3 = new Condition(3, "memberType", Operator.STRING_NOT_EQUAL, "CGS");
        Condition c4 = new Condition(4, "email", Operator.STRING_EQUAL, "abc@163.com");
        Condition c5 = new Condition(5, "cookie", Operator.STRING_EQUAL, "123456");
        Condition c6 = new Condition(6, "os", Operator.STRING_NOT_EQUAL, "windows");
        Condition c7 = new Condition(7, "login_count_today", Operator.INT_LITTLE_THAN, "10");
        Condition c8 = new Condition(8, "country", Operator.STRING_EQUAL, "US");
        Condition c9 = new Condition(9, "country", Operator.STRING_EQUAL, "JP");


        Rule rule1 = new Rule();
        rule1.addCondition(c1);
        rule1.addCondition(c2);
        rule1.addCondition(c3);
        rule1.setCondOperator(CondOperator.AND);
        rule1.setId(11);
        rule1.setName("rule1");

        Rule rule2 = new Rule();
        rule2.addCondition(c1);
        rule2.addCondition(c2);
        rule2.addCondition(c4);
        rule2.setCondOperator(CondOperator.AND);
        rule2.setId(12);
        rule2.setName("rule2");
        
        Rule rule3 = new Rule();
        rule3.addCondition(c1);
        rule3.addCondition(c2);
        rule3.addCondition(c4);
        rule3.addCondition(c5);
        rule3.setCondOperator(CondOperator.AND);
        rule3.setId(13);
        rule3.setName("rule3");
        
        Rule rule4 = new Rule();
        rule4.addCondition(c6);
        rule4.addCondition(c7);
        rule4.addCondition(c8);
        rule4.addCondition(c9);
        rule4.setCondOperator(CondOperator.OR);
        rule4.setId(14);
        rule4.setName("rule4");

        List<Rule> rules = new ArrayList<Rule>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);
        rules.add(rule4);

        DAG dag=new DAG(rules); 
        System.out.println(dag);
        Set<Integer> conds = dag.traverse(fact);
        List<Rule> matched = DagUtil.matchRule(rules, conds);
        System.out.println("matched rule:");
        for (Rule rule : matched) {
            System.out.println(rule.getId() + ":" + rule.getName());
        }
    }

}
