package DLC.N11057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int CUT_OFF = 10_007;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(br.readLine());
        var dp = new int[N][10];
        Arrays.fill(dp[0], 1);
        for (int n = 1; n < N; n++) {
            for (int i = 0; i <= 9; i++) {
                for (int j = 9; j >= i; j--) {
                    dp[n][i] = (dp[n][i] + dp[n - 1][j]) % CUT_OFF;
                }
            }
        }
        var result = 0;
        for (var n : dp[N - 1]) {
            result += n;
        }
        System.out.println(result % CUT_OFF);
    }

}
