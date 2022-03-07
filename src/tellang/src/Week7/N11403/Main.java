package Week7.N11403;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            var st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = parseInt(st.nextToken());
            }
        }
        for (int k = 0; k < N; k++) {
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < N; i++) {
                    if (map[j][k] + map[k][i] == 2)
                        map[j][i] = 1;
                }
            }
        }
        var sb = new StringBuilder();
        for (var y : map) {
            for (var x : y) {
                sb.append(x).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
