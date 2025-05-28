import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab1Experiment1 黑盒测试类
 * 主要测试 queryBridgeWords 函数的各种场景
 */
public class Lab1Experiment1BlackBoxTest {
    
    private Lab1Experiment1.DirectedGraph graph;
    
    @BeforeEach
    public void setUp() {
        graph = new Lab1Experiment1.DirectedGraph();
    }
    
    // TC01: 正常情况 - 存在单个桥接词
    @Test
    public void testTC01_SingleBridge() {
        // 构建测试图: "a->b->c"
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "a", "c");
        assertEquals("The bridge words from a to c are: b.", result);
    }
    
    // TC02: 正常情况 - 存在多个桥接词
    @Test
    public void testTC02_MultipleBridges() {
        // 构建测试图: "a->b->d, a->c->d"
        graph.addEdge("a", "b");
        graph.addEdge("b", "d");
        graph.addEdge("a", "c");
        graph.addEdge("c", "d");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "a", "d");
        assertTrue(result.contains("The bridge words from a to d are:"));
        assertTrue(result.contains("b") && result.contains("c"));
    }
    
    // TC03: 大小写处理
    @Test
    public void testTC03_CaseInsensitive() {
        // 构建测试图: "hello->world->test"
        graph.addEdge("hello", "world");
        graph.addEdge("world", "test");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "HELLO", "TEST");
        assertEquals("The bridge words from hello to test are: world.", result);
    }
    
    // TC04: 无桥接词情况
    @Test
    public void testTC04_NoBridgeWords() {
        // 构建测试图: "a->b, c->d"
        graph.addEdge("a", "b");
        graph.addEdge("c", "d");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "a", "d");
        assertEquals("No bridge words from a to d!", result);
    }
    
    // TC05: 直接连接情况
    @Test
    public void testTC05_DirectConnection() {
        // 构建测试图: "start->end"
        graph.addEdge("start", "end");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "start", "end");
        assertEquals("No bridge words from start to end!", result);
    }
    
    // TC06: 相同单词
    @Test
    public void testTC06_SameWord() {
        // 构建测试图: 只有节点"word"
        graph.addNode("word");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "word", "word");
        assertEquals("No bridge words from word to word!", result);
    }
    
    // TC07: word1不存在
    @Test
    public void testTC07_Word1NotExists() {
        // 构建测试图: 只有节点"existing"
        graph.addNode("existing");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "nonexistent", "existing");
        assertEquals("No word1 or word2 in the graph!", result);
    }
    
    // TC08: word2不存在
    @Test
    public void testTC08_Word2NotExists() {
        // 构建测试图: 只有节点"existing"
        graph.addNode("existing");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "existing", "nonexistent");
        assertEquals("No word1 or word2 in the graph!", result);
    }
    
    // TC09: 两个单词都不存在
    @Test
    public void testTC09_BothWordsNotExist() {
        // 构建测试图: 只有节点"existing"
        graph.addNode("existing");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "word1", "word2");
        assertEquals("No word1 or word2 in the graph!", result);
    }
    
    // TC10: 空图测试
    @Test
    public void testTC10_EmptyGraph() {
        // 使用空图
        String result = Lab1Experiment1.queryBridgeWords(graph, "any", "word");
        assertEquals("No word1 or word2 in the graph!", result);
    }
    
    // TC11: word1为null
    @Test
    public void testTC11_Word1Null() {
        graph.addNode("test");
        
        assertThrows(NullPointerException.class, () -> {
            Lab1Experiment1.queryBridgeWords(graph, null, "test");
        });
    }
    
    // TC12: word2为null
    @Test
    public void testTC12_Word2Null() {
        graph.addNode("test");
        
        assertThrows(NullPointerException.class, () -> {
            Lab1Experiment1.queryBridgeWords(graph, "test", null);
        });
    }
    
    // TC13: 空字符串测试
    @Test
    public void testTC13_EmptyStrings() {
        graph.addNode("test");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "", "");
        assertEquals("No word1 or word2 in the graph!", result);
    }
    
    // TC14: 自环测试
    @Test
    public void testTC14_SelfLoop() {
        // 构建测试图: "start->node->end, node->node"
        graph.addEdge("start", "node");
        graph.addEdge("node", "end");
        graph.addEdge("node", "node"); // 自环
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "start", "end");
        assertEquals("The bridge words from start to end are: node.", result);
    }
    
    // TC15: 包含特殊字符的输入
    @Test
    public void testTC15_SpecialCharacters() {
        graph.addNode("normal");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "word@123", "test word");
        assertEquals("No word1 or word2 in the graph!", result);
    }
    
    // TC16: 图为null
    @Test
    public void testTC16_GraphNull() {
        assertThrows(NullPointerException.class, () -> {
            Lab1Experiment1.queryBridgeWords(null, "word1", "word2");
        });
    }
    
    // TC17: 只有节点无边
    @Test
    public void testTC17_NodesWithoutEdges() {
        // 构建只有节点无边的图
        graph.addNode("node1");
        graph.addNode("node2");
        
        String result = Lab1Experiment1.queryBridgeWords(graph, "node1", "node2");
        assertEquals("No bridge words from node1 to node2!", result);
    }
}
