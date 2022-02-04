package Week3.N1967;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var n = parseInt(br.readLine());
        var map = new HashMap<Integer, List<Node>>();
        boolean[] isVisited;
        var head = 0;
        for (int i = 0; i < n - 1; i++) {
            var st = new StringTokenizer(br.readLine());
            head = parseInt(st.nextToken());
            var next = parseInt(st.nextToken());
            if (!map.containsKey(head))
                map.put(head, new ArrayList<>());
            if (!map.containsKey(next))
                map.put(next, new ArrayList<>());
            var cost = parseInt(st.nextToken());
            var nodes = map.get(head);
            nodes.add(new Node(next, cost));
            nodes = map.get(next);
            nodes.add(new Node(head, cost));
        }

        var max = 0;
        var stack = new Stack<Pair>();
        for (int i = 0; i < 2 && n > 1; i++) {
            isVisited = new boolean[n + 1];
            stack.push(new Pair(head, 0));
            isVisited[head] = true;
            while (!stack.isEmpty()) {
                var pop = stack.pop();
                if (pop.totalCost > max) {
                    max = pop.totalCost;
                    head = pop.node;
                }
                for (var next : map.get(pop.node)) {
                    if (!isVisited[next.next]) {
                        stack.push(new Pair(next.next, next.cost + pop.totalCost));
                        isVisited[next.next] = true;
                    }
                }
            }
        }
        System.out.println(max);
    }
}

class Node {

    int next, cost;

    public Node(int next, int cost) {
        this.next = next;
        this.cost = cost;
    }
}

class Pair {

    int node, totalCost;

    public Pair(int node, int totalCost) {
        this.node = node;
        this.totalCost = totalCost;
    }
}
