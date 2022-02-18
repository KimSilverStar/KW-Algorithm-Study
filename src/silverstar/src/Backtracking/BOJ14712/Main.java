package Backtracking.BOJ14712;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - 넴모를 배치 및 삭제하는 순서 상관 X
   => 2 x 2 가 없는 넴모의 배치 조합 수 찾기
   => 백트래킹을 이용한 브루트 포스

 - 입력 행렬의 각 칸 [0][0] ~ [n-1][m-1] 을 차례로 확인
   => 각 칸에 대해 2가지 경우 존재
      1) 넴모를 놓는 경우 (해당 칸 선택 O)
      2) 넴모를 놓지 않는 경우 (해당 칸 선택 X)
 - 재귀 호출 종료 조건: 행렬의 마지막 칸까지 모두 확인한 경우
   => 2 x 2 를 이루는 넴모가 있는지 확인

2. 자료구조
 - boolean[][]: 넴모를 놓을 행렬

3. 시간 복잡도
 - 행렬의 각 칸에 대해 2가지 경우(선택 O / 선택 X)
   => 넴모 배치 조합 개수: 2^(n x m)
   => n, m 최대값 대입: 2^25 = 33,554,432
 - 행렬의 마지막 칸까지 모두 확인한 후, 2 x 2 넴모를 이루는지 확인
   => 대충 O(n x m)
   => n, m 최대값 대입: O(25)
   => 무시될 정도
*/

public class Main {
	static int n, m;				// n x m 행렬
	static boolean[][] map;
	static int count;				// 출력: 2 x 2 넴모를 이루지 않는 배치 가짓수

	static void solution(int y, int x) {
		if (y == n) {
			// 구성한 넴모 배치가 2 x 2 를 이루는지 확인 => 4칸씩 확인
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < m - 1; j++) {
					// 2 x 2 를 이루는 경우
					if (map[i][j] && map[i][j+1] &&
						map[i+1][j] && map[i+1][j+1])
						return;
				}
			}

			count++;
			return;
		}

		int nx = (x + 1 == m) ? 0 : x + 1;
		int ny = (nx == 0) ? y + 1 : y;

		// 넴모 배치 구성
		map[y][x] = true;			// 넴모 배치 O
		solution(ny, nx);

		map[y][x] = false;			// 넴모 배치 X
		solution(ny, nx);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new boolean[n][m];

		solution(0, 0);
		System.out.println(count);
	}
}
