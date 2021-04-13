package greedy_algorithms.huffman.node;

public class LeafNode extends Node {
    private char symbol;

    public LeafNode(char symbol, int sum) {
        super(sum);
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
