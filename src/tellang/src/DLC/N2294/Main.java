package DLC.N2294;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var N = parseInt(st.nextToken());
        var K = parseInt(st.nextToken());
        var dp = new int[K + 1];
        var MAX = 10_001;
        Arrays.fill(dp, MAX);
        var coins = new ArrayList<Integer>();
        for (int n = 0; n < N; n++) {
            int coin = parseInt(br.readLine());
            if (coin <= K) {
                coins.add(coin);
                dp[coin] = 1;
            }
        }
        for (int k = 1; k <= K; k++) {
            for (var n : coins) {
                if (k >= n) {
                    dp[k] = min(dp[k], dp[k - n] + 1);
                }
            }
        }
        System.out.println(dp[K] >= MAX ? -1 : dp[K]);
    }

}
