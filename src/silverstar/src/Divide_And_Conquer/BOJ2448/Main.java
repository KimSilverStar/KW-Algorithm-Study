package Divide_And_Conquer.BOJ2448;
import java.io.*;

/*
1. 풀이
 - 큰 삼각형을 3개의 작은 삼각형으로 분할
   => 상단, 하단 좌측, 하단 우측
   => 파라미터: 삼각형의 상단 꼭짓점 좌표, 삼각형의 높이
 - solution(int y, int x, int h)
   => 재귀 호출 종료 조건: h == 3 인 경우, 출력

2. 자료구조
 - char[][]: 출력 저장

3. 시간 복잡도
 - 1개의 큰 삼각형이 3개의 작은 삼각형으로 구성
   => 1개의 삼각형에 대해 재귀 호출 3번 수행
 - 시간 복잡도 = O(3^k)
   => k 최대값 10 대입: 3^10 = 59,049 << 1억
*/

public class Main {
	static int n;
	static char[][] map;

	static void solution(int y, int x, int h) {
		if (h == 3) {		// 가장 작은 높이 3 삼각형까지 분할한 경우, 출력
			map[y][x] = '*';

			map[y + 1][x - 1] = '*';
			map[y + 1][x + 1] = '*';

			map[y + 2][x - 2] = '*';
			map[y + 2][x - 1] = '*';
			map[y + 2][x] = '*';
			map[y + 2][x + 1] = '*';
			map[y + 2][x + 2] = '*';

			return;
		}

		// 3개의 작은 삼각형으로 분할
		int nh = h / 2;
		solution(y, x, nh);						// 상단
		solution(y + nh, x - nh, nh);		// 하단 좌측
		solution(y + nh, x + nh, nh);		// 하단 우측
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		n = Integer.parseInt(br.readLine());
		map = new char[n][2 * n];				// 전체 높이: n, 전체 너비: 2 * n
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n; j++)
				map[i][j] = ' ';
		}

		solution(0, n - 1, n);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n; j++)
				sb.append(map[i][j]);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
