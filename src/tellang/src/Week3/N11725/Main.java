package Week3.N11725;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine()) + 1;
        var isParent = new boolean[N];
        var map = new HashMap<Integer, ArrayList<Integer>>();
        var result = new int[N];
        var index = new int[]{0, 1, 0};
        isParent[1] = true;
        var nums = new int[2];
        var stack = new Stack<Pair>();
        for (int i = 1; i < N - 1; i++) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                nums[j] = parseInt(st.nextToken());
                if (!map.containsKey(nums[j])) {
                    map.put(nums[j], new ArrayList<>());
                }
            }
            if (!isParent[nums[0]] && !isParent[nums[1]]) {
                for (int j = 0; j < 2; j++) {
                    map.get(nums[index[j]]).add(nums[index[j + 1]]);
                }
            } else {
                var parent = nums[0];
                var child = nums[1];
                if (isParent[nums[1]]) {
                    parent = nums[1];
                    child = nums[0];
                }
                stack.push(new Pair(child, parent));
                while (!stack.isEmpty()) {
                    var pop = stack.pop();
                    result[pop.node] = pop.parent;

                    isParent[pop.node] = true;
                    for (var grandChild : map.get(pop.node)) {
                        if (!isParent[grandChild]) {
                            stack.push(new Pair(grandChild, pop.node));
                        }
                    }
                }
            }
        }
        var sb = new StringBuilder();
        for (int j = 2; j < N; j++) {
            sb.append(result[j]).append('\n');
        }
        System.out.println(sb);
    }
}

class Pair {

    int node, parent;

    public Pair(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}
