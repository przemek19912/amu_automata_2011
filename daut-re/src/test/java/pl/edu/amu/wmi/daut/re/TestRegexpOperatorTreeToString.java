/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.re;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author wintron
 */
public class TestRegexpOperatorTreeToString extends TestCase {
    
    /**
     * Test.
     */
    public void test1() {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new KleeneStarOperator();
        RegexpOperator s2 = new WhitespaceOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorManager manager = new RegexpOperatorManager();
        manager.addOperator("*", new KleeneStarOperator.Factory(), Arrays.<String>asList("", "*"));
        manager.addOperator(" ", new WhitespaceOperator.Factory(), Arrays.<String>asList(" "));
        manager.addOperator("|", new AlternativeOperator.Factory(), Arrays.<String>asList("|"));
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(RegexpOperatorTreeToString(tree, manager), "*");
    }
    
    public void test2() {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new SingleCharacterOperator('b');
        RegexpOperator s2 = new AnyCharOperator();
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorManager manager = new RegexpOperatorManager();
        manager.addOperator(".", new AnyCharOperator.Factory(), Arrays.<String>asList("."));
        manager.addOperator("b", new SingleCharacterOperator.Factory(), Arrays.<String>asList("a"));
        manager.addOperator("|", new AlternativeOperator.Factory(), Arrays.<String>asList("|"));
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(RegexpOperatorTreeToString(tree, manager), "b.");
    }
    
    public void test3() {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new  AlternativeOperator();
        RegexpOperator s1 = new KleeneStarOperator();
        RegexpOperator s2 = new CharClassOperator("a-b");
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorManager manager = new RegexpOperatorManager();
        manager.addOperator("[a-b]", new CharClassOperator.Factory(), Arrays.<String>asList("[a-b]"));
        manager.addOperator("|", new AlternativeOperator.Factory(), Arrays.<String>asList("|"));
        manager.addOperator("*", new KleeneStarOperator.Factory(), Arrays.<String>asList("*"));
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(RegexpOperatorTreeToString(tree, manager, "[a-b]");
    }
    
     public void test4() {
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperator root = new AlternativeOperator();
        RegexpOperator s1 = new NumericalRangeOperator(0,9);
        RegexpOperator s2 = new SingleCharacterOperator('b');
        RegexpOperatorTree tree0 = new RegexpOperatorTree(s1, subtrees);
        RegexpOperatorTree tree1 = new RegexpOperatorTree(s2, subtrees);
        subtrees.add(tree0);
        subtrees.add(tree1);
        RegexpOperatorManager manager = new RegexpOperatorManager();
        manager.addOperator("[0-9]", new NumericalRangeOperator.Factory(), Arrays.<String>asList("[0-9]"));
        manager.addOperator("b", new SingleCharacterOperator.Factory(), Arrays.<String>asList("b"));
        manager.addOperator("|", new AlternativeOperator.Factory(), Arrays.<String>asList("|"));
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);
        assertEquals(RegexpOperatorTreeToString(tree, manager), "[0-9]");
    }
}
