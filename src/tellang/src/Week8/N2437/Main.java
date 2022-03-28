package Week8.N2437;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        var st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            list.add(parseInt(st.nextToken()));
        }
        list.sort(Comparator.naturalOrder());

        var Sk = 1;
        for (var an : list) {
            if (an >= (Sk + 1))
                break;
            Sk += an;
        }
        System.out.println(Sk);
    }

}
