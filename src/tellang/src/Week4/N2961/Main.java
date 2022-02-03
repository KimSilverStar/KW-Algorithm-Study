package Week4.N2961;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long min = Long.MAX_VALUE;
    static List<Spice> spices = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        var br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            var st = new StringTokenizer(br.readLine());
            var spice = new Spice(parseLong(st.nextToken()), parseLong(st.nextToken()));
            spices.add(spice);
        }

        backTracking(new Spice(1, 0), 0, 0, true);
        System.out.println(min);
    }

    public static void backTracking(Spice basic, int index, int count, boolean isWater) {
        if (count == N) {
            var newStatus = basic.getStatus();
            if (newStatus < min && !isWater)
                min = newStatus;
        } else {
            backTracking(new Spice(basic, spices.get(index)), index + 1, count + 1, false);
            backTracking(basic, index + 1, count + 1, isWater);
        }
    }
}

class Spice {

    long S, B;

    public Spice(long s, long b) {
        S = s;
        B = b;
    }

    public Spice(Spice s0, Spice s1) {
        S = s0.S * s1.S;
        B = s0.B + s1.B;
    }

    public long getStatus() {
        return abs(S - B);
    }
}
