package DataStructure.Heap.BOJ11286;
import java.io.*;
import java.util.PriorityQueue;

/*
1. 풀이
 - PriorityQueue 에 x 추가 및 삭제
   => 절댓값이 가장 작은 것이 먼저 오도록 정렬
      !!! 절댓값이 같으면, 작은 수가 먼저 오도록 정렬

 1) x != 0 인 경우
   - PriorityQueue 에 x 추가
 2) x == 0 인 경우
   - PriorityQueue 에서 삭제 및 출력
     !!! PriorityQueue 가 empty 하면, 0 출력

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
	static int n;		// 수행할 연산의 개수
	static int x;		// 입력 정수
	static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
		int absDiff = Math.abs(o1) - Math.abs(o2);
		if (absDiff != 0)
			return absDiff;
		else		// 절댓값이 같으면, 작은 순서
			return o1 - o2;
	});

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine());

			if (x != 0)
				pq.add(x);
			else {		// x == 0
				if (!pq.isEmpty())
					sb.append(pq.remove()).append("\n");
				else
					sb.append(0).append("\n");
			}
		}

		System.out.println(sb.toString());
	}
}
