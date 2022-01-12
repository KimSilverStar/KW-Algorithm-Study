package Week1.N1005;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
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
            var buildCosts = new int[N + 1];
            var preCosts = new int[N + 1];
            var q = new PriorityQueue<Build>(((o1, o2) -> {
                if (o1.level == o2.level)
                    return o1.preLevel - o2.preLevel;
                return o1.level - o2.level;
            }));

            st = new StringTokenizer(br.readLine());
            for (int i = 1; st.hasMoreTokens(); i++) {
                buildCosts[i] = parseInt(st.nextToken());
            }
            for (int i = 0; i < K; i++) {
                q.offer(new Build(br.readLine()));
            }
            var W = parseInt(br.readLine());
            while (!q.isEmpty()) {
                var build = q.poll();
                if (preCosts[build.level] < preCosts[build.preLevel] + buildCosts[build.preLevel]) {
                    preCosts[build.level] = preCosts[build.preLevel] + buildCosts[build.preLevel];
                }
            }
            sb.append(buildCosts[W] + preCosts[W]).append('\n');
            System.out.println("buildCosts = " + Arrays.toString(buildCosts));
            System.out.println("preCosts = " + Arrays.toString(preCosts));
        }
        System.out.println(sb);
    }
}

class Build {

    int preLevel, level;

    public Build(String pair) {
        var st = new StringTokenizer(pair);
        preLevel = parseInt(st.nextToken());
        level = parseInt(st.nextToken());
    }

    @Override
    public String toString() {
        return "\n" +
            "level=" + level +
            ", preLevel=" + preLevel +
            "}";
    }
}
