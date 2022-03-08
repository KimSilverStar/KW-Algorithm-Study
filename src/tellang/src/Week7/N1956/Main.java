package Week7.N1956;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var V = parseInt(st.nextToken());
        var E = parseInt(st.nextToken());
        var MAX = 2_000_000_001;
        var map = new long[V + 1][V + 1];
        for (int y = 1; y <= V; y++) {
            for (int x = 1; x <= V; x++) {
                map[y][x] = MAX;
            }
        }
        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int i = parseInt(st.nextToken());
            int j = parseInt(st.nextToken());
            int val = parseInt(st.nextToken());
            map[i][j] = val;
        }
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        long min = MAX;
        for (int i = 1; i <= V; i++) {
            if (map[i][i] < min)
                min = map[i][i];
        }
        if (min == MAX)
            min = -1;
        System.out.println(min);
    }
}
