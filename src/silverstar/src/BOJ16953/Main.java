package BOJ16953;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - BFS
 - Queue 에 연산 결과 값 추가
 - 연산 결과 값 == B 인 경우, 탐색 종료
 - 연산 결과 값 > B 인 경우, 탐색 종료 (만들 수 없는 경우)

2. 자료구조
 - Queue<Pair>
   => Pair: 연산 결과 값, 연산 횟수 저장

3. 시간 복잡도
*/

class Pair {
	private long value;
	private int opCount;

	public Pair(long opValue, int opCount) {
		this.value = opValue;
		this.opCount = opCount;
	}
	public long getValue() { return value; }
	public int getOpCount() { return opCount; }
}

public class Main {
	static long a, b;
	static Queue<Pair> queue = new LinkedList<>();

	static int bfs() {
		while (!queue.isEmpty()) {
			Pair current = queue.remove();
			long value = current.getValue();
			int opCount = current.getOpCount();

			long value1 = value * 2;
			if (value1 == b)
				return opCount + 1;
			else if (value1 < b)		// 연산 결과 값 > b 이면, Queue 에 추가 X
				queue.add(new Pair(value1, opCount + 1));

			long value2 = (value * 10) + 1;
			if (value2 == b)
				return opCount + 1;
			else if (value2 < b)		// 연산 결과 값 > b 이면, Queue 에 추가 X
				queue.add(new Pair(value2, opCount + 1));
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());

		queue.add(new Pair(a, 1));
		System.out.println(bfs());
	}
}
