package Week6.N1932;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] tri;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        tri = new int[N + 1][N + 1];
        for (int n = 1; n <= N; n++) {
            var st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                tri[n][i] = parseInt(st.nextToken());
            }
        }
        for (int i = N - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
            }
        }
        System.out.println(tri[1][1]);
    }
}
