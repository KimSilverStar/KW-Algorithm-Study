package DLC.N15650;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new int[M];
        backTracking(1, 0);
        System.out.println(sb);
    }

    static void backTracking(int s, int i) {
        if (i == M) {
            for (var num : arr) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int j = s; j <= N; j++) {
            arr[i] = j;
            backTracking(j + 1, i + 1);
        }
    }
}
