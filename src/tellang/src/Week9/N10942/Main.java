package Week9.N10942;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var arr = new int[N + 1];
        var st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            arr[n] = parseInt(st.nextToken());
        }
        var isDifferent = new boolean[N + 1][N + 1];

        for (int s = N - 1; s >= 1; s--) {
            for (int e = s + 1; e <= N; e++) {
                isDifferent[s][e] = ((arr[s] != arr[e]) || isDifferent[s + 1][e - 1]);
            }
        }
        var sb = new StringBuilder();
        var M = parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            sb.append(isDifferent[parseInt(st.nextToken())][parseInt(st.nextToken())] ? 0 : 1)
                .append('\n');
        }
        System.out.println(sb);
    }

}
