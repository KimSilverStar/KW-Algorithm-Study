package Week7.N2660;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var relation = new int[N + 1][N + 1];
        var MAX = 1_000_001;
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (y == x)
                    relation[y][x] = 0;
                else
                    relation[y][x] = MAX;
            }
        }
        while (true) {
            var st = new StringTokenizer(br.readLine());
            var i = parseInt(st.nextToken());
            var j = parseInt(st.nextToken());
            if (i == -1)
                break;
            relation[i][j] = 1;
            relation[j][i] = 1;
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (relation[i][j] > relation[i][k] + relation[k][j]) {
                        relation[i][j] = relation[i][k] + relation[k][j];
                        relation[j][i] = relation[i][j];
                    }
                }
            }
        }
        var maxPoint = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (relation[i][j] != MAX && maxPoint[i] < relation[i][j]) {
                    maxPoint[i] = relation[i][j];
                }
            }
        }
        var sb = new StringBuilder();
        var list = new ArrayList<Integer>();
        var min = MAX;
        for (int i = 1; i <= N; i++) {
            if (min > maxPoint[i]) {
                list = new ArrayList<>();
                list.add(i);
                min = maxPoint[i];
            } else if (min == maxPoint[i]) {
                list.add(i);
            }

        }
        sb.append(min).append(' ').append(list.size()).append('\n');
        for (var i : list) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }

}
