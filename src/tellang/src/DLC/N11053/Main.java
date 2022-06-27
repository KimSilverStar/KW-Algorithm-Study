package DLC.N11053;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, LIS;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var s = 0;
        arr = new int[N];
        LIS = new int[N];
        var st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = parseInt(st.nextToken());
        }
        LIS[0] = arr[0];
        for (int n = 1; n < N; n++) {
            int next = arr[n];
            if (LIS[s] < next)
                LIS[++s] = next;
            else {
                int i = binarySearch(0, s, next);
                LIS[i] = next;
            }
        }
        System.out.println(s + 1);
    }

    public static int binarySearch(int l, int r, int target) {
        while (l < r) {
            var m = (int) Math.ceil((r + l) >> 1);
            if (LIS[m] < target)
                l = m + 1;
            else
                r = m;
        }
        return r;
    }
}
