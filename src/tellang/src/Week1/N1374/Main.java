package Week1.N1374;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        var lectures = new ArrayList<Lecture>();
        var clazzes = new int[N];
        var count = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken(); // ignore index
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            var lecture = new Lecture(startTime, endTime);
            lectures.add(lecture);
        }
        lectures.sort(((o1, o2) -> {
            if (o1.startTime == o2.startTime)
                return o1.endTime - o2.endTime;
            return o1.startTime - o2.startTime;
        }));
        for (var lecture : lectures) {
            for (int i = 0; i < N; i++) {
                if (clazzes[i] <= lecture.startTime) {
                    if (clazzes[i] == 0)
                        count++;
                    clazzes[i] = lecture.endTime;
                    break;
                }
            }
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

    @Override
    public String toString() {
        return "Lecture{" +
            "startTime=" + startTime +
            ", endTime=" + endTime +
            '}';
    }
}
