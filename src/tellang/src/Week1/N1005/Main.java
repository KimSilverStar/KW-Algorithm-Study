package Week1.N1005;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var T = parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int j = 0; j < T; j++) {
            var st = new StringTokenizer(br.readLine());
            var N = parseInt(st.nextToken());
            var K = parseInt(st.nextToken());
            var costs = new int[N + 1];
            var dp = new int[N + 1];
            var map = new ArrayList<ArrayList<Integer>>();
            var result = 0;

            st = new StringTokenizer(br.readLine());
            map.add(new ArrayList<>());

            for (int i = 1; i <= N; i++) {
                costs[i] = parseInt(st.nextToken());
                map.add(new ArrayList<>());
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                var preLevel = parseInt(st.nextToken());
                var level = parseInt(st.nextToken());
                map.get(level).add(preLevel);
            }
            var W = parseInt(br.readLine());
            dp[W] = costs[W];
            var q = new ArrayDeque<Integer>();
            q.offer(W);
            while (!q.isEmpty()) {
                var pre = q.poll();
                var size = map.get(pre).size();
                if (size == 0)
                    result = Math.max(result, dp[pre]);
                else {
                    for (var next : map.get(pre)) {
                        if (dp[next] < (costs[next] + dp[pre])) {
                            dp[next] = (costs[next] + dp[pre]);
                            q.offer(next);
                        }
                    }
                }
            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
}