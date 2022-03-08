package Week7.N1753;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        var V = parseInt(st.nextToken());
        var E = parseInt(st.nextToken());
        var K = parseInt(br.readLine());

        var values = new int[V + 1];
        var map = new HashMap<Integer, ArrayList<Node>>();
        for (int i = 1; i <= V; i++) {
            values[i] = MAX_VALUE;
            map.put(i, new ArrayList<>());
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            var u = parseInt(st.nextToken());
            var v = parseInt(st.nextToken());
            var w = parseInt(st.nextToken());

            map.get(u).add(new Node(v, w));
        }

        var q = new PriorityQueue<Node>(Comparator.comparingInt(o1 -> o1.val));
        q.offer(new Node(K, 0));
        values[K] = 0;
        while (!q.isEmpty()) {
            var p = q.poll();

            var nextNodes = map.get(p.index);
            for (var node : nextNodes) {
                var newCost = p.val + node.val;
                if (newCost < values[node.index]) {
                    values[node.index] = newCost;
                    q.offer(new Node(node.index, newCost));
                }
            }
        }
        var sb = new StringBuilder();
        for (int i = 1; i < values.length; i++) {
            if (values[i] == MAX_VALUE)
                sb.append("INF");
            else
                sb.append(values[i]);
            sb.append('\n');
        }
        System.out.println(sb);
    }

}

class Node {

    int index, val;

    public Node(int index, int val) {
        this.index = index;
        this.val = val;
    }
}
