package Week8.N1092;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var cranes = new ArrayList<Integer>();
        var boxes = new ArrayList<Integer>();

        var N = parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            cranes.add(parseInt(st.nextToken()));
        }

        var M = parseInt(br.readLine());
        var isCarried = new boolean[M];
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            boxes.add(parseInt(st.nextToken()));
        }

        cranes.sort(Comparator.reverseOrder());
        boxes.sort(Comparator.reverseOrder());

        var carryCount = 0;
        var time = 0;

        //100_000_000
        while (carryCount < M) { //10_000
            var canCarry = false;
            var craneIndex = 0;
            for (int i = 0; i < boxes.size(); i++) { // 10_000
                var crane = cranes.get(craneIndex);
                if (!isCarried[i]) {
                    if (crane >= boxes.get(i)) {
                        isCarried[i] = true;
                        carryCount++;
                        canCarry = true;
                        craneIndex++;
                    }
                }
                if (craneIndex == cranes.size()) {
                    break;
                }
            }
            time++;
            if (!canCarry) {
                time = -1;
                break;
            } else if (carryCount == M)
                break;
        }
        System.out.println(time);
    }

}
