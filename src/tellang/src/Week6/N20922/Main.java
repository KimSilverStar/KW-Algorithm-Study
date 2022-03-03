package Week6.N20922;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var N = parseInt(st.nextToken());
        var K = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        var map = new HashMap<Integer, ArrayDeque<Integer>>();
        var result = 1;
        var l = 0;
        var length = 0;
        for (int r = 1; r <= N; r++) {
            var num = parseInt(st.nextToken());

            if (map.containsKey(num)) {
                if (map.get(num).size() >= K) {
                    l = Math.max(l, map.get(num).poll());
                }
            } else {
                var q = new ArrayDeque<Integer>();
                map.put(num, q);
            }
            map.get(num).offer(r);

            length = r - l;
            if (length > result) {
                result = length;
            }
        }

        System.out.println(result);
    }

}
