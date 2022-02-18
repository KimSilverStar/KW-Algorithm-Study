package Backtracking.BOJ9663;
import java.io.*;

/*
1. 풀이
 => Backtracking 으로 가지치기를 해가며, 유망한 노드에 대해서만 확인

 - 행렬의 한 행씩 확인
   => 기본적으로, 퀸은 한 행에 1 개씩만 배치 가능 (상하좌우 직선 상으로 위협 X 해야함)
   => 한 행에서 좌 ~ 우로 열을 확인해가면서,
      promising 하면 (퀸을 놓을 수 있는 경우) 퀸 배치

 - 배치하려는 퀸의 promising 여부 => boolean isPromising(int depth)
   1) 이전 윗 행의 퀸들과 서로 다른 열에 위치
     - col[depth] 와 col[0 ~ depth - 1] 비교
   2) 이전 윗 행의 퀸들과 서로 다른 대각선 상에 위치
     - 같은 대각선 상에 위치 => "행의 차이 == 열의 차이"

 - 재귀 종료 조건: 마지막 행까지 모두 확인한 경우
 => void solution(int depth)

2. 자료구조
 - int[] col: 각 행에 배치된 퀸의 열 위치 저장
   ex) col[0]: 0 행에 배치된 퀸의 열 위치
       col[0] = 3 이면, [0][3] 에 퀸 배치됨

3. 시간 복잡도
 * 브루트 포스로 N x N 의 모든 칸을 확인: N ^ N
   => 시간 초과
 * 백트래킹 + 가지치기로 유망한 노드만 확인: N! 미만
   => N 최대값 대입: 대충 14! = 87,178,291,200 미만
*/

public class Main {
	static int n;				// n x n 행렬에 n개 퀸 배치
	static int count;			// n개 퀸을 배치 가능한 경우의 수

	static int[] col;			// 배치된 각 퀸의 열 저장

	/* depth: 행렬의 행 */
	static void solution(int depth) {
		if (depth == n) {		// 마지막 행까지 퀸을 모두 배치한 경우
			count++;
			return;
		}

		// 한 행에서 각 열에 퀸을 배치해보고, promising 한지 판단
		for (int i = 0; i < n; i++) {
			col[depth] = i;						// [depth][col] 위치에 퀸 배치
			if (promising(depth))
				solution(depth + 1);
		}
	}

	/* depth 행에 배치한 퀸이 유효한지 판단 - 서로 다른 열, 서로 다른 대각선 상 */
	static boolean promising(int depth) {
		// 이전 윗 행에 배치된 퀸들과 비교
		for (int i = 0; i < depth; i++) {
			if (col[i] == col[depth])
				return false;

			if ((depth - i) == Math.abs(col[depth] - col[i]))
				return false;
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		n = Integer.parseInt(br.readLine());
		col = new int[n];

		if (n == 1) {
			System.out.println(1);
			return;
		}
		else if (n == 2) {
			System.out.println(0);
			return;
		}

		solution(0);
		System.out.println(count);
	}
}
