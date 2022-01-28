package Week3.N9279;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
//        var br = new BufferedReader(new InputStreamReader(System.in));
        var sc = new Scanner(System.in);
        var sb = new StringBuilder();
        while (sc.hasNext()) {
//            var st = new StringTokenizer(br.readLine());
//            var N = parseInt(st.nextToken());
//            var C = parseInt(st.nextToken());
            var N = sc.nextInt();
            var C = sc.nextInt();
            var map = new HashMap<Integer, ArrayList<Node>>();
            for (int i = 0; i < N - 1; i++) {
//                st = new StringTokenizer(br.readLine());
//                var node = parseInt(st.nextToken());
//                var next = parseInt(st.nextToken());
//                var cost = parseInt(st.nextToken());
                var node = sc.nextInt();
                var next = sc.nextInt();
                var cost = sc.nextInt();
                if (!map.containsKey(node)) {
                    map.put(node, new ArrayList<>());
                }
                if (!map.containsKey(next)) {
                    map.put(next, new ArrayList<>());
                }
                var nodes = map.get(node);
                nodes.add(new Node(next, cost, 0));
            }
            var stack = new Stack<Node>();
            stack.push(new Node(C, 0, 0));
            var max = 0;
            while (!stack.isEmpty()) {
                var pop = stack.pop();
                var cost = pop.totalCost;

                ArrayList<Node> nodes = map.get(pop.node);
                if (nodes.isEmpty()) {
                    max += pop.totalCost;
                }
                var childCost = 0;
                for (var child : nodes) {
                    childCost += child.value;
                }
                if (childCost > pop.totalCost)
                    cost = childCost;
                for (var child : nodes) {
                    child.totalCost = cost;
                    stack.push(child);
                }
            }
            sb.append(max).append('\n');
        }
        System.out.println(sb);
    }
}

class Node {

    int node, value, totalCost;

    public Node(int node, int value, int totalCost) {
        this.node = node;
        this.value = value;
        this.totalCost = totalCost;
    }
}
