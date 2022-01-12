package DataStructure.Heap.BOJ1927;
import java.io.*;
import java.util.PriorityQueue;

/*
1. 풀이
 1) x > 0 인 경우
   - PriorityQueue 에 x 추가
     => 최소값이 먼저 오도록 정렬
 2) x == 0 인 경우
   - PriorityQueue 가 not empty => PriorityQueue 에서 remove 및 출력
   - PriorityQueue 가 empty => 0 출력

2. 자료구조
 - PriorityQueue<Integer>

3. 시간 복잡도
 - PriorityQueue / Heap 의 시간 복잡도
   => 삽입 / 삭제: O(log n)		(n: 노드 개수)
 - 최대 총 n번 삽입 / 삭제 발생
   => 대충 최대 n log n
   => n 최대값 대입: 10^5 x log 10^5 = 5 x 10^5 << 1억 (1초)
*/

public class Main_PriorityQueue {
	static int n;		// 수행할 연산 개수
	static int x;		// 연산 정보 정수 (0 또는 자연수)
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine());

			if (x > 0)
				pq.add(x);
			else if (x == 0) {
				if (!pq.isEmpty())
					sb.append(pq.remove()).append("\n");
				else
					sb.append(0).append("\n");
			}
		}

		System.out.println(sb.toString());
	}
}
