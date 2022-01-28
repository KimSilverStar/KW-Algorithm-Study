package DFS_BFS.BOJ2668;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 선택한 수들의 index, value 가 싸이클을 구성하면 두 집합이 같음
   ex 1) 1 -> 3 -> 1
   ex 2) 5 -> 5
 - 1 ~ n 까지 차례로 확인
   1) 해당 i 에서 DFS 탐색 시작
   2) 탐색하다가 탐색 시작 index i 로 돌아오면, 싸이클을 구성하여 두 집합이 같음
     => 리스트에 탐색 시작 index i 추가

2. 자료구조
 - boolean[]: 방문 확인
 - List<Integer>, ArrayList<Integer>: 싸이클을 구성하는 경우, 탐색 시작 index 저장

3. 시간 복잡도
 - V: n => 최대 100
 - E: 1 (링크 따라 이동하므로, vertex 1개에 edge 1개)
   => index i 를 DFS 한번 수행할 때, 시간 복잡도 = O(V + E) = 101 ~= 대충 100
   => 최대 정수 1 ~ 100 까지 DFS 수행할 때, 총 시간 복잡도 = 100 x 100 = 10,000 << 1억 (1초)
*/

public class Main {
	static int n;				// 1 ~ n 정수
	static int[] numbers;		// 1 ~ n 에 적힌 값들
	static boolean[] check;

	static int startIdx;		// DFS 탐색 시작 정수 번호
	static List<Integer> selected = new ArrayList<>();

	/* idx 번에서 시작하여 싸이클 구성하는지 확인 */
	static void dfs(int idx) {
		check[idx] = true;

		int value = numbers[idx];		// 다음
		if (startIdx == value) {		// 싸이클 구성한 경우, 리스트에 시작 index 추가
			selected.add(startIdx);
			return;
		}

		if (!check[value])
			dfs(value);

//		check[idx] = false;				// 방문 취소
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		n = Integer.parseInt(br.readLine());
		check = new boolean[n + 1];		// [1 ~ n] 사용
		numbers = new int[n + 1];
		for (int i = 1; i <= n; i++)
			numbers[i] = Integer.parseInt(br.readLine());

		for (int i = 1; i <= n; i++) {
			check = new boolean[n + 1];
			if (!check[i]) {
				startIdx = i;
				dfs(i);
			}
		}

		Collections.sort(selected);

		StringBuilder sb = new StringBuilder();
		sb.append(selected.size()).append("\n");
		for (int num : selected)
			sb.append(num).append("\n");

		System.out.println(sb);
	}
}
