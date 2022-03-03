package Week6.N1806;

import static java.lang.Integer.MAX_VALUE;
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
        var S = parseInt(st.nextToken());
        var arr = new int[N];
        var total = 0;
        var size = MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = parseInt(st.nextToken());
        }
        var l = 0;
        var r = 0;
        while ((r < N) || (l < N)) {
            if (total >= S) {
                if ((total - arr[l]) >= S) {
                    total -= arr[l++];
                    int nSize = Math.abs(r - l);
                    if (nSize < size)
                        size = nSize;
                } else {
                    if (r > N - 1)
                        break;
                    total += arr[r++];
                }
            } else {
                if (r > N - 1)
                    break;
                total += arr[r++];
                if (total >= S) {
                    int nSize = Math.abs(r - l);
                    if (nSize < size)
                        size = nSize;
                }
            }
        }
        if (size == MAX_VALUE)
            size = 0;
        System.out.println(size);
    }
}
