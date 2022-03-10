package Shortest_Path.Dijkstra.BOJ13549;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 간선의 가중치가 다름
   => 앞, 뒤 1칸 이동하는 경우 1, 순간이동 x 2 칸 이동하는 경우 0
   => 일반적인 그래프 탐색 DFS / BFS 사용 X
 - 하나의 시작 지점 -> 나머지 다른 모든 지점으로의 최단경로
   => 다익스트라

 1) 비용 배열, 우선순위 큐 초기화
   - time[] 을 모두 INF 로 초기화 후, 시작 지점 dist[n] = 0
   - pq.add(n, 0)
   - INF = 노드 최대 개수 (|n - k| 최대 10^5) x 간선 가중치 최대값 1 = 10^5
 2) 우선순위 큐가 empty 할 때까지, 다음을 반복
   - pq 에서 비용이 가장 작은 노드를 꺼냄
     => 이미 갱신된 비용의 노드는 제외
   - 꺼낸 현재 노드의 -1칸, +1칸, x2칸 3가지 다음 위치에 대해,
     현재 노드를 거쳐서 다음 위치를 갈 때 비용이 더 적은 경우
     => 비용 갱신 및 우선순위 큐에 추가


2. 자료구조
 - int[] time: 비용 배열
   => 자료형: 최대값 INF = 10^5 이므로, int 가능
   => 메모리: 4 x 10^5 byte = 0.4 MB
 - PriorityQueue<Node>: 비용 가장 작은 노드부터 꺼냄
   => Node: 노드 v, 비용 w


3. 시간 복잡도
 - 다익스트라 시간 복잡도: O(E log_2 V)
   => 한 Edge 에 Vertex 3개 가정 (E = 3V)
   => O(3V log_2 V)
   => V = |n - k| => 최대값 10^5
   => (3 x 10^5) x log_2 10^5 = (15 x 10^5) x log_2 10
      ~= (15 x 10^5) x log_2 2^3 = 45 x 10^5 << 2억
*/

class Node implements Comparable<Node> {
	public int v;
	public int w;

	public Node(int v, int w) {
		this.v = v;
		this.w = w;
	}

	public int compareTo(Node n) {
		return this.w - n.w;
	}
}

public class Main_Dijkstra {
	static int n, k;				// 수빈 시작 위치 n, 목표 동생 위치 k
	static int minTime;				// 출력, 최소 시간

	static final int INF = 100_000;
	static final int MAX_POSITION = 100_000;
	static int[] time = new int[MAX_POSITION + 1];		// 비용 배열
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static void dijkstra() {
		// 1) 비용 배열, 우선순위 큐 초기화
		Arrays.fill(time, INF);
		time[n] = 0;							// 시작 지점
		pq.add(new Node(n, 0));

		// 2) 우선순위 큐가 empty 할 때까지, 다음을 반복
		while (!pq.isEmpty()) {
			Node current = pq.remove();

			if (time[current.v] < current.w)	// 이미 갱신된 비용의 노드는 제외
				continue;

			int nv1 = current.v - 1;
			if (isValid(nv1) && time[nv1] > time[current.v] + 1) {
				time[nv1] = time[current.v] + 1;
				pq.add(new Node(nv1, time[nv1]));
			}

			int nv2 = current.v + 1;
			if (isValid(nv2) && time[nv2] > time[current.v] + 1) {
				time[nv2] = time[current.v] + 1;
				pq.add(new Node(nv2, time[nv2]));
			}

			int nv3 = current.v * 2;
			if (isValid(nv3) && time[nv3] > time[current.v]) {		// 순간이동은 시간 소요 X
				time[nv3] = time[current.v];
				pq.add(new Node(nv3, time[nv3]));
			}
		}
	}

	static boolean isValid(int v) {
		return 0 <= v && v <= MAX_POSITION;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		if (n >= k)				// 수빈 시작 위치가 더 앞선 경우
			minTime = n - k;
		else {
			dijkstra();
			minTime = time[k];
		}

		System.out.println(minTime);
	}
}
