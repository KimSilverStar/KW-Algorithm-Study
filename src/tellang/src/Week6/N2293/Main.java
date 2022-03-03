package Week6.N2293;

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
        var matrix = new long[N + 1][K + 1];
        matrix[0][0] = 1L;
        for (int n = 1; n <= N; n++) {
            var coin = parseInt(br.readLine());
            for (int k = 0; k <= K; k++) {
                matrix[n][k] = matrix[n - 1][k];
                if (coin <= k)
                    matrix[n][k] += matrix[n][k - coin];
            }
        }
        System.out.println(matrix[N][K]);
    }

}
