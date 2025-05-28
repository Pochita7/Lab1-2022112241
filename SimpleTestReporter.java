public class SimpleTestReporter {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("          JUnit测试用例详细报告");
        System.out.println("========================================");
        System.out.println();
          try {
            int passed = 0;
            int total = 17;
            
            // TC01: 单个桥接词测试
            System.out.println("测试用例: TC01_SingleBridge");
            System.out.println("测试描述: 测试存在单个桥接词的情况");
            Lab1Experiment1.DirectedGraph graph1 = new Lab1Experiment1.DirectedGraph();
            graph1.addEdge("a", "b");
            graph1.addEdge("b", "c");
            String actual1 = Lab1Experiment1.queryBridgeWords(graph1, "a", "c");
            String expected1 = "The bridge words from a to c are: b.";
            boolean pass1 = expected1.equals(actual1);
            System.out.println("期望输出: " + expected1);
            System.out.println("实际输出: " + actual1);
            System.out.println("测试结果: " + (pass1 ? "✅ PASS" : "❌ FAIL"));
            if (pass1) passed++;
            System.out.println("----------------------------------------");
            
            // TC02: 多个桥接词测试
            System.out.println("测试用例: TC02_MultipleBridges");
            System.out.println("测试描述: 测试存在多个桥接词的情况");
            test.setUp();
            test.graph.addEdge("a", "b");
            test.graph.addEdge("b", "d");
            test.graph.addEdge("a", "c");
            test.graph.addEdge("c", "d");
            String actual2 = Lab1Experiment1.queryBridgeWords(test.graph, "a", "d");
            String expected2 = "包含'The bridge words from a to d are:'且包含'b'和'c'";
            boolean pass2 = actual2.contains("The bridge words from a to d are:") && 
                           actual2.contains("b") && actual2.contains("c");
            System.out.println("期望输出: " + expected2);
            System.out.println("实际输出: " + actual2);
            System.out.println("测试结果: " + (pass2 ? "✅ PASS" : "❌ FAIL"));
            if (pass2) passed++;
            System.out.println("----------------------------------------");
            
            // TC03: 大小写不敏感测试
            System.out.println("测试用例: TC03_CaseInsensitive");
            System.out.println("测试描述: 测试查询时大小写不敏感");
            test.setUp();
            test.graph.addEdge("hello", "world");
            test.graph.addEdge("world", "test");
            String actual3 = Lab1Experiment1.queryBridgeWords(test.graph, "HELLO", "TEST");
            String expected3 = "The bridge words from hello to test are: world.";
            boolean pass3 = expected3.equals(actual3);
            System.out.println("期望输出: " + expected3);
            System.out.println("实际输出: " + actual3);
            System.out.println("测试结果: " + (pass3 ? "✅ PASS" : "❌ FAIL"));
            if (pass3) passed++;
            System.out.println("----------------------------------------");
            
            // TC04: 无桥接词测试
            System.out.println("测试用例: TC04_NoBridgeWords");
            System.out.println("测试描述: 测试两个单词间无桥接词的情况");
            test.setUp();
            test.graph.addEdge("a", "b");
            test.graph.addEdge("c", "d");
            String actual4 = Lab1Experiment1.queryBridgeWords(test.graph, "a", "d");
            String expected4 = "No bridge words from a to d!";
            boolean pass4 = expected4.equals(actual4);
            System.out.println("期望输出: " + expected4);
            System.out.println("实际输出: " + actual4);
            System.out.println("测试结果: " + (pass4 ? "✅ PASS" : "❌ FAIL"));
            if (pass4) passed++;
            System.out.println("----------------------------------------");
            
            // TC05: 直接连接测试
            System.out.println("测试用例: TC05_DirectConnection");
            System.out.println("测试描述: 测试两个单词直接相连的情况");
            test.setUp();
            test.graph.addEdge("start", "end");
            String actual5 = Lab1Experiment1.queryBridgeWords(test.graph, "start", "end");
            String expected5 = "No bridge words from start to end!";
            boolean pass5 = expected5.equals(actual5);
            System.out.println("期望输出: " + expected5);
            System.out.println("实际输出: " + actual5);
            System.out.println("测试结果: " + (pass5 ? "✅ PASS" : "❌ FAIL"));
            if (pass5) passed++;
            System.out.println("----------------------------------------");
            
            // TC06: 相同单词测试
            System.out.println("测试用例: TC06_SameWord");
            System.out.println("测试描述: 测试查询相同单词的桥接词");
            test.setUp();
            test.graph.addNode("word");
            String actual6 = Lab1Experiment1.queryBridgeWords(test.graph, "word", "word");
            String expected6 = "No bridge words from word to word!";
            boolean pass6 = expected6.equals(actual6);
            System.out.println("期望输出: " + expected6);
            System.out.println("实际输出: " + actual6);
            System.out.println("测试结果: " + (pass6 ? "✅ PASS" : "❌ FAIL"));
            if (pass6) passed++;
            System.out.println("----------------------------------------");
            
            // TC07: Word1不存在测试
            System.out.println("测试用例: TC07_Word1NotExists");
            System.out.println("测试描述: 测试第一个单词不存在的情况");
            test.setUp();
            test.graph.addNode("existing");
            String actual7 = Lab1Experiment1.queryBridgeWords(test.graph, "nonexistent", "existing");
            String expected7 = "No word1 or word2 in the graph!";
            boolean pass7 = expected7.equals(actual7);
            System.out.println("期望输出: " + expected7);
            System.out.println("实际输出: " + actual7);
            System.out.println("测试结果: " + (pass7 ? "✅ PASS" : "❌ FAIL"));
            if (pass7) passed++;
            System.out.println("----------------------------------------");
            
            // TC08: Word2不存在测试
            System.out.println("测试用例: TC08_Word2NotExists");
            System.out.println("测试描述: 测试第二个单词不存在的情况");
            test.setUp();
            test.graph.addNode("existing");
            String actual8 = Lab1Experiment1.queryBridgeWords(test.graph, "existing", "nonexistent");
            String expected8 = "No word1 or word2 in the graph!";
            boolean pass8 = expected8.equals(actual8);
            System.out.println("期望输出: " + expected8);
            System.out.println("实际输出: " + actual8);
            System.out.println("测试结果: " + (pass8 ? "✅ PASS" : "❌ FAIL"));
            if (pass8) passed++;
            System.out.println("----------------------------------------");
            
            // TC09: 两个单词都不存在测试
            System.out.println("测试用例: TC09_BothWordsNotExist");
            System.out.println("测试描述: 测试两个单词都不存在的情况");
            test.setUp();
            test.graph.addNode("existing");
            String actual9 = Lab1Experiment1.queryBridgeWords(test.graph, "word1", "word2");
            String expected9 = "No word1 or word2 in the graph!";
            boolean pass9 = expected9.equals(actual9);
            System.out.println("期望输出: " + expected9);
            System.out.println("实际输出: " + actual9);
            System.out.println("测试结果: " + (pass9 ? "✅ PASS" : "❌ FAIL"));
            if (pass9) passed++;
            System.out.println("----------------------------------------");
            
            // TC10: 空图测试
            System.out.println("测试用例: TC10_EmptyGraph");
            System.out.println("测试描述: 测试在空图中查询桥接词");
            test.setUp();
            String actual10 = Lab1Experiment1.queryBridgeWords(test.graph, "any", "word");
            String expected10 = "No word1 or word2 in the graph!";
            boolean pass10 = expected10.equals(actual10);
            System.out.println("期望输出: " + expected10);
            System.out.println("实际输出: " + actual10);
            System.out.println("测试结果: " + (pass10 ? "✅ PASS" : "❌ FAIL"));
            if (pass10) passed++;
            System.out.println("----------------------------------------");
            
            // TC11: Word1为null测试
            System.out.println("测试用例: TC11_Word1Null");
            System.out.println("测试描述: 测试第一个参数为null的情况");
            test.setUp();
            test.graph.addNode("test");
            boolean pass11 = false;
            String actual11 = "";
            try {
                actual11 = Lab1Experiment1.queryBridgeWords(test.graph, null, "test");
                actual11 = "未抛出异常：" + actual11;
            } catch (NullPointerException e) {
                actual11 = "成功抛出NullPointerException";
                pass11 = true;
            }
            String expected11 = "抛出NullPointerException";
            System.out.println("期望输出: " + expected11);
            System.out.println("实际输出: " + actual11);
            System.out.println("测试结果: " + (pass11 ? "✅ PASS" : "❌ FAIL"));
            if (pass11) passed++;
            System.out.println("----------------------------------------");
            
            // TC12: Word2为null测试
            System.out.println("测试用例: TC12_Word2Null");
            System.out.println("测试描述: 测试第二个参数为null的情况");
            test.setUp();
            test.graph.addNode("test");
            boolean pass12 = false;
            String actual12 = "";
            try {
                actual12 = Lab1Experiment1.queryBridgeWords(test.graph, "test", null);
                actual12 = "未抛出异常：" + actual12;
            } catch (NullPointerException e) {
                actual12 = "成功抛出NullPointerException";
                pass12 = true;
            }
            String expected12 = "抛出NullPointerException";
            System.out.println("期望输出: " + expected12);
            System.out.println("实际输出: " + actual12);
            System.out.println("测试结果: " + (pass12 ? "✅ PASS" : "❌ FAIL"));
            if (pass12) passed++;
            System.out.println("----------------------------------------");
            
            // TC13: 空字符串测试
            System.out.println("测试用例: TC13_EmptyStrings");
            System.out.println("测试描述: 测试空字符串参数的情况");
            test.setUp();
            test.graph.addNode("test");
            String actual13 = Lab1Experiment1.queryBridgeWords(test.graph, "", "");
            String expected13 = "No word1 or word2 in the graph!";
            boolean pass13 = expected13.equals(actual13);
            System.out.println("期望输出: " + expected13);
            System.out.println("实际输出: " + actual13);
            System.out.println("测试结果: " + (pass13 ? "✅ PASS" : "❌ FAIL"));
            if (pass13) passed++;
            System.out.println("----------------------------------------");
            
            // TC14: 自环测试
            System.out.println("测试用例: TC14_SelfLoop");
            System.out.println("测试描述: 测试包含自环的图");
            test.setUp();
            test.graph.addEdge("start", "node");
            test.graph.addEdge("node", "end");
            test.graph.addEdge("node", "node");
            String actual14 = Lab1Experiment1.queryBridgeWords(test.graph, "start", "end");
            String expected14 = "The bridge words from start to end are: node.";
            boolean pass14 = expected14.equals(actual14);
            System.out.println("期望输出: " + expected14);
            System.out.println("实际输出: " + actual14);
            System.out.println("测试结果: " + (pass14 ? "✅ PASS" : "❌ FAIL"));
            if (pass14) passed++;
            System.out.println("----------------------------------------");
            
            // TC15: 特殊字符测试
            System.out.println("测试用例: TC15_SpecialCharacters");
            System.out.println("测试描述: 测试包含特殊字符的单词");
            test.setUp();
            test.graph.addNode("normal");
            String actual15 = Lab1Experiment1.queryBridgeWords(test.graph, "word@123", "test word");
            String expected15 = "No word1 or word2 in the graph!";
            boolean pass15 = expected15.equals(actual15);
            System.out.println("期望输出: " + expected15);
            System.out.println("实际输出: " + actual15);
            System.out.println("测试结果: " + (pass15 ? "✅ PASS" : "❌ FAIL"));
            if (pass15) passed++;
            System.out.println("----------------------------------------");
            
            // TC16: 图为null测试
            System.out.println("测试用例: TC16_GraphNull");
            System.out.println("测试描述: 测试图对象为null的情况");
            boolean pass16 = false;
            String actual16 = "";
            try {
                actual16 = Lab1Experiment1.queryBridgeWords(null, "word1", "word2");
                actual16 = "未抛出异常：" + actual16;
            } catch (NullPointerException e) {
                actual16 = "成功抛出NullPointerException";
                pass16 = true;
            }
            String expected16 = "抛出NullPointerException";
            System.out.println("期望输出: " + expected16);
            System.out.println("实际输出: " + actual16);
            System.out.println("测试结果: " + (pass16 ? "✅ PASS" : "❌ FAIL"));
            if (pass16) passed++;
            System.out.println("----------------------------------------");
            
            // TC17: 无边节点测试
            System.out.println("测试用例: TC17_NodesWithoutEdges");
            System.out.println("测试描述: 测试存在但无连接边的节点");
            test.setUp();
            test.graph.addNode("node1");
            test.graph.addNode("node2");
            String actual17 = Lab1Experiment1.queryBridgeWords(test.graph, "node1", "node2");
            String expected17 = "No bridge words from node1 to node2!";
            boolean pass17 = expected17.equals(actual17);
            System.out.println("期望输出: " + expected17);
            System.out.println("实际输出: " + actual17);
            System.out.println("测试结果: " + (pass17 ? "✅ PASS" : "❌ FAIL"));
            if (pass17) passed++;
            System.out.println("----------------------------------------");
            
            // 打印总结
            System.out.println();
            System.out.println("========================================");
            System.out.println("          测试统计");
            System.out.println("========================================");
            System.out.println("总测试数: " + total);
            System.out.println("通过测试: " + passed);
            System.out.println("失败测试: " + (total - passed));
            System.out.println("成功率: " + String.format("%.1f%%", (double)passed / total * 100));
            
        } catch (Exception e) {
            System.err.println("测试过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
