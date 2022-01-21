package DFS_BFS.BOJ12851;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 최소 시간 => BFS

2. 자료구조
 - Queue<Pair>, LinkedList<Pair>: BFS
   => Pair: 위치, 경과 시간 저장
 - boolean[]: 방문 확인

3. 시간 복잡도
 - O(V + E)
*/

class Pair {
	private int position;			// 위치
	private int time;				// 경과 시간

	public Pair(int position, int time) {
		this.position = position;
		this.time = time;
	}
	public int getPosition() { return position; }
	public int getTime() { return time; }
}

public class Main {
	static int n, k;				// 수빈 시작 위치, 목표 동생 위치
	static int minTime = Integer.MAX_VALUE;			// 출력값 1: 찾는 가장 빠른 시간
	static int minCount;			// 출력값 2: 가장 빠른 시간으로 찾는 방법 수

	static Queue<Pair> queue = new LinkedList<>();
	static boolean[] check = new boolean[100001];		// [1 ~ 100,000] 사용
	static int[] minTimes = new int[100001];			// 각 지점으로 가는 최소 시간들 저장

	static void bfs() {
		while (!queue.isEmpty()) {
			Pair current = queue.remove();
			int position = current.getPosition();
			int time = current.getTime();

			if (minTime < minTimes[position])		// 종료 조건
				return;

			// 최초로 최단 시간으로 찾은 경우 => 해당 Depth 까지만 마저 확인
			if (position == k) {
//				minTime = Math.min(minTime, time);
				minTime = time;
				minCount++;
			}

			int next1 = position - 1;
			if (0 <= next1 && next1 <= 100000) {
				// 다음 지점을 아직 방문 안했거나 or 방문 했더라도 경과한 시간이 같은 경우
				if (!check[next1] || minTimes[next1] == time + 1) {
					check[next1] = true;
					minTimes[next1] = time + 1;
					queue.add(new Pair(next1, time + 1));
				}
			}
			int next2 = position + 1;
			if (0 <= next2 && next2 <= 100000) {
				if (!check[next2] || minTimes[next2] == time + 1) {
					check[next2] = true;
					minTimes[next2] = time + 1;
					queue.add(new Pair(next2, time + 1));
				}
			}
			int next3 = position * 2;
			if (0 <= next3 && next3 <= 100000) {
				if (!check[next3]  || minTimes[next3] == time + 1) {
					check[next3] = true;
					minTimes[next3] = time + 1;
					queue.add(new Pair(next3, time + 1));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		if (n >= k) {
			minTime = n - k;	// -1 칸씩 (n-k)번 이동하는 1가지 방법만 존재
			minCount = 1;
		}
		else {		// n < k
			minTimes[n] = 0;
			check[n] = true;
			queue.add(new Pair(n, 0));
			bfs();
		}

		System.out.println(minTime);
		System.out.println(minCount);
	}
}
