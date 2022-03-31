package Week8.N7983;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var map = new TreeMap<Integer, ArrayList<Integer>>(Comparator.reverseOrder());
        for (int n = 0; n < N; n++) {
            var st = new StringTokenizer(br.readLine());
            var di = parseInt(st.nextToken());
            var ti = parseInt(st.nextToken());
            if (!map.containsKey(ti))
                map.put(ti, new ArrayList<>());
            map.get(ti).add(di);
        }

        var lastDay = MAX_VALUE;
        for (var key : map.keySet()) {
            for (var day : map.get(key)) {
                if (lastDay < key)
                    lastDay -= day;
                else
                    lastDay = (key - day);
            }
        }
        System.out.println(lastDay);
    }
}
