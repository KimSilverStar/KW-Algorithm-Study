package Week4.N2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<String, Integer> inorderMap, postorderMap;
    static String[] postorder, inorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var n = Integer.parseInt(br.readLine());
        inorderMap = new HashMap<>();
        postorderMap = new HashMap<>();
        postorder = new String[n];
        inorder = new String[n];

        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            var s = st.nextToken();
            inorderMap.put(s, i);
            inorder[i] = s;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            var s = st.nextToken();
            postorderMap.put(s, i);
            postorder[i] = s;
        }
        dq(n, 0);
        System.out.println(sb);
    }

    static void dq(int n, int start) {
        if (n == 3) {
            sb.append(postorder[start + 2]).append(' ')
                .append(postorder[start]).append(' ')
                .append(postorder[start + 1]).append(' ');
        } else if (n == 2) {
            sb.append(postorder[start + 1]).append(' ')
                .append(postorder[start]).append(' ');
        } else if (n == 1)
            sb.append(postorder[start]).append(' ');
        else if (n > 1) {
            var k = inorderMap.get(postorder[start + n - 1]);
            sb.append(postorder[start + n - 1]).append(' ');
            var inorderIndex = inorderMap.get(postorder[start]);
            dq(k - inorderIndex, inorderMap.get(inorder[start]));
            dq(n - (k - inorderIndex + 1), postorderMap.get(inorder[k + 1]));
        }
    }
}
