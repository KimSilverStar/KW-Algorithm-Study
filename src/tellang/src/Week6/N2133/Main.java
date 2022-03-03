package Week6.N2133;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var N = sc.nextInt();
        var arr = new int[N + 2];
        arr[0] = 1;
        arr[2] = 3;
        for (int i = 4; i <= N; i += 2) {
            arr[i] = arr[i - 2] * 3;
            for (int j = 4; j <= i; j += 2) {
                arr[i] += (arr[i - j] * 2);
            }
        }
        System.out.println(arr[N]);
    }

}
