package Shortest_Path.Floyd_Warshall.BOJ2660;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 모든 회원의 점수를 구해야 함
   => 모든 회원 -> 나머지 모든 회원의 경로
   => 플로이드-와샬

 1) 비용 배열 초기화
   - dist[][] 를 모두 INF 로 초기화 후, dist[i][i] = 0
   - INF = (노드 개수 최대값 50) x (간선 가중치 최대값 1) = 50
 2) 비용 배열에 간선 가중치 저장
 3) 3중 for 문
   - 중간 경유 노드, 시작 노드, 끝 노드
   - 중간 경유 노드를 거쳐서 갈 때, 비용이 더 적은 경우 비용 갱신


2. 자료구조
 - int[][] dist: 비용 배열
   => 비용 최대값 INF = 50 << 21억 이므로, int 가능


3. 시간 복잡도
 - 플로이드-와샬 시간 복잡도: O(V^3)
   => V = n 최대값 대입: 50^3 = 125 x 10^3 << 1억
*/

public class Main {
	static int n;					// 회원 수
	static int[] scores;			// 각 회원의 점수
	static int candidateScore;		// 후보 점수
	static List<Integer> candidateList = new ArrayList<>();

	static final int INF = 50;
	static int[][] dist;			// 비용 배열

	static void solution() {
		// 각 회원의 점수 찾기
		scores = new int[n + 1];		// [1] ~ [n] 사용
		for (int i = 1; i <= n; i++) {
			scores[i] = dist[i][1];

			for (int j = 2; j <= n; j++) {
				if (scores[i] < dist[i][j])
					scores[i] = dist[i][j];
			}
		}

		// 후보 점수 찾기
		candidateScore = scores[1];
		for (int i = 2; i <= n; i++) {
			if (candidateScore > scores[i])
				candidateScore = scores[i];
		}

		// 후보 인원 찾기
		for (int i = 1; i <= n; i++) {
			if (scores[i] == candidateScore)
				candidateList.add(i);
		}
	}

	static void floyd() {
		// 3) 3중 for 문 - 중간 경유 노드, 시작 노드, 끝 노드
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					// 중간 경유 노드를 거쳐서 갈 때, 비용이 더 적은 경우 비용 갱신
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
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		// 1) 비용 배열 초기화
		dist = new int[n + 1][n + 1];			// [1][1] ~ [n][n] 사용
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j)
					dist[i][j] = INF;
				else
					dist[i][j] = 0;
			}
		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			if (v1 == -1 && v2 == -1)
				break;

			// 2) 비용 배열에 간선 가중치 저장
			dist[v1][v2] = 1;
			dist[v2][v1] = 1;
		}

		floyd();
		solution();

		StringBuilder sb = new StringBuilder();
		sb.append(candidateScore).append(" ")
				.append(candidateList.size()).append("\n");
		for (int candidate : candidateList)
			sb.append(candidate).append(" ");
		System.out.println(sb);
	}
}
