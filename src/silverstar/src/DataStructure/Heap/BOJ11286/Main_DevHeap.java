package DataStructure.Heap.BOJ11286;
import DataStructure.Heap.AbsHeap;
import java.io.*;

/*
1. 풀이
 - AbsHeap 구현
   => 배열 기반, add(new item), remove() 구현

2. 자료구조
 - AbsHeap: 구현한 절댓값 힙

3. 시간 복잡도
 - PriorityQueue / Heap 의 시간 복잡도
   => 삽입 / 삭제: O(log n)		(n: 노드 개수)
 - 최대 총 n번 삽입 / 삭제 발생
   => 대충 최대 n log n
   => n 최대값 대입: 10^5 x log 10^5 = 5 x 10^5 << 1억 (1초)
*/

public class Main_DevHeap {
	static int n;			// 수행할 연산 개수
	static int x;			// 입력 정수 x
	static AbsHeap absHeap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		absHeap = new AbsHeap(n + 1);
		
		for (int i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine());
			
			if (x != 0)
				absHeap.add(x);
			else {		// x == 0
				if (!absHeap.isEmpty())
					sb.append(absHeap.remove()).append("\n");
				else
					sb.append(0).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
