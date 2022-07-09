package DLC.N14888;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, PLUS = 0, MINUS = 1, MULTI = 2, DIVIDE = 3, max = MIN_VALUE, min = MAX_VALUE;
    static int[] nums, opers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        opers = new int[4];
        N = parseInt(br.readLine());
        nums = new int[N];
        var st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            nums[n] = parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            opers[i] = parseInt(st.nextToken());
        }
        calculate(1, nums[0]);
        sb.append(max).append('\n').append(min);
        System.out.println(sb);
    }

    static void calculate(int n, int num) {
        if (n == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        } else {
            if (opers[PLUS] > 0) {
                opers[PLUS]--;
                calculate(n + 1, num + nums[n]);
                opers[PLUS]++;
            }
            if (opers[MINUS] > 0) {
                opers[MINUS]--;
                calculate(n + 1, num - nums[n]);
                opers[MINUS]++;
            }
            if (opers[MULTI] > 0) {
                opers[MULTI]--;
                calculate(n + 1, num * nums[n]);
                opers[MULTI]++;
            }
            if (opers[DIVIDE] > 0) {
                opers[DIVIDE]--;
                calculate(n + 1, num / nums[n]);
                opers[DIVIDE]++;
            }
        }
    }
}
