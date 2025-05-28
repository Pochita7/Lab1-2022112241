public class SimpleDetailedTest {
    public static void main(String[] args) {
        System.out.println("=== Lab1Experiment1 queryBridgeWords 测试用例的期望输出和实际输出 ===\n");
        
        // TC01测试
        System.out.println("TC01: 正常情况 - 存在单个桥接词");
        System.out.println("图结构: a->b->c");
        System.out.println("查询: queryBridgeWords(graph, \"a\", \"c\")");
        System.out.println("期望输出: \"The bridge words from a to c are: b.\"");
        
        Lab1Experiment1.DirectedGraph graph1 = new Lab1Experiment1.DirectedGraph();
        graph1.addEdge("a", "b");
        graph1.addEdge("b", "c");
        String result1 = Lab1Experiment1.queryBridgeWords(graph1, "a", "c");
        System.out.println("实际输出: \"" + result1 + "\"");
        System.out.println("结果: " + (result1.equals("The bridge words from a to c are: b.") ? "PASS" : "FAIL"));
        System.out.println();
        
        // TC02测试
        System.out.println("TC02: 正常情况 - 存在多个桥接词");
        System.out.println("图结构: a->b->d, a->c->d");
        System.out.println("查询: queryBridgeWords(graph, \"a\", \"d\")");
        System.out.println("期望输出: 包含 \"The bridge words from a to d are:\" 和桥接词 \"b\", \"c\"");
        
        Lab1Experiment1.DirectedGraph graph2 = new Lab1Experiment1.DirectedGraph();
        graph2.addEdge("a", "b");
        graph2.addEdge("b", "d");
        graph2.addEdge("a", "c");
        graph2.addEdge("c", "d");
        String result2 = Lab1Experiment1.queryBridgeWords(graph2, "a", "d");
        System.out.println("实际输出: \"" + result2 + "\"");
        boolean pass2 = result2.contains("The bridge words from a to d are:") && result2.contains("b") && result2.contains("c");
        System.out.println("结果: " + (pass2 ? "PASS" : "FAIL"));
        System.out.println();
        
        // TC04测试
        System.out.println("TC04: 边界情况 - 无桥接词");
        System.out.println("图结构: a->b, c->d (无连接)");
        System.out.println("查询: queryBridgeWords(graph, \"a\", \"d\")");
        System.out.println("期望输出: \"No bridge words from a to d!\"");
        
        Lab1Experiment1.DirectedGraph graph4 = new Lab1Experiment1.DirectedGraph();
        graph4.addEdge("a", "b");
        graph4.addEdge("c", "d");
        String result4 = Lab1Experiment1.queryBridgeWords(graph4, "a", "d");
        System.out.println("实际输出: \"" + result4 + "\"");
        System.out.println("结果: " + (result4.equals("No bridge words from a to d!") ? "PASS" : "FAIL"));
        System.out.println();
        
        // TC07测试
        System.out.println("TC07: 异常情况 - word1不存在");
        System.out.println("图结构: a->b");
        System.out.println("查询: queryBridgeWords(graph, \"nonexistent\", \"b\")");
        System.out.println("期望输出: \"No word1 or word2 in the graph!\"");
        
        Lab1Experiment1.DirectedGraph graph7 = new Lab1Experiment1.DirectedGraph();
        graph7.addEdge("a", "b");
        String result7 = Lab1Experiment1.queryBridgeWords(graph7, "nonexistent", "b");
        System.out.println("实际输出: \"" + result7 + "\"");
        System.out.println("结果: " + (result7.equals("No word1 or word2 in the graph!") ? "PASS" : "FAIL"));
        System.out.println();
        
        // TC10测试
        System.out.println("TC10: 异常情况 - 空图");
        System.out.println("图结构: 空图");
        System.out.println("查询: queryBridgeWords(graph, \"a\", \"b\")");
        System.out.println("期望输出: \"No word1 or word2 in the graph!\"");
        
        Lab1Experiment1.DirectedGraph graph10 = new Lab1Experiment1.DirectedGraph();
        String result10 = Lab1Experiment1.queryBridgeWords(graph10, "a", "b");
        System.out.println("实际输出: \"" + result10 + "\"");
        System.out.println("结果: " + (result10.equals("No word1 or word2 in the graph!") ? "PASS" : "FAIL"));
        System.out.println();
        
        // TC11测试 - null参数
        System.out.println("TC11: 异常情况 - word1为null");
        System.out.println("图结构: 正常图");
        System.out.println("查询: queryBridgeWords(graph, null, \"test\")");
        System.out.println("期望: 抛出NullPointerException");
        
        Lab1Experiment1.DirectedGraph graph11 = new Lab1Experiment1.DirectedGraph();
        try {
            String result11 = Lab1Experiment1.queryBridgeWords(graph11, null, "test");
            System.out.println("实际输出: \"" + result11 + "\" (没有抛出异常)");
            System.out.println("结果: FAIL");
        } catch (NullPointerException e) {
            System.out.println("实际输出: 成功抛出NullPointerException");
            System.out.println("结果: PASS");
        }
        System.out.println();
        
        // TC16测试 - null图
        System.out.println("TC16: 异常情况 - 图对象为null");
        System.out.println("图结构: null");
        System.out.println("查询: queryBridgeWords(null, \"a\", \"b\")");
        System.out.println("期望: 抛出NullPointerException");
        
        try {
            String result16 = Lab1Experiment1.queryBridgeWords(null, "a", "b");
            System.out.println("实际输出: \"" + result16 + "\" (没有抛出异常)");
            System.out.println("结果: FAIL");
        } catch (NullPointerException e) {
            System.out.println("实际输出: 成功抛出NullPointerException");
            System.out.println("结果: PASS");
        }
        System.out.println();
        
        System.out.println("=== 测试完成 ===");
    }
}
