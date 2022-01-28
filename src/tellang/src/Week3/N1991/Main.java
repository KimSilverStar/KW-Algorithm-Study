package Week3.N1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<String, Tree> map = new HashMap<>();
    static StringBuilder pre = new StringBuilder();
    static StringBuilder in = new StringBuilder();
    static StringBuilder post = new StringBuilder();

    public static void main(String[] args) throws IOException {

        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        var key = st.nextToken();
        map.put(key, new Tree(st.nextToken(), st.nextToken()));
        var root = key;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            key = st.nextToken();
            map.put(key, new Tree(st.nextToken(), st.nextToken()));
        }
        order(root);
        pre.append('\n').append(in).append('\n').append(post);
        System.out.println(pre);
    }

    static void order(String root) {
        var head = map.get(root);
        pre.append(root);
        if (!head.left.equals(".")) {
            order(head.left);
        }
        in.append(root);
        if (!head.right.equals(".")) {
            order(head.right);
        }
        post.append(root);
    }
}

class Tree {

    String left, right;

    public Tree(String left, String right) {
        this.left = left;
        this.right = right;
    }
}
