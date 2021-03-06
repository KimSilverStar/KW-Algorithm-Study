package Shortest_Path.Floyd_Warshall.BOJ1389;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 - 케빈 베이컨의 수 = "모든 사람과 케빈 베이컨 게임을 했을 때, 나오는 단계의 합"
 - 모든 노드 -> 나머지 모든 노드
   => 플로이드-와샬
 - [i]번 노드의 케빈 베이컨 수 = [i]행 dist[i]의 원소 합
 - 친구 관계 O or 친구 관계 X
   => 간선의 가중치 0 or 1

 1) 비용 배열 초기화
   - dist[][] 를 모두 INF 로 초기화, dist[i][i] = 0
   - INF = (노드 최대 개수 100) x (간선 가중치 최대값 1) = 100
 2) 비용 배열에 간선 가중치 저장
   - 노드 i -> 노드 j 로 가는 간선이 여러 개일 경우, 간선 가중치 작은 값으로 저장
 3) 3중 for 문
   - 중간 경유 노드, 시작 노드, 끝 노드
   - 중간 경유 노드를 거쳐서 갈 때 비용이 더 작은 경우, 비용 갱신


2. 자료구조
 - int[][] dist: 비용 배열
   => 비용 최대값 INF = 100 << 21억 이므로, int 가능


3. 시간 복잡도
 - 플로이드-와샬 시간 복잡도: O(V^3)
   => V = n 최대값 대입: 100^3 = 10^6 << 2억
*/

public class Main {
	static int n, m;				// 유저의 수 n, 친구 관계의 수 m
	static int minNode = 1;			// 출력, 비용 합이 최소인 노드 번호

	static final int INF = 100;
	static int[][] dist;			// 비용 배열
	static int[] numbers;			// 각 유저의 케빈 베이컨 수

	static void solution() {
		// 각 유저의 케빈 베이컨 수 계산 및 최소인 유저 찾기
		numbers = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				numbers[i] += dist[i][j];

			if (numbers[minNode] > numbers[i])
				minNode = i;
		}
	}

	static void floyd() {
		// 3) 3중 for 문 - 중간 경유 노드, 시작 노드, 끝 노드
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					// 중간 경유 노드를 거쳐서 갈 때 비용이 더 작은 경우, 비용 갱신
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

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 1) 비용 배열 초기화
		dist = new int[n + 1][n + 1];		// [1][1] ~ [n][n] 사용
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j)
					dist[i][j] = INF;
				else
					dist[i][j] = 0;
			}
		}

		// 2) 비용 배열에 간선 가중치 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			dist[v1][v2] = 1;
			dist[v2][v1] = 1;
		}

		floyd();
		solution();
		System.out.println(minNode);
	}
}
