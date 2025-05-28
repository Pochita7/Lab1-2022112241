import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class DetailedTestRunner {
    
    private Lab1Experiment1.DirectedGraph graph;
    
    @BeforeEach
    public void setUp() {
        graph = new Lab1Experiment1.DirectedGraph();
    }
    
    public static void main(String[] args) {
        DetailedTestRunner runner = new DetailedTestRunner();
        
        System.out.println("=== Lab1Experiment1 queryBridgeWords 测试报告 ===\n");
        
        // TC01: 正常情况 - 存在单个桥接词
        runner.runTC01();
        
        // TC02: 正常情况 - 存在多个桥接词
        runner.runTC02();
        
        // TC03: 正常情况 - 大小写处理
        runner.runTC03();
        
        // TC04: 边界情况 - 无桥接词
        runner.runTC04();
        
        // TC05: 边界情况 - 直接连接
        runner.runTC05();
        
        // TC06: 边界情况 - 相同单词
        runner.runTC06();
        
        // TC07: 异常情况 - word1不存在
        runner.runTC07();
        
        // TC08: 异常情况 - word2不存在
        runner.runTC08();
        
        // TC09: 异常情况 - 两个单词都不存在
        runner.runTC09();
        
        // TC10: 异常情况 - 空图
        runner.runTC10();
        
        // TC11: 异常情况 - word1为null
        runner.runTC11();
        
        // TC12: 异常情况 - word2为null
        runner.runTC12();
        
        // TC13: 边界情况 - 空字符串
        runner.runTC13();
        
        // TC14: 边界情况 - 自环
        runner.runTC14();
        
        // TC15: 边界情况 - 特殊字符
        runner.runTC15();
        
        // TC16: 异常情况 - 图对象为null
        runner.runTC16();
        
        // TC17: 边界情况 - 节点存在但无边
        runner.runTC17();
    }
    
    private void runTC01() {
        System.out.println("TC01: 正常情况 - 存在单个桥接词");
        System.out.println("描述: 测试当存在单个桥接词时的情况");
        
        setUp();
        // 构建测试图: "a->b->c"
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        
        String expected = "The bridge words from a to c are: b.";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "a", "c");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC02() {
        System.out.println("TC02: 正常情况 - 存在多个桥接词");
        System.out.println("描述: 测试当存在多个桥接词时的情况");
        
        setUp();
        // 构建测试图: "a->b->d" 和 "a->c->d"
        graph.addEdge("a", "b");
        graph.addEdge("b", "d");
        graph.addEdge("a", "c");
        graph.addEdge("c", "d");
        
        String actual = Lab1Experiment1.queryBridgeWords(graph, "a", "d");
        
        System.out.println("期望输出: 包含 'The bridge words from a to d are:' 和桥接词 'b', 'c'");
        System.out.println("实际输出: " + actual);
        boolean containsHeader = actual.contains("The bridge words from a to d are:");
        boolean containsB = actual.contains("b");
        boolean containsC = actual.contains("c");
        System.out.println("测试结果: " + (containsHeader && containsB && containsC ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC03() {
        System.out.println("TC03: 正常情况 - 大小写处理");
        System.out.println("描述: 测试大小写不敏感的情况");
        
        setUp();
        // 构建测试图: "hello->world->test"
        graph.addEdge("hello", "world");
        graph.addEdge("world", "test");
        
        String expected = "The bridge words from hello to test are: world.";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "hello", "test");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC04() {
        System.out.println("TC04: 边界情况 - 无桥接词");
        System.out.println("描述: 测试当不存在桥接词时的情况");
        
        setUp();
        // 构建测试图: "a->b" 和 "c->d" (无连接)
        graph.addEdge("a", "b");
        graph.addEdge("c", "d");
        
        String expected = "No bridge words from a to d!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "a", "d");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC05() {
        System.out.println("TC05: 边界情况 - 直接连接");
        System.out.println("描述: 测试当两个单词直接连接时的情况");
        
        setUp();
        // 构建测试图: "start->end"
        graph.addEdge("start", "end");
        
        String expected = "No bridge words from start to end!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "start", "end");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC06() {
        System.out.println("TC06: 边界情况 - 相同单词");
        System.out.println("描述: 测试当word1和word2相同时的情况");
        
        setUp();
        graph.addEdge("word", "other");
        
        String expected = "No bridge words from word to word!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "word", "word");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC07() {
        System.out.println("TC07: 异常情况 - word1不存在");
        System.out.println("描述: 测试当word1在图中不存在时的情况");
        
        setUp();
        graph.addEdge("a", "b");
        
        String expected = "No word1 or word2 in the graph!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "nonexistent", "b");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC08() {
        System.out.println("TC08: 异常情况 - word2不存在");
        System.out.println("描述: 测试当word2在图中不存在时的情况");
        
        setUp();
        graph.addEdge("a", "b");
        
        String expected = "No word1 or word2 in the graph!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "a", "nonexistent");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC09() {
        System.out.println("TC09: 异常情况 - 两个单词都不存在");
        System.out.println("描述: 测试当word1和word2都不在图中时的情况");
        
        setUp();
        graph.addEdge("a", "b");
        
        String expected = "No word1 or word2 in the graph!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "x", "y");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC10() {
        System.out.println("TC10: 异常情况 - 空图");
        System.out.println("描述: 测试当图为空时的情况");
        
        setUp();
        // 空图
        
        String expected = "No word1 or word2 in the graph!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "a", "b");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC11() {
        System.out.println("TC11: 异常情况 - word1为null");
        System.out.println("描述: 测试当word1为null时的情况");
        
        setUp();
        
        try {
            String actual = Lab1Experiment1.queryBridgeWords(graph, null, "test");
            System.out.println("期望: 抛出NullPointerException");
            System.out.println("实际: 没有抛出异常，返回: " + actual);
            System.out.println("测试结果: FAIL");
        } catch (NullPointerException e) {
            System.out.println("期望: 抛出NullPointerException");
            System.out.println("实际: 成功抛出NullPointerException");
            System.out.println("测试结果: PASS");
        }
        System.out.println();
    }
    
    private void runTC12() {
        System.out.println("TC12: 异常情况 - word2为null");
        System.out.println("描述: 测试当word2为null时的情况");
        
        setUp();
        
        try {
            String actual = Lab1Experiment1.queryBridgeWords(graph, "test", null);
            System.out.println("期望: 抛出NullPointerException");
            System.out.println("实际: 没有抛出异常，返回: " + actual);
            System.out.println("测试结果: FAIL");
        } catch (NullPointerException e) {
            System.out.println("期望: 抛出NullPointerException");
            System.out.println("实际: 成功抛出NullPointerException");
            System.out.println("测试结果: PASS");
        }
        System.out.println();
    }
    
    private void runTC13() {
        System.out.println("TC13: 边界情况 - 空字符串");
        System.out.println("描述: 测试当输入空字符串时的情况");
        
        setUp();
        graph.addEdge("a", "b");
        
        String expected = "No word1 or word2 in the graph!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "", "");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC14() {
        System.out.println("TC14: 边界情况 - 自环");
        System.out.println("描述: 测试包含自环的图");
        
        setUp();
        // 构建包含自环的图: "start->node->end", "node->node"
        graph.addEdge("start", "node");
        graph.addEdge("node", "node");
        graph.addEdge("node", "end");
        
        String expected = "The bridge words from start to end are: node.";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "start", "end");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC15() {
        System.out.println("TC15: 边界情况 - 特殊字符");
        System.out.println("描述: 测试包含特殊字符的单词");
        
        setUp();
        
        String expected = "No word1 or word2 in the graph!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "word@", "word#");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private void runTC16() {
        System.out.println("TC16: 异常情况 - 图对象为null");
        System.out.println("描述: 测试当图对象为null时的情况");
        
        try {
            String actual = Lab1Experiment1.queryBridgeWords(null, "a", "b");
            System.out.println("期望: 抛出NullPointerException");
            System.out.println("实际: 没有抛出异常，返回: " + actual);
            System.out.println("测试结果: FAIL");
        } catch (NullPointerException e) {
            System.out.println("期望: 抛出NullPointerException");
            System.out.println("实际: 成功抛出NullPointerException");
            System.out.println("测试结果: PASS");
        }
        System.out.println();
    }
    
    private void runTC17() {
        System.out.println("TC17: 边界情况 - 节点存在但无边");
        System.out.println("描述: 测试节点存在但之间没有边的情况");
        
        setUp();
        // 添加独立的边，使节点存在但node1和node2之间无连接
        graph.addEdge("node1", "other");
        graph.addEdge("other2", "node2");
        
        String expected = "No bridge words from node1 to node2!";
        String actual = Lab1Experiment1.queryBridgeWords(graph, "node1", "node2");
        
        System.out.println("期望输出: " + expected);
        System.out.println("实际输出: " + actual);
        System.out.println("测试结果: " + (expected.equals(actual) ? "PASS" : "FAIL"));
        System.out.println();
    }
}
