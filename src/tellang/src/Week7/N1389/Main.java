package Week7.N1389;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var N = parseInt(st.nextToken());
        var M = parseInt(st.nextToken());
        var relation = new int[N + 1][N + 1];
        var CV = new int[N + 1];
        var MAX = 5001;
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                relation[y][x] = MAX;
            }
        }
        for (int y = 1; y <= N; y++) {
            relation[y][0] = y;
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            var a = parseInt(st.nextToken());
            var b = parseInt(st.nextToken());
            relation[a][b] = 1;
            relation[b][a] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (relation[i][j] > relation[i][k] + relation[k][j]) {
                        relation[i][j] = relation[i][k] + relation[k][j];
                    }
                }
            }
        }
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (relation[y][x] != MAX)
                    CV[y] += relation[y][x];
            }
        }
        var min = MAX;
        var result = 0;
        for (int i = 1; i <= N; i++) {
            if (min > CV[i]) {
                result = i;
                min = CV[i];
            }
        }
        System.out.println(result);
    }

}
