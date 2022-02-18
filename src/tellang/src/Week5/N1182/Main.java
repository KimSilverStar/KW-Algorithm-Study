package Week5.N1182;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S, result = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        S = parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        backtrack(0, 0, false);
        System.out.println(result);
    }

    private static void backtrack(int i, int s, boolean isChanged) {
        if (s == S && isChanged) {
            result++;
        }
        if (i < N) {
            backtrack(i + 1, s, false);
            s += arr[i];
            backtrack(i + 1, s, true);
        }
    }
}
