package DLC.N1759;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static List<Pair> chars;
    static Set<Character> vowels;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        L = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        chars = new ArrayList<>();
        vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < C; c++) {
            char key = st.nextToken().charAt(0);
            chars.add(new Pair(key, vowels.contains(key)));
        }
        chars.sort(Comparator.comparing(pair -> pair.c));
        backtracking(0, 0, 0, new StringBuilder());
        System.out.println(result);
    }

    public static void backtracking(int index, int vowelCount, int consonantCount,
        StringBuilder sb) {
        if (index == C) {
            if (vowelCount >= 1 && consonantCount >= 2 &&
                vowelCount + consonantCount == L) {
                result.append(sb).append('\n');
            }
        } else {
            Pair pair = chars.get(index);
            var nextVowelCount = vowelCount;
            var nextConsonantCount = consonantCount;
            if (pair.isVowel)
                nextVowelCount++;
            else
                nextConsonantCount++;
            backtracking(index + 1, nextVowelCount, nextConsonantCount,
                new StringBuilder(sb).append(pair.c));
            backtracking(index + 1, vowelCount, consonantCount, new StringBuilder(sb));
        }
    }
}

class Pair {

    char c;
    boolean isVowel;

    public Pair(char c, boolean isVowel) {
        this.c = c;
        this.isVowel = isVowel;
    }

    @Override
    public String toString() {
        return "Pair{" +
            "c=" + c +
            ", isVowel=" + isVowel +
            '}';
    }
}
