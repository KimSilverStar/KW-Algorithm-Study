package DFS_BFS.BOJ9466;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - 팀을 이루든, 이루지 못하든 링크는 항상 싸이클을 이루면서 끝남
   e.g. 1 -> 3 -> 3,	4 -> 7 -> 6 -> 4
 - 링크를 따라 DFS 수행
 - 한번 탐색이 종료된 노드들은 더 이상 탐색 X

2. 자료구조
 - boolean[] check: 단순 방문 확인
   => 탐색 확장 여부 판단
 - boolean[] finished: 해당 노드들의 탐색 종료 여부
   => 이미 링크를 따라 탐색이 완료된 노드들 표시

3. 시간 복잡도
 - n 최대 10^5
*/

public class Main {
	static int t;					// 테스트 케이스 개수
	static int n;					// 학생 수
	static int[] students;
	static int soloCount;			// 팀을 이루지 못한 학생 수

	static boolean[] check;			// 방문 확인 여부
	static boolean[] finished;		// 탐색 종료 여부

	/* currentId: 현재 보고있는 학생 번호 */
	static void dfs(int currentId) {
		check[currentId] = true;

		int nextId = students[currentId];		// 다음 학생 번호
		if (!check[nextId])
			dfs(nextId);
		else {		// 다음 학생을 이미 방문한 경우 => 싸이클 구성
			if (!finished[nextId]) {		// 아직 탐색하지 않은 학생인 경우
				// 싸이클 중에서 팀을 구성하는 인원 카운트
				soloCount--;
				while (nextId != currentId) {
					soloCount--;
					nextId = students[nextId];
					// nextId 에서 시작, 링크를 따라 currentId 까지 따라감
				}
			}
		}

		finished[currentId] = true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			students = new int[n + 1];				// [1 ~ n] 사용
			for (int i = 1; i <= n; i++)
				students[i] = Integer.parseInt(st.nextToken());

			soloCount = n;					// 초기화
			check = new boolean[n + 1];
			finished = new boolean[n + 1];
			for (int i = 1; i <= n; i++) {
				if (!check[i])
					dfs(i);
			}
			sb.append(soloCount).append("\n");
		}

		System.out.println(sb.toString());
	}
}
