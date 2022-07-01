package DLC.N10844;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int CUT_OFF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(br.readLine());
        var dp = new long[N + 1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;
        for (int n = 2; n <= N; n++) {
            for (int i = 1; i < 9; i++) {
                dp[n][i] = (dp[n - 1][i - 1] + dp[n - 1][i + 1]) % CUT_OFF;
            }
            dp[n][0] = dp[n - 1][1];
            dp[n][9] = dp[n - 1][8];
        }
        var result = 0L;
        for (var count : dp[N]) {
            result += count;
        }

        System.out.println(result % CUT_OFF);
    }
}
