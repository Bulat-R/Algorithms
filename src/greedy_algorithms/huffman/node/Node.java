package greedy_algorithms.huffman.node;

public class Node implements Comparable<Node> {
    private final int sum;
    private String code;

    public Node(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public String getCode() {
        return code;
    }

    public void createCode(String code){
        this.code = code;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(sum, o.sum);
    }
}
