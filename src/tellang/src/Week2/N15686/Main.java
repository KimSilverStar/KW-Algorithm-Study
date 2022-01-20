package Week2.N15686;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

enum Status {
    EMPTY, HOUSE, CHICKEN
}

public class Main {

    static int N, M;
    static List<Position> zip = new ArrayList<>();
    static List<Integer> banList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var min = MAX_VALUE;
        var chickenZipCount = 0;
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        var chickenZip = new ArrayList<Position>();
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                var node = parseInt(st.nextToken());
                if (node == Status.CHICKEN.ordinal()) {
                    chickenZip.add(new Position(y, x));
                    chickenZipCount++;
                } else if (node == Status.HOUSE.ordinal())
                    zip.add(new Position(y, x));
            }
        }

        combination(0, 0, chickenZipCount, M);
        for (var ban : banList) {
            var choiceIndex = new ArrayList<Integer>();
            for (int i = 0; i < chickenZipCount; i++) {
                var shift = 1 << i;
                if ((ban & shift) == shift) {
                    choiceIndex.add(i);
                }
            }
            var chickenGuryOfCity = 0;
            for (var house : zip) {
                var chickenGury = MAX_VALUE;
                for (var c : choiceIndex) {
                    var chickenDis = calDistance(house, chickenZip.get(c));
                    if (chickenDis < chickenGury)
                        chickenGury = chickenDis;
                }
                chickenGuryOfCity += chickenGury;
            }
            if (min > chickenGuryOfCity)
                min = chickenGuryOfCity;
        }
        System.out.println(min);
    }

    static int calDistance(Position p0, Position p1) {
        return abs(p0.y - p1.y) + abs(p0.x - p1.x);
    }


    static void combination(int visited, int offset, int n, int r) {
        if (r == 0) {
            banList.add(visited);
            return;
        }
        for (int i = offset; i < n; i++) {
            var shift = 1 << i;
            visited |= shift;
            combination(visited, i + 1, n, r - 1);
            visited ^= shift;
        }
    }

}

class Position {

    int y, x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
