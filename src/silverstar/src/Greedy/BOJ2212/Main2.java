package Greedy.BOJ2212;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 인접한 센서 간, 거리가 먼 센서들부터 서로 다른 집중국 할당
   => 거리가 먼 센서들부터 분리해나가는 느낌

2. 자료구조
 - Integer[]: 인접한 센서 간의 거리 저장
   => 거리 큰 순으로 정렬하기 위해 Integer[] 사용

3. 시간 복잡도
 - 센서 배열, 센서 거리 배열 정렬: O(2 x n log n)
 - 영역 최소 합 계산: O(k)
*/

public class Main2 {
	static int n, k;				// 센서 개수 n, 집중국 개수 k
	static int[] sensors;			// 각 센서의 위치
	static int minCount;			// 출력: 수신 가능영역 최소합
	static Integer[] distances;		// 인접한 센서 간의 거리 => 큰 순으로 정렬

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		sensors = new int[n];
		for (int i = 0; i < n; i++)
			sensors[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(sensors);		// 센서 정렬

		// 예외 처리) 집중국 개수 k >= 센서 개수 n 인 경우, 센서마다 집중국 설치
		if (k >= n) {
			System.out.println(0);
			return;
		}

		distances = new Integer[n - 1];
		for (int i = 0; i < n - 1; i++)
			distances[i] = Math.abs(sensors[i + 1] - sensors[i]);
		Arrays.sort(distances, (d1, d2) -> d2 - d1);		// 거리 큰 순으로 정렬

		// 거리 큰 순으로 (k-1)개 제외하고, 나머지 모두 더함
		for (int i = k - 1; i < n - 1; i++)
			minCount += distances[i];

		System.out.println(minCount);
	}
}
