package Shortest_Path.Floyd_Warshall.BOJ1956;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 - 각 도시(노드)에서 출발하여,
   해당 도시(노드)로 돌아오는 싸이클의 최소 가중치 합
 - 모든 노드 -> 나머지 다른 모든 노드의 최단경로
   => 플로이드-와샬 (dist[i][i] = 0 초기화)
 - 모든 노드 -> 모든 노드(본인 노드 포함)의 최단경로
   => 플로이드-와샬 (dist[i][i] = INF 초기화)
   => 노드 본인으로 돌아오는 싸이클의 가중치까지 확인

 1) 비용 배열 초기화
   - dist[i][i] 를 포함하여, dist[][] 를 모두 INF 로 초기화
   - INF = (노드 최대 개수 401) x 간선 가중치 최대값 10^4
     => 노드 최대 개수: 400 + 1 (싸이클 구성하는 노드 본인 1개 추가)
 2) 비용 배열에 간선 가중치 저장
   - 노드 i -> 노드 j 로 가는 간선이 여러 개일 경우, 더 작은 가중치로 저장
 3) 3중 for 문
   - 중간 경유 노드, 시작 노드, 끝 노드
   - 중간 경유 노드를 거쳐서 갈 때, 비용이 더 작은 경우
     => 비용 갱신
 => 출력 minDistSum = dist[i][i] 최소값


2. 자료구조
 - int[][] dist: 비용 배열
   => 자료형: 최대값 INF = 401 x 10^4 < 21억 이므로, int 가능
   => 메모리: 4 x 400 x 400 byte = 64 x 10^4 byte = 0.64 MB


3. 시간 복잡도
 - 플로이드-와샬 시간 복잡도: O(V^3)
   => V 최대값 대입: 400^3 = 64 x 10^6 << 2억
*/

public class Main {
	static int v, e;
	static int minDistSum = Integer.MAX_VALUE;

	static final int INF = 4_010_000;		// 401 x 10000
	static int[][] dist;

	static void floyd() {
		for (int k = 1; k <= v; k++) {
			for (int i = 1; i <= v; i++) {
				for (int j = 1; j <= v; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
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

		dist = new int[v + 1][v + 1];
		for (int i = 1; i <= v; i++) {
			for (int j = 1; j <= v; j++)
				dist[i][j] = INF;
		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int startV = Integer.parseInt(st.nextToken());
			int destV = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			dist[startV][destV] = Math.min(dist[startV][destV], distance);
		}

		floyd();

		for (int i = 1; i <= v; i++) {
			if (dist[i][i] != INF)
				minDistSum = Math.min(minDistSum, dist[i][i]);
		}

		if (minDistSum != Integer.MAX_VALUE)
			System.out.println(minDistSum);
		else
			System.out.println(-1);
	}
}
