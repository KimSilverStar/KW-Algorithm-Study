package Add.Q3;

import java.util.Arrays;

class Solution {

    static int[] powerCount;
    static int fuel, maxIndex = 0;

    public static double getVal(int index, int[] powers, int[] powerCount, int[] distance) {
        return (double) distance[index] / powers[index] * powerCount[index];
    }

    public int solution(int fuel, int[] powers, int[] distances) {
        powerCount = new int[powers.length];
        Arrays.fill(powerCount, 1);
        Solution.fuel = fuel - powers.length;
        for (int i = 0; i < fuel; i++) {
            double max = 0;
            for (int j = 0; j < powers.length; j++) {
                double val = getVal(j, powers, powerCount, distances);
//                double val = getTime(distances, getVelocity(powers, maxIndex));
                if (max < val) {
                    maxIndex = j;
                    max = val;
                }
            }
            Solution.fuel--;
            powerCount[maxIndex]++;
        }
        double max = 0;
        for (int i = 0; i < powers.length; i++) {

            double velocity = getVelocity(powers, i);
            double result = getTime(distances, velocity, i);
            if (result > max)
                max = result;
        }
        return (int) Math.ceil(max);
    }

    private int getVelocity(int[] powers, int maxIndex) {
        return powerCount[maxIndex] * powers[maxIndex];
    }

    private double getTime(int[] distances, double velocity, int maxIndex) {
        return powerCount[maxIndex]
            + (distances[maxIndex] - velocity / 2 * powerCount[maxIndex]) / velocity;
    }
}