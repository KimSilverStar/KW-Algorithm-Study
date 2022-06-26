package Week9.N1106;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var C = parseInt(st.nextToken());
        var N = parseInt(st.nextToken());
        var DP = new int[C + 101]; // index: man, value: cost
        Arrays.fill(DP, MAX_VALUE);
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            var cost = parseInt(st.nextToken());
            var manCount = parseInt(st.nextToken());
            for (int i = cost; i < DP.length; i++) {
                if (n >= i)
                    DP[n] = max(DP[n], DP[n - i] + manCount);
            }
        }
        var result = MAX_VALUE;
        for (int i = C; i < DP.length; i++) {
            result = min(result, DP[i]);
        }
        System.out.println(result);
    }
}
