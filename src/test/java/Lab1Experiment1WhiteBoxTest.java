import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;

/**
 * Lab1Experiment1 白盒测试类
 * 主要测试 generateNewText 函数的各种路径覆盖
 */
public class Lab1Experiment1WhiteBoxTest {
    
    private Lab1Experiment1.DirectedGraph graph;
    
    @BeforeEach
    public void setUp() {
        graph = new Lab1Experiment1.DirectedGraph();
    }
    
    // =======================================================================
    // generateNewText 函数的白盒测试用例
    // =======================================================================
    
    // 基本路径1：空输入路径
    // 路径：131 → 132 → 133 → 134 → 136(false) → 146 → 147
    // 覆盖条件：words.size() == 0
    @Test
    public void testGenerateNewText_Path1_EmptyInput() {
        // 测试数据：空字符串
        String result1 = Lab1Experiment1.generateNewText(graph, "");
        assertEquals("", result1);
        
        // 测试数据：只包含特殊字符无有效单词
        String result2 = Lab1Experiment1.generateNewText(graph, "!@#$%^&*()");
        assertEquals("", result2);
        
        // 测试数据：只包含空格和数字
        String result3 = Lab1Experiment1.generateNewText(graph, "   123 456   ");
        assertEquals("", result3);
    }
    
    // 基本路径2：单个单词路径
    // 路径：131 → 132 → 133 → 134 → 136(true) → 137 → 138(false) → 145 → 136(false) → 146 → 147
    // 覆盖条件：words.size() == 1
    @Test
    public void testGenerateNewText_Path2_SingleWord() {
        // 测试数据：只包含一个单词
        String result1 = Lab1Experiment1.generateNewText(graph, "hello");
        assertEquals("hello", result1);
        
        // 测试数据：一个单词但包含特殊字符
        String result2 = Lab1Experiment1.generateNewText(graph, "hello123!@#world456");
        assertEquals("hello world", result2);
        
        // 测试数据：大写字母会被转换为小写
        String result3 = Lab1Experiment1.generateNewText(graph, "HELLO");
        assertEquals("hello", result3);
        
        // 测试数据：单词前后有特殊字符
        String result4 = Lab1Experiment1.generateNewText(graph, "###test###");
        assertEquals("test", result4);
    }
    
    // 基本路径3：多个单词无桥接词路径
    // 路径：131 → 132 → 133 → 134 → 136(true) → 137 → 138(true) → 139 → 140 → 141(false) → 145 → 136(true) → 137 → 138(false) → 145 → 136(false) → 146 → 147
    // 覆盖条件：words.size() > 1 且 bridges.isEmpty() == true
    @Test
    public void testGenerateNewText_Path3_MultipleWordsNoBridges() {
        // 构建测试图：创建不相关的边，使得输入的相邻单词间无桥接词
        graph.addEdge("apple", "banana");
        graph.addEdge("cat", "dog");
        graph.addEdge("red", "blue");
        
        // 测试数据：多个单词但相邻单词间无桥接词
        String result = Lab1Experiment1.generateNewText(graph, "hello world test");
        assertEquals("hello world test", result);
        
        // 验证没有添加桥接词（输出应该与输入相同）
        String[] words = result.split(" ");
        assertEquals(3, words.length);
        assertEquals("hello", words[0]);
        assertEquals("world", words[1]);
        assertEquals("test", words[2]);
        
        // 测试更多单词的情况
        String result2 = Lab1Experiment1.generateNewText(graph, "one two three four");
        assertEquals("one two three four", result2);
    }
    
    // 基本路径4：多个单词有桥接词路径
    // 路径：131 → 132 → 133 → 134 → 136(true) → 137 → 138(true) → 139 → 140 → 141(true) → 142 → 143 → 145 → 136(false) → 146 → 147
    // 覆盖条件：words.size() > 1 且 bridges.isEmpty() == false
    @Test
    public void testGenerateNewText_Path4_MultipleWordsWithBridges() {
        // 构建测试图：创建桥接词
        // hello -> bridge1 -> world
        // hello -> bridge2 -> world
        graph.addEdge("hello", "bridge1");
        graph.addEdge("bridge1", "world");
        graph.addEdge("hello", "bridge2");
        graph.addEdge("bridge2", "world");
        
        // world -> connector -> test
        graph.addEdge("world", "connector");
        graph.addEdge("connector", "test");
        
        // 测试数据：多个单词且相邻单词间存在桥接词
        String result = Lab1Experiment1.generateNewText(graph, "hello world test");
        
        // 验证结果包含桥接词
        assertNotEquals("hello world test", result);
        
        // 验证结果格式正确
        String[] words = result.split(" ");
        assertTrue(words.length >= 3); // 至少包含原始的3个单词
        assertEquals("hello", words[0]);
        
        // 验证桥接词被正确插入
        // 由于随机性，我们检查可能的桥接词是否出现
        boolean hasBridge1Or2 = result.contains("bridge1") || result.contains("bridge2");
        boolean hasConnector = result.contains("connector");
        assertTrue(hasBridge1Or2 || hasConnector, "应该包含至少一个桥接词");
        
        // 验证原始单词仍然存在
        assertTrue(result.contains("hello"));
        assertTrue(result.contains("world"));
        assertTrue(result.contains("test"));
    }
    
    // 额外测试：验证桥接词选择的确定性（通过多次运行验证随机性）
    @Test
    public void testGenerateNewText_BridgeWordRandomness() {
        // 构建包含多个桥接词的图
        graph.addEdge("start", "bridge1");
        graph.addEdge("bridge1", "end");
        graph.addEdge("start", "bridge2");
        graph.addEdge("bridge2", "end");
        graph.addEdge("start", "bridge3");
        graph.addEdge("bridge3", "end");
        
        // 多次运行，收集结果
        Set<String> results = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            String result = Lab1Experiment1.generateNewText(graph, "start end");
            results.add(result);
        }
        
        // 验证确实有随机性（应该产生不同的结果）
        assertTrue(results.size() > 1, "应该产生多种不同的结果，显示随机选择桥接词");
        
        // 验证所有结果都包含原始单词
        for (String result : results) {
            assertTrue(result.contains("start"));
            assertTrue(result.contains("end"));
            assertTrue(result.length() > "start end".length());
        }
    }
}
