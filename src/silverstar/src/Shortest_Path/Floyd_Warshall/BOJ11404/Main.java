package Shortest_Path.Floyd_Warshall.BOJ11404;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 - 모든 도시(노드) -> 다른 모든 도시(노드)의 최소 비용
   => 플로이드-와샬

  1) 비용 배열 초기화
    - cost[][] 를 모두 INF 로 초기화 후, cost[i][i] = 0
    - INF = (노드 최대 개수 10^2) x (간선 가중치 최대값 10^5) = 10^7
  2) 비용 배열에 간선 가중치 저장
    - 노드 i -> 노드 j 로 가는 간선이 여러 개일 경우, 가중치 더 작은 것으로 저장
  3) 3중 for 문
    - 중간 경유 노드, 시작 노드, 끝 노드
    - 중간 경유 노드를 거쳐서 갈 때, 비용이 더 작은 경우
      => 비용 갱신


2. 자료구조
 - int[][] cost: 비용 배열
   => 비용 최대값 INF = 10^7 << 21억 이므로, int 가능


3. 시간 복잡도
 - 플로이드-와샬 시간 복잡도: O(V^3)
   => V = n 최대값 대입: 100^3 = 10^6 << 1억
*/

public class Main {
	static int n;						// 도시 개수
	static int m;						// 버스 개수

	static final int INF = 10_000_000;
	static int[][] cost;				// 비용 배열

	static void floyd() {
		// 3) 3중 for 문 - 중간 경유 노드, 시작 노드, 끝 노드
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					// 중간 경유 노드를 거쳐서 갈 때, 비용이 더 작은 경우 비용 갱신
					if (cost[i][j] > cost[i][k] + cost[k][j])
						cost[i][j] = cost[i][k] + cost[k][j];
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		// 1) 비용 배열 초기화
		cost = new int[n + 1][n + 1];		// [1][1] ~ [n][n] 사용
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j)
					cost[i][j] = INF;
				else
					cost[i][j] = 0;
			}
		}

		// 2) 비용 배열에 간선 가중치 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 노드 i -> 노드 j 로 가는 간선이 여러 개일 경우, 가중치 더 작은 것으로 저장
			cost[a][b] = Math.min(cost[a][b], c);
		}

		floyd();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (cost[i][j] != INF)
					sb.append(cost[i][j]).append(" ");
				else
					sb.append(0).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
