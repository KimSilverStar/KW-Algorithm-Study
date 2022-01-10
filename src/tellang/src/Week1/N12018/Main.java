package Week1.N12018;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var MIN_MILEAGE = 1;
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var n = parseInt(st.nextToken());
        var m = parseInt(st.nextToken());
        var targets = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            var Pi = parseInt(st.nextToken());
            var Li = parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < Pi; j++) {
                list.add(parseInt(st.nextToken()));
            }
            list.sort(reverseOrder());
            if (Pi >= Li) {
                var target = list.get(Li - 1);
                targets.add(target);
            } else {
                targets.add(MIN_MILEAGE);
            }
        }
        targets.sort(naturalOrder());
        var count = 0;
        var totalMileage = 0;
        for (var mileage : targets) {
            if (totalMileage + mileage > m)
                break;
            totalMileage += mileage;
            count++;
        }
        System.out.println(count);
    }
}
