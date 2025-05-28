public class BasicTest {
    public static void main(String[] args) {
        System.out.println("开始测试queryBridgeWords函数...\n");
        
        // 测试1: 单个桥接词
        Lab1Experiment1.DirectedGraph graph1 = new Lab1Experiment1.DirectedGraph();
        graph1.addEdge("a", "b");
        graph1.addEdge("b", "c");
        
        String result1 = Lab1Experiment1.queryBridgeWords(graph1, "a", "c");
        System.out.println("测试1 - 单个桥接词:");
        System.out.println("结果: " + result1);
        System.out.println();
        
        // 测试2: 多个桥接词
        Lab1Experiment1.DirectedGraph graph2 = new Lab1Experiment1.DirectedGraph();
        graph2.addEdge("a", "b");
        graph2.addEdge("b", "d");
        graph2.addEdge("a", "c");
        graph2.addEdge("c", "d");
        
        String result2 = Lab1Experiment1.queryBridgeWords(graph2, "a", "d");
        System.out.println("测试2 - 多个桥接词:");
        System.out.println("结果: " + result2);
        System.out.println();
        
        // 测试3: 无桥接词
        Lab1Experiment1.DirectedGraph graph3 = new Lab1Experiment1.DirectedGraph();
        graph3.addEdge("a", "b");
        graph3.addEdge("c", "d");
        
        String result3 = Lab1Experiment1.queryBridgeWords(graph3, "a", "d");
        System.out.println("测试3 - 无桥接词:");
        System.out.println("结果: " + result3);
        System.out.println();
        
        // 测试4: 单词不存在
        Lab1Experiment1.DirectedGraph graph4 = new Lab1Experiment1.DirectedGraph();
        graph4.addEdge("a", "b");
        
        String result4 = Lab1Experiment1.queryBridgeWords(graph4, "notexist", "b");
        System.out.println("测试4 - 单词不存在:");
        System.out.println("结果: " + result4);
        System.out.println();
        
        System.out.println("测试完成！");
    }
}
