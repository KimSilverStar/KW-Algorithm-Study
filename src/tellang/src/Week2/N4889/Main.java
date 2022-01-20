package Week2.N4889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; !string.startsWith("-"); i++) {
            int left = 0;
            int fixCount = 0;
            for (char c : string.toCharArray()) {
                if (c == '{')
                    left++;
                else {
                    if (left == 0) {
                        fixCount++;
                        left++;
                    } else
                        left--;
                }
            }
            sb.append(i).append(". ").append((fixCount) + (left / 2)).append('\n');
            string = br.readLine();
        }
        System.out.println(sb);
    }
}
