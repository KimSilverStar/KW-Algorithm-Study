package Add.Q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution {

    public static String[] solution(String[] logs) {
        var user = new HashSet<String>();
        var sol = new TreeMap<String, HashSet<String>>();

        for (var log : logs) {
            var st = new StringTokenizer(log);
            var u = st.nextToken();
            var q = st.nextToken();

            user.add(u);
            if (!sol.containsKey(q))
                sol.put(q, new HashSet<>());
            var set = sol.get(q);
            set.add(u);
            sol.put(q, set);
        }
        int size = user.size();
        if (size % 2 != 0)
            size++;
        size /= 2;
        var list = new ArrayList<String>();
        for (var key : sol.keySet()) {
            if (sol.get(key).size() >= size)
                list.add(key);
        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args) {
        var logs = new String[]{"morgan string_compare", "felix string_compare", "morgan reverse",
            "rohan sort", "andy reverse", "morgan sqrt"};
        String[] solution = Solution.solution(logs);
        System.out.println(Arrays.toString(solution));
    }
}

