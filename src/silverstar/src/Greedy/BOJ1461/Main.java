package Greedy.BOJ1461;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 책을 모두 정리한 후, 다시 원점 0으로 돌아올 필요 X
   => 원점 0으로부터 가장 먼 위치의 책 m권을 마지막에 정리하고 종료
 1) 각 책의 위치 리스트를 거리가 먼(절댓값이 큰) 순으로 정렬
   - 음수 위치 리스트, 양수 위치 리스트 각각 나누어 저장 및 정렬
 2) 거리가 가장 먼 m개 책은 가장 마지막에 정리하고 종료하므로, 단순히 + 편도 거리
 3) 이후, 나머지 책들은 거리 먼 순으로 + 왕복 거리 (+ 2배 거리)

2. 자료구조
 - ArrayList<Integer> 2개: 음수, 양수 위치 리스트

3. 시간 복잡도
 - 2개 리스트 정렬: O(2 x n log_2 n)
   => n 최대값 대입: 2 x 50 log_2 50 ~= 100 x (2 + 3) = 500
*/

public class Main {
	static int n, m;			// 책 개수 n, 한 번에 들 수 있는 책 개수 m
	static List<Integer> plusPosList = new ArrayList<>();
	static List<Integer> minusPosList = new ArrayList<>();
	static int maxDistance;		// 가장 먼 거리
	static int minStep;			// 출력, 최소 이동 거리

	static void solution() {
		// 2) 거리가 가장 먼 m개 책은 가장 마지막에 정리하고 종료하므로, 단순히 + 편도 거리
		// 3) 이후, 나머지 책들은 거리 먼 순으로 + 왕복 거리 (+ 2배 거리)
		while (!plusPosList.isEmpty()) {
			// 거리 먼 순으로 m개 책 정리
			for (int i = 0; i < m; i++) {
				if (plusPosList.isEmpty())
					break;

				if (i == 0)
					minStep += (plusPosList.get(0) * 2);
				plusPosList.remove(0);
			}
		}

		while (!minusPosList.isEmpty()) {
			// 거리 먼 순으로 m개 책 정리
			for (int i = 0; i < m; i++) {
				if (minusPosList.isEmpty())
					break;

				if (i == 0)
					minStep += (-minusPosList.get(0) * 2);
				minusPosList.remove(0);
			}
		}

		minStep -= maxDistance;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			int position = Integer.parseInt(st.nextToken());

			if (position > 0)
				plusPosList.add(position);
			else
				minusPosList.add(position);

			if (maxDistance < Math.abs(position))
				maxDistance = Math.abs(position);
		}

		// 1) 각 책의 위치 리스트를 거리가 먼(절댓값이 큰) 순으로 정렬
		Collections.sort(plusPosList, Collections.reverseOrder());
		Collections.sort(minusPosList);

		solution();
		System.out.println(minStep);
	}
}
