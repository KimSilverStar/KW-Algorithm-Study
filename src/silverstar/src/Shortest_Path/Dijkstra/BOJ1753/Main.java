package Shortest_Path.Dijkstra.BOJ1753;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 한 노드 -> 다른 모든 노드로의 최단경로
   => 다익스트라

 1) 비용 배열, 우선순위 큐 초기화
   - dist[] 를 모두 INF 로 초기화, dist[startV] = 0
   - pq.add(startV, 0)
 2) 우선순위 큐가 empty 할 때까지, 다음을 반복
   - pq 에서 가중치 w 가 가장 작은 노드를 꺼냄
   - 꺼낸 노드를 거쳐갈 때, 비용이 더 작은 경우
     => 최소 비용 갱신 및 pq 에 추가


2. 자료구조
 - List<Node>[], ArrayList<Node>[]: 인접 리스트
 - int[] dist: 비용 배열
 - PriorityQueue<Node>


3. 시간 복잡도
 - 다익스트라 시간 복잡도: O(E log_2 V)
   => E, V 최대값 대입: (3 x 10^5) x log_2 (2 x 10^4)
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

public class Main {
	static int v, e;					// 정점 개수 v, 간선 개수 e
	static int startV;					// 탐색 시작 정점
	static List<Node>[] lists;			// 인접 리스트

	static final int INF = 200_000;
	static int[] dist;					// 비용 배열
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static void dijkstra() {
		// 비용 배열, 우선순위 큐 초기화
		Arrays.fill(dist, INF);
		dist[startV] = 0;
		pq.add(new Node(startV, 0));

		while (!pq.isEmpty()) {
			Node current = pq.remove();

			if (dist[current.v] < current.w)		// 이미 갱신된 최단거리는 제외
				continue;

			for (Node next : lists[current.v]) {
				if (dist[next.v] > dist[current.v] + next.w) {
					dist[next.v] = dist[current.v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		startV = Integer.parseInt(br.readLine());

		lists = new ArrayList[v + 1];		// [1] ~ [v] 사용
		for (int i = 1; i <= v; i++)
			lists[i] = new ArrayList<>();

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			lists[u].add(new Node(v, w));
		}

		dist = new int[v + 1];		// [1] ~ [v] 사용
		dijkstra();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= v; i++) {
			if (dist[i] != INF)
				sb.append(dist[i]).append("\n");
			else
				sb.append("INF").append("\n");
		}
		System.out.println(sb);
	}
}
