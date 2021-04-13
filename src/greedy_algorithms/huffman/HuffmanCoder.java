package greedy_algorithms.huffman;

import greedy_algorithms.huffman.node.*;
import java.io.*;
import java.util.*;

public class HuffmanCoder {

    public static void main(String[] args) throws IOException {
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter("input");
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            printWriter.print((char) ('a' + random.nextInt(26)));
        }
        printWriter.close();

        long start = System.currentTimeMillis();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input"));
        String inputString = bufferedReader.readLine();
        System.out.println(encryptString(inputString));
        bufferedReader.close();
        System.out.println(System.currentTimeMillis() - start);


    }

    public static String encryptString(String s) {
        if (s.length() == 1) {
            return "0";
        }
        StringBuilder encryptString = new StringBuilder();
        Map<Character, Integer> charsCount = getCharsCount(s);
        Map<Character, Node> cryptoTable = getCryptoTable(charsCount);
        for (Character c : s.toCharArray()) {
            encryptString.append(cryptoTable.get(c).getCode());
        }
        return encryptString.toString();
    }

    private static Map<Character, Integer> getCharsCount(String s) {
        Map<Character, Integer> charsCount = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (charsCount.containsKey(c)) {
                charsCount.put(c, charsCount.get(c) + 1);
            } else {
                charsCount.put(c, 1);
            }
        }
        return charsCount;
    }

    private static Map<Character, Node> getCryptoTable(Map<Character, Integer> charsCount) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Map<Character, Node> cryptoTable = new HashMap<>();

        for (Map.Entry<Character, Integer> pair : charsCount.entrySet()) {
            LeafNode leafNode = new LeafNode(pair.getKey(), pair.getValue());
            priorityQueue.add(leafNode);
            cryptoTable.put(leafNode.getSymbol(), leafNode);
        }

        while (priorityQueue.size() > 1) {
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            NeutralNode node = new NeutralNode(first, second);
            priorityQueue.add(node);
        }
        Node root = priorityQueue.poll();
        root.createCode("");

        return cryptoTable;
    }
}
