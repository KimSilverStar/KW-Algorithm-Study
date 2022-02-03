package Week4.N5639;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static Map<String, Node> tree = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var input = br.readLine();
        var root = input;
        tree.put(root, new Node(root));
        while (true) {
            input = br.readLine();
            if ((input == null || input.equals("")))
                break;
            tree.put(input, new Node(input));
            makeTree(input, root);
        }

        postOrder(root);
        System.out.println(sb);
    }

    static void makeTree(String node, String root) {
        var rootNode = tree.get(root);
        if (parseInt(node) < parseInt(root)) {
            if (rootNode.left == null)
                rootNode.left = node;
            else
                makeTree(node, rootNode.left);
        } else {
            if (rootNode.right == null)
                rootNode.right = node;
            else
                makeTree(node, rootNode.right);
        }
    }

    static void postOrder(String node) {
        var presentNode = tree.get(node);
        if (presentNode.left != null)
            postOrder(presentNode.left);
        if (presentNode.right != null) {
            postOrder(presentNode.right);
        }
        sb.append(node).append('\n');
    }
}

class Node {

    public String left, right, root;

    public Node(String root) {
        this.root = root;
    }

}