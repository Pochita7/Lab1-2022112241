
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Lab1Experiment1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. 选择或输入文件路径
        System.out.print("请输入文本文件路径：");
        String path = scanner.nextLine().trim();
        String text = readFile(path);
        if (text == null) {
            System.err.println("读取文件失败！");
            return;
        }
        // 2. 预处理并生成图
        DirectedGraph graph = GraphBuilder.build(text);
        System.out.println("有向图已生成，共 " + graph.nodeCount() + " 个节点，"
                + graph.edgeCount() + " 条有向边。");

        // 3. 交互菜单
        while (true) {
            System.out.println("\n请选择功能：");
            System.out.println("1. 展示有向图");
            System.out.println("2. 查询桥接词");
            System.out.println("3. 根据桥接词生成新文本");
            System.out.println("4. 最短路径");
            System.out.println("5. 计算 PageRank");
            System.out.println("6. 随机游走");
            System.out.println("0. 退出");
            System.out.print(">> ");
            String cmd = scanner.nextLine().trim();
            switch (cmd) {
                case "1":
                    showDirectedGraph(graph);
                    break;
                case "2":
                    System.out.print("word1 = ");
                    String w1 = scanner.next();
                    System.out.print("word2 = ");
                    String w2 = scanner.next();
                    scanner.nextLine();
                    System.out.println(queryBridgeWords(graph, w1, w2));
                    break;
                case "3":
                    System.out.print("请输入一行文本：");
                    String inputLine = scanner.nextLine();
                    System.out.println(generateNewText(graph, inputLine));
                    break;
                case "4":
                    System.out.print("起点 = ");
                    String s = scanner.next();
                    System.out.print("终点 = ");
                    String t = scanner.next();
                    scanner.nextLine();
                    System.out.println(calcShortestPath(graph, s, t));
                    break;
                case "5":
                    System.out.print("单词 = ");
                    String p = scanner.next();
                    scanner.nextLine();
                    Double pr = calPageRank(graph, p);
                    System.out.printf("%s 的 PageRank = %.6f\n", p, pr);
                    break;
                // ...existing code...
                case "6":
                    System.out.println("随机游走序列（结果将同时保存到文件中）：");
                    System.out.println(randomWalk(graph, 1000));
                    break;
                // ...existing code...
                case "0":
                    System.out.println("退出。");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效输入。");
            }
        }
    }

    // 读取整个文件为一个字符串
    static String readFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 功能 1：展示有向图
    static void showDirectedGraph(DirectedGraph G) {
        for (String u : G.nodes()) {
            System.out.print(u + " -> ");
            Map<String, Integer> outs = G.outgoing(u);
            if (outs.isEmpty()) {
                System.out.println("（无出边）");
            } else {
                String line = outs.entrySet().stream()
                    .map(e -> e.getKey() + "(" + e.getValue() + ")")
                    .collect(Collectors.joining(", "));
                System.out.println(line);
            }
        }
    }

    // 功能 3：查询桥接词
    static String queryBridgeWords(DirectedGraph G, String word1, String word2) {
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        if (!G.hasNode(word1) || !G.hasNode(word2)) {
            return "No word1 or word2 in the graph!";
        }
        Set<String> bridges = new HashSet<>();
        for (String mid : G.outgoing(word1).keySet()) {
            if (G.outgoing(mid).containsKey(word2)) {
                bridges.add(mid);
            }
        }
        if (bridges.isEmpty()) {
            return "No bridge words from " + word1 + " to " + word2 + "!";
        }
        return "The bridge words from " + word1 + " to " + word2 + " are: "
                + String.join(", ", bridges) + ".";
    }

    // 功能 4：根据桥接词生成新文本
    static String generateNewText(DirectedGraph G, String input) {
        Random rand = new Random();
        List<String> words = preprocess(input);
        List<String> out = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            out.add(words.get(i));
            if (i + 1 < words.size()) {
                Set<String> bridges = new HashSet<>(G.outgoing(words.get(i)).keySet());
                bridges.retainAll(G.incoming(words.get(i+1)).keySet());
                if (!bridges.isEmpty()) {
                    List<String> list = new ArrayList<>(bridges);
                    out.add(list.get(rand.nextInt(list.size())));
                }
            }
        }
        return String.join(" ", out);
    }

    // 功能 5：最短路径（Dijkstra）
    static String calcShortestPath(DirectedGraph G, String src, String dst) {
        src = src.toLowerCase(); dst = dst.toLowerCase();
        if (!G.hasNode(src) || !G.hasNode(dst)) {
            return "No such nodes in graph!";
        }
        // Dijkstra
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        for (String u : G.nodes()) dist.put(u, Integer.MAX_VALUE);
        dist.put(src, 0);
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(src);
        while (!pq.isEmpty()) {
            String u = pq.poll();
            if (u.equals(dst)) break;
            for (Map.Entry<String,Integer> e : G.outgoing(u).entrySet()) {
                String v = e.getKey(); int w = e.getValue();
                int nd = dist.get(u) + w;
                if (nd < dist.get(v)) {
                    dist.put(v, nd);
                    prev.put(v, u);
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
        if (!prev.containsKey(dst) && !src.equals(dst)) {
            return "Unreachable!";
        }
        // 重建路径
        List<String> path = new LinkedList<>();
        for (String at = dst; at != null; at = prev.get(at)) {
            path.add(0, at);
            if (at.equals(src)) break;
        }
        return String.format("Path: %s\nLength: %d",
                String.join(" -> ", path), dist.get(dst));
    }

    // 功能 6：PageRank
    static Double calPageRank(DirectedGraph G, String word) {
        word = word.toLowerCase();
        final double d = 0.85, eps = 1e-6;
        int N = G.nodeCount();
        Map<String, Double> pr = new HashMap<>(), tmp = new HashMap<>();
        double init = 1.0 / N;
        for (String u : G.nodes()) pr.put(u, init);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String u : G.nodes()) {
                double sum = 0;
                for (String v : G.incoming(u).keySet()) {
                    sum += pr.get(v) / G.outgoing(v).size();
                }
                tmp.put(u, (1 - d) / N + d * sum);
            }
            for (String u : G.nodes()) {
                if (Math.abs(tmp.get(u) - pr.get(u)) > eps) changed = true;
                pr.put(u, tmp.get(u));
            }
        }
        return pr.getOrDefault(word, 0.0);
    }

// ...existing code...
// 功能 7：随机游走
static String randomWalk(DirectedGraph G, int maxSteps) {
    Random rand = new Random();
    List<String> seq = new ArrayList<>();
    if (G.nodeCount() == 0) return "";
    String cur = G.nodes().iterator().next();
    seq.add(cur);
    Set<String> seenEdges = new HashSet<>();
    for (int i = 0; i < maxSteps; i++) {
        Map<String,Integer> outs = G.outgoing(cur);
        if (outs.isEmpty()) break;
        // 随机选择一条出边（按权重概率）
        int total = outs.values().stream().mapToInt(Integer::intValue).sum();
        int r = rand.nextInt(total);
        String next = null; int cum = 0;
        for (Map.Entry<String,Integer> e : outs.entrySet()) {
            cum += e.getValue();
            if (r < cum) { next = e.getKey(); break; }
        }
        String edgeId = cur + "->" + next;
        if (seenEdges.contains(edgeId)) break;
        seenEdges.add(edgeId);
        seq.add(next);
        cur = next;
    }
    String result = String.join(" -> ", seq);
    
    // 将结果保存到文件
    String filename = "randomWalk_" + System.currentTimeMillis() + ".txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        writer.write(result);
        System.out.println("随机游走结果已保存至文件: " + filename);
    } catch (IOException e) {
        System.err.println("保存到文件时出错: " + e.getMessage());
    }
    
    return result;
}
// ...existing code...

    // 文本预处理：小写，只保留字母，用空格分词
    static List<String> preprocess(String text) {
        return Arrays.stream(text.toLowerCase()
                .replaceAll("[^a-z]", " ")
                .split("\\s+"))
            .filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
    }

    // 构建图
    static class GraphBuilder {
        static DirectedGraph build(String raw) {
            List<String> words = preprocess(raw);
            DirectedGraph G = new DirectedGraph();
            String prev = null;
            for (String w : words) {
                G.addNode(w);
                if (prev != null) G.addEdge(prev, w);
                prev = w;
            }
            return G;
        }
    }

    // 有向图类
    static class DirectedGraph {
        private final Map<String, Map<String,Integer>> out = new HashMap<>();
        private final Map<String, Map<String,Integer>> in  = new HashMap<>();

        void addNode(String u) {
            out.putIfAbsent(u, new HashMap<>());
            in.putIfAbsent(u, new HashMap<>());
        }
        void addEdge(String u, String v) {
            addNode(u); addNode(v);
            out.get(u).merge(v, 1, Integer::sum);
            in .get(v).merge(u, 1, Integer::sum);
        }
        boolean hasNode(String u) { return out.containsKey(u); }
        Set<String> nodes() { return out.keySet(); }
        int nodeCount() { return out.size(); }
        int edgeCount() {
            return out.values().stream()
                .mapToInt(m -> m.values().stream().mapToInt(i->i).sum())
                .sum();
        }
        Map<String,Integer> outgoing(String u) {
            return out.getOrDefault(u, Collections.emptyMap());
        }
        Map<String,Integer> incoming(String u) {
            return in .getOrDefault(u, Collections.emptyMap());
        }
    }
}