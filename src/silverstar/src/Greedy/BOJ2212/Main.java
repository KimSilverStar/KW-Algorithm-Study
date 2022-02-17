package Greedy.BOJ2212;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 인접한 센서 간, 거리가 먼 센서들부터 서로 다른 집중국 할당
   => 거리가 먼 센서들부터 분리해나가는 느낌

2. 자료구조
 - SensorDistance[]: 인접한 센서 간의 거리 저장
   => 거리 큰 순으로 정렬
 - List<Integer>, ArrayList<Integer>: 집중국 설치할 위치 (거리가 먼 인접 센서 분할) 저장

3. 시간 복잡도
 - 센서 배열, 센서 거리 배열 정렬: O(2 x n log n)
 - 영역 최소 합 계산: O(k)
*/

class SensorDistance implements Comparable<SensorDistance> {
	public int sensor1, sensor2;
	public int distance;

	public SensorDistance(int sensor1, int sensor2, int distance) {
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
		this.distance = distance;
	}

	public int compareTo(SensorDistance sd) {
		int distanceDiff = sd.distance - this.distance;
		if (distanceDiff != 0)			// 거리 큰 순으로 정렬
			return distanceDiff;
		else
			return this.sensor1 - sd.sensor1;
	}
}

public class Main {
	static int n, k;				// 센서 개수 n, 집중국 개수 k
	static int[] sensors;			// 각 센서의 위치
	static int minSum;				// 출력: 수신 가능영역 최소합

	static SensorDistance[] sds;	// 인접한 센서 간의 거리 => 큰 순으로 정렬
	static List<Integer> herbList = new ArrayList<>();

	static void solution() {
		// 인접 센서 간 거리 큰 순으로 (k-1)개 선택
		for (int i = 0; i < k - 1; i++)
			herbList.add(sds[i].sensor1);

		// 각 집중국의 영역 계산
		int startIdx = 0;
		for (int herbIdx : herbList) {
			minSum += (sensors[herbIdx] - sensors[startIdx]);
			startIdx = herbIdx + 1;
		}
		minSum += (sensors[n - 1] - sensors[startIdx]);
	}

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

		sds = new SensorDistance[n - 1];
		for (int i = 0; i < n - 1; i++) {
			int distance = Math.abs(sensors[i + 1] - sensors[i]);
			sds[i] = new SensorDistance(i, i + 1, distance);
		}
		Arrays.sort(sds);

		solution();
		System.out.println(minSum);
	}
}
