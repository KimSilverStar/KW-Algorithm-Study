package DataStructure.PriorityQueue.BOJ12018;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
1. 풀이
 1) 각 과목에서 다른 학생들이 투자한 마일리지 배열을 높은 순으로 정렬
 2) 정렬된 투자 마일리지 배열에서 수강 가능 인원에 걸친 학생과 똑같은 마일리지를 투자
   - PriorityQueue 에 동일 마일리지로 추가
   - PriorityQueue 에 각 과목 마다 수강 가능한 최소 투자 마일리지들 저장됨
 3) 모든 과목을 확인한 후, PriorityQueue 에서 최소 투자 마일리지를
    낮은 순으로 뽑아서 누적해나감
   - 주어진 마일리지 내에서 누적

2. 자료구조
 - Integer[]: 각 과목에서 학생들이 투자한 마일리지 배열
   => 높은 순으로 정렬하기 위해 int[]가 아닌 Integer[] 사용
 - PriorityQueue<Integer>: 각 과목 마다 최소 투자 마일리지 저장
   => 낮은 순으로 정렬

3. 시간 복잡도
 1) 마일리지 배열 정렬 입력 후 정렬
   - 한 과목에서 마일리지 배열 정렬: O(P_i + P_i log P_i)
   => 모든 과목에 대해 마일리지 배열 정렬: n(P_i + P_i log P_i)
   => 입력 n, P_i 최대값 대입: 100 x 100 log 100 = 20,000
  2) PriorityQueue 에 각 과목에 대해 최소 투자 마일리지 추가 (한 과목에서 pq.add() 한번 수행)
   - log 1 + log 2 + ... + log n = log(n!) = n log n	(n: 전체 과목 수)
   => 입력 n 최대값 대입: 100 log 100 = 200
 3) PriorityQueue 모두 저장한 후, 투자할 과목 선택 (pq.remove())
   - 대충 최대로 모든 과목 투자 가능하다고 가정, n번 pq.remove() 수행
   - log n + log n-1 + ... + log 1 = log(n!) = n log n
   => 입력 n 최대값 대입: 100 log 100 = 200
 => 총 시간 복잡도 = 20,000 + 200 + 200 = 20,400 << 1억 (1초)
*/

public class Main {
	static int n;				// 전체 과목 수
	static int m;				// 주어진 마일리지
	static int numOfStudent, numOfPass;		// 각 과목에 수강 신청한 학생 수, 수강 가능 인원
	static Integer[] mArr;		// 각 과목에서 학생들이 투자한 마일리지 배열 => 높은 순으로 정렬
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			numOfStudent = Integer.parseInt(st.nextToken());
			numOfPass = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			mArr = new Integer[numOfStudent];
			for (int j = 0; j < numOfStudent; j++)
				mArr[j] = Integer.parseInt(st.nextToken());

			// 1) 수강 신청 인원이 넉넉한 경우: 1 투자
			if (numOfStudent < numOfPass) {
				pq.add(1);
				continue;
			}

			// 2) 수강 신청 인원이 넉넉하지 않은 경우
			// 다른 학생들의 투자 마일리지 배열을 높은 순으로 정렬
			Arrays.sort(mArr, (m1, m2) -> m2 - m1);
			pq.add(mArr[numOfPass - 1]);
		}

		int maxNum = 0;
		int usedMileage = 0;
		while (!pq.isEmpty()) {
			if (usedMileage + pq.peek() > m)
				break;

			usedMileage += pq.remove();
			maxNum++;
		}

		System.out.println(maxNum);
	}
}
