package Week4.N11000;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());

        var lectures = new ArrayList<Lecture>();
        var clazzes = new PriorityQueue<Integer>();
        var count = 1;

        for (int i = 0; i < N; i++) {
            var st = new StringTokenizer(br.readLine());
            int startTime = parseInt(st.nextToken());
            int endTime = parseInt(st.nextToken());
            var lecture = new Lecture(startTime, endTime);
            lectures.add(lecture);
        }
        lectures.sort(((o1, o2) -> {
            if (o1.startTime == o2.startTime)
                return o1.endTime - o2.endTime;
            return o1.startTime - o2.startTime;
        }));
        for (var lecture : lectures) {
            if (!clazzes.isEmpty()) {
                if (clazzes.peek() <= lecture.startTime) {
                    clazzes.poll();
                } else {
                    count++;
                }
            }
            clazzes.offer(lecture.endTime);
        }
        System.out.println(count);
    }
}

class Lecture {

    int startTime, endTime;

    public Lecture(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
