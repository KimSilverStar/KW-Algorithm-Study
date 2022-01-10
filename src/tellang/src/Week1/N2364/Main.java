package Week1.N2364;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        Cueue c = new Cueue(st);
    }
}

class Cueue {

    List<Balloon> balloons;
    StringBuilder sb = new StringBuilder();
    int presentIndex = 0;

    public Cueue(StringTokenizer st) {
        balloons = new LinkedList<>();
        int i = 0;
        while (st.hasMoreTokens()) {
            balloons.add(new Balloon(++i, Integer.parseInt(st.nextToken())));
        }
        drop();
    }

    private void drop() {
        while (!balloons.isEmpty()) {
            Balloon presentBalloon = balloons.get(presentIndex);
            sb.append(presentBalloon.index).append(" ");
            balloons.remove(presentIndex);
            if (presentIndex == (balloons.size())) {
                presentIndex--;
                presentBalloon.val++;
            }
            if (presentBalloon.val > 0)
                presentIndex = calIndex(presentIndex, presentBalloon.val - 1);
            else
                presentIndex = calIndex(presentIndex, presentBalloon.val);
        }
        System.out.println(sb);
    }

    private int calIndex(int startIndex, int offset) {
        if (balloons.isEmpty())
            return -1;
        offset %= balloons.size();
        startIndex += offset;
        startIndex %= balloons.size();
        if (startIndex < 0) {
            startIndex += balloons.size();
        }
        return startIndex;
    }
}

class Balloon {

    int index, val;

    public Balloon(int index, int val) {
        this.index = index;
        this.val = val;
    }

    @Override
    public String toString() {
        return "Balloon{" +
            "index=" + index +
            ", val=" + val +
            '}';
    }
}

