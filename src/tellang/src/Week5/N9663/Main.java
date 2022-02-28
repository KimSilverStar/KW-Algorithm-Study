package Week5.N9663;

import static java.lang.Math.abs;

import java.util.Scanner;

public class Main {

    static int N;
    static int result = 0;
    static int[] queens;

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        N = sc.nextInt();
        queens = new int[N];
        nQueen(0);
        System.out.println(result);
    }

    public static void nQueen(int col) {
        if (col == N)
            result++;
        else {
            for (int row = 0; row < N; row++) {
                queens[col] = row;
                if (isCheck(col)) {
                    nQueen(col + 1);
                }
            }
        }
    }

    public static boolean isCheck(int column) {
        for (int i = 0; i < column; i++) {
            if ((queens[i] == queens[column]) ||
                (abs(i - column) == abs(queens[i] - queens[column])))
                return false;
        }
        return true;
    }

}

