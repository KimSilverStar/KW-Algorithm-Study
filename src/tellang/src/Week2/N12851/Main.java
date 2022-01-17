package Week2.N12851;

import static java.lang.System.*;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        var N = sc.nextInt();
        var K = sc.nextInt();
        var time = 0;
        var count = 0;
        var MAX = 100_001;
        StringBuilder sb = new StringBuilder();
        var step = new boolean[MAX];

        var q = new ArrayDeque<Pair>();
        q.push(new Pair(N, 0));
        while (!q.isEmpty()){
            var poll = q.poll();
            step[poll.pos] = true;
            if (poll.pos == K){
                time = poll.time;
                count++;
                while (!q.isEmpty()){
                    var p = q.poll();
                    if (p.time != time)
                        break;
                    if (p.pos == K)
                        count++;
                }
                sb.append(time).append('\n').append(count);
                out.println(sb);
                return;
            }else {
                if (poll.pos + 1 < MAX &&!step[poll.pos + 1])
                    q.offer(new Pair(poll.pos + 1, poll.time+1));
                if (poll.pos - 1 >= 0 &&!step[poll.pos - 1])
                    q.offer(new Pair(poll.pos - 1, poll.time+1));
                if (poll.pos * 2 < MAX &&!step[poll.pos * 2])
                    q.offer(new Pair(poll.pos * 2, poll.time+1));
            }
        }
    }
}
class Pair {
    int pos, time;

    public Pair(int pos, int time) {
        this.pos = pos;
        this.time = time;
    }
}
