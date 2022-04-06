package Week9.N9252;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var R = sc.next().toCharArray();
        var L = sc.next().toCharArray();
        int l = 0;
        int r = 0;
        var LCS = new int[L.length + 1][R.length + 1];
        var sb = new StringBuilder();
        for (; l < L.length; l++) {
            for (r = 0; r < R.length; r++) {
                if (L[l] == R[r]) {
                    LCS[l + 1][r + 1] = LCS[l][r] + 1;
                } else {
                    LCS[l + 1][r + 1] = Math.max(LCS[l + 1][r], LCS[l][r + 1]);
                }
            }
        }
        var count = 0;
        while (l > 0 && r > 0 && LCS[l][r] > 0) {
            if (LCS[l][r] == LCS[l - 1][r]) {
                l--;
            } else {
                if (LCS[l][r] != LCS[l][r - 1]) {
                    sb.append(L[--l]);
                    count++;
                }
                r--;
            }
        }
        System.out.println(String.valueOf(count) + '\n' + sb.reverse());
    }
}
