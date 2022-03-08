package Week7.N11404;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var M = parseInt(br.readLine());
        var MAX = 10_000_001;
        var map = new int[N + 1][N + 1];
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (y == x)
                    map[y][x] = 0;
                else
                    map[y][x] = MAX;
            }
        }
        for (int m = 0; m < M; m++) {
            var st = new StringTokenizer(br.readLine());
            int i = parseInt(st.nextToken());
            int j = parseInt(st.nextToken());
            int val = parseInt(st.nextToken());
            if (map[i][j] > val)
                map[i][j] = val;

        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        var sb = new StringBuilder();
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (map[y][x] == MAX)
                    sb.append('0');
                else
                    sb.append(map[y][x]);
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
