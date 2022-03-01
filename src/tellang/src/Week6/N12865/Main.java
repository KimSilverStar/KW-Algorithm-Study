package Week6.N12865;

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
        var K = parseInt(st.nextToken());
        var V = new int[N + 1][K + 1];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int weight = parseInt(st.nextToken());
            int val = parseInt(st.nextToken());
            for (int wLimit = 0; wLimit <= K; wLimit++) {
                if (wLimit >= weight)
                    V[n][wLimit] = Math.max(V[n - 1][wLimit - weight] + val, V[n - 1][wLimit]);
                else
                    V[n][wLimit] = V[n - 1][wLimit];
            }
        }
        System.out.println(V[N][K]);
    }
}
