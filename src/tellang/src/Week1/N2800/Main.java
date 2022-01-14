package Week1.N2800;

import static java.lang.Math.pow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

public class Main {

    static char[] target;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var s = br.readLine();
        target = s.toCharArray();
        var stack = new Stack<Integer>();
        var parentheses = new ArrayList<Parenthesis>();
        for (int i = 0; i < target.length; i++) {
            var c = target[i];
            if (c == '(')
                stack.push(i);
            else if (c == ')') {
                parentheses.add(new Parenthesis(stack.pop(), i));
            }
        }
        var size = parentheses.size();
        var set = new TreeSet<String>();
        for (int i = 1; i < pow(2, size); i++) {
            var str = new StringBuilder(s);
            for (int j = 0; j < size; j++) {
                if ((i & (int) pow(2, j)) == (int) pow(2, j)) {
                    var par = parentheses.get(j);
                    str.setCharAt(par.start, ' ');
                    str.setCharAt(par.end, ' ');
                }
            }
            set.add(str.toString().replaceAll(" ", ""));
        }
        var result = new StringBuilder();
        for (var str: set)
            result.append(str).append('\n');
        System.out.println(result);
    }
}

class Parenthesis {

    int start, end;

    public Parenthesis(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
