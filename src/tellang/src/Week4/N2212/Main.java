package Week4.N2212;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var K = parseInt(br.readLine());
        var set = new ConcurrentSkipListSet<Integer>();
        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(parseInt(st.nextToken()));
        }
        var iter = set.iterator();
        var base = iter.next();
        var distances = new ArrayList<Integer>();
        var result = 0;
        while (iter.hasNext()) {
            var next = iter.next();
            var distance = next - base;
            result += distance;
            distances.add(distance);
            base = next;
        }
        distances.sort(Comparator.reverseOrder());
        if (!distances.isEmpty()) {
            for (int i = 0; i < K - 1; i++) {
                result -= distances.get(i);
            }
        }
        System.out.println(result);
    }
}
