package Week4.N3584;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var T = parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            var N = parseInt(br.readLine());
            var level = new int[N + 1];
            var upTree = new HashMap<Integer, Integer>();
            var downTree = new HashMap<Integer, ArrayList<Integer>>();
            for (int n = 0; n < N - 1; n++) {
                var st = new StringTokenizer(br.readLine());
                var parent = parseInt(st.nextToken());
                var child = parseInt(st.nextToken());
                upTree.put(child, parent);
                if (!downTree.containsKey(parent))
                    downTree.put(parent, new ArrayList<>());
                downTree.get(parent).add(child);
                level[child] = level[parent] + 1;
                dfs(level, downTree, child);
            }
            var st = new StringTokenizer(br.readLine());
            var left = parseInt(st.nextToken());
            var right = parseInt(st.nextToken());
            var distance = Math.abs(level[left] - level[right]);
            if (level[left] > level[right]) {
                for (int i = 0; i < distance; i++) {
                    left = upTree.get(left);
                }
            } else {
                for (int i = 0; i < distance; i++) {
                    right = upTree.get(right);
                }
            }
            while (right != left) {
                if (!upTree.containsKey(right))
                    break;
                right = upTree.get(right);
                left = upTree.get(left);
            }
            sb.append(right).append('\n');
        }
        System.out.println(sb);
    }

    private static void dfs(int[] level, HashMap<Integer, ArrayList<Integer>> downTree,
        int child) {
        if (downTree.containsKey(child)) {
            for (var c : downTree.get(child)) {
                level[c] = level[child] + 1;
                dfs(level, downTree, c);
            }
        }
    }
}

