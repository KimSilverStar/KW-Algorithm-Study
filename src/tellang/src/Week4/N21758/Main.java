package Week4.N21758;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        var arr = new int[N];
        var left2Right = new int[N];
        var right2Left = new int[N];
        var max = 0;
        var total = 0;
        var pillar = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
            total += arr[i];
            if (i > 0 && i < N - 1 && pillar < arr[i]) {
                pillar = arr[i];
                left2Right[i] += (left2Right[i - 1] + arr[i]);
            } else {
                if (i == 0)
                    left2Right[i] = arr[i];
                else
                    left2Right[i] += (left2Right[i - 1] + arr[i]);
            }
        }

        right2Left[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right2Left[i] += right2Left[i + 1] + arr[i];
        }

        for (int i = 1; i < N - 1; i++) {
            var rightHorny = total - arr[0] - arr[i] + right2Left[i + 1];
            if (rightHorny > max)
                max = rightHorny;
        }
        for (int i = N - 2; i > 0; i--) {
            var leftHorny = total - arr[N - 1] - arr[i] + left2Right[i - 1];
            if (leftHorny > max)
                max = leftHorny;
        }
        var middleHorny = total - arr[0] - arr[N - 1] + pillar;
        if (middleHorny > max)
            max = middleHorny;
        System.out.println(max);
    }

    static int lineIntegration(int[] arr, int start, int end) {
        var result = 0;
        for (int i = start + 1; i <= end; i++) {
            result += arr[i];
        }
        return result;
    }
}
