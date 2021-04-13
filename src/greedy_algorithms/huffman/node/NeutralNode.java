package greedy_algorithms.huffman.node;

public class NeutralNode extends Node {
    private Node left;
    private Node right;

    public NeutralNode(Node left, Node right) {
        super(left.getSum() + right.getSum());
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public void createCode(String code) {
        super.createCode(code);
        left.createCode(code + "0");
        right.createCode(code + "1");
    }
}
