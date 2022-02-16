package Week5.N14891;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int NOTCH_SIZE = 8, GEAR_NUM = 4, REVERSE = -1;
    static int[] notchOffsets = {0, 0, 0, 0};
    static boolean[][] isSouthNotch = new boolean[GEAR_NUM][NOTCH_SIZE];

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < GEAR_NUM; i++) {
            var notches = br.readLine().toCharArray();
            for (int j = 0; j < NOTCH_SIZE; j++) {
                if (notches[j] == '1')
                    isSouthNotch[i][j] = true;
            }
        }
        var K = parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            var st = new StringTokenizer(br.readLine());
            boolean[] isVisited = new boolean[GEAR_NUM];
            moveGears(isVisited, parseInt(st.nextToken()) - 1, parseInt(st.nextToken()) * REVERSE);
        }
        var result = 0;
        for (int i = 0; i < GEAR_NUM; i++) {
            if (isSouthNotch[i][notchOffsets[i]])
                result += Math.pow(2, i);
        }
        System.out.println(result);
    }

    public static void moveGears(boolean[] isVisited, int gearNum, int wise) {

        var left = getGearIndex(gearNum - 1);
        var right = getGearIndex(gearNum + 1);

        isVisited[gearNum] = true;
        if (gearNum > 0 && !isVisited[left]
            && isSouthNotch[gearNum][getNotchIndex(notchOffsets[gearNum] - 2)] !=
            isSouthNotch[left][getNotchIndex(notchOffsets[left] + 2)]) {
            isVisited[left] = true;
            moveGears(isVisited, left, REVERSE * wise);
        }
        if (gearNum < GEAR_NUM - 1 && !isVisited[right]
            && isSouthNotch[gearNum][getNotchIndex(notchOffsets[gearNum] + 2)] !=
            isSouthNotch[right][getNotchIndex(notchOffsets[right] - 2)]) {
            isVisited[right] = true;
            moveGears(isVisited, right, REVERSE * wise);
        }
        moveGear(gearNum, wise);
    }

    private static void moveGear(int gearNum, int move) {
        notchOffsets[gearNum] = getNotchIndex(notchOffsets[gearNum] + move);
    }

    private static int getNotchIndex(int index) {
        return getNextIndex(index, NOTCH_SIZE);
    }

    private static int getGearIndex(int index) {
        return getNextIndex(index, GEAR_NUM);
    }

    private static int getNextIndex(int index, int size) {
        index %= size;
        if (index < 0)
            return size + index;
        return index;
    }
}
