package Week2.N17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var T = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            var string = br.readLine().toCharArray();
            var start = 0;
            var end = string.length - 1;
            var counter = 0;
            while (start < end) {
                if (string[start] != string[end]) {
                    counter++;
                    if (counter >= 2) {
                        break;
                    }
                    if (string[start + 1] == string[end] && string[start] == string[end - 1]) {
                        var tieStart = start;
                        var tieEnd = end;
                        var isSemiPell = true;
                        start++;
                        while (start < end) {
                            if (string[start] != string[end]) {
                                isSemiPell = false;
                                break;
                            }
                            start++;
                            end--;
                        }
                        if (isSemiPell)
                            break;
                        start = tieStart;
                        end = tieEnd - 1;
                        while (start < end) {
                            if (string[start] != string[end]) {
                                counter = 2;
                                break;
                            }
                            start++;
                            end--;
                        }
                    } else if (string[start + 1] == string[end]) {
                        start++;
                    } else if (string[start] == string[end - 1]) {
                        end--;
                    } else {
                        counter++;
                        break;
                    }
                }
                start++;
                end--;
            }
            sb.append(counter).append('\n');
        }
        System.out.println(sb);
    }
}
