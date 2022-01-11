package DataStructure.Deque.BOJ2346;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

/*
1. 풀이
 - Deque 에 (풍선 번호, 풍선에 적힌 값) 쌍을 저장
 1) 터뜨린 풍선의 값 v > 0 인 경우
   - Deque 에서 (v-1) 개의 풍선을 앞에서 꺼내서, 뒤로 추가
   - 이후, 맨 앞 풍선을 터뜨림
 2) 터뜨린 풍선의 값 v < 0 인 경우
   - Deque 에서 (-v-1) 개의 풍선을 뒤에서 꺼내서, 앞으로 추가
   - 이후, 맨 뒤 풍선을 터뜨림

2. 자료구조
 - Deque<Balloon>: ArrayDeque<>
   => 단순히 맨 앞 / 뒤에서 item 을 꺼내고 추가하는 작업만 하면,
      구현체로 LinkedList 보다 ArrayDeque 이 메모리 효율적
   !!! Deque 의 LinkedList 는 이중 연결 리스트로 메모리 사용 많음

3. 시간 복잡도
 - while 문으로 Deque 이 empty 할 때까지 반복: O(n)
 - 반복 1회에서 deque.remove() 횟수: 총 n번	(n: 풍선에 적힌 값의 절대값)
 => 총 O(n^2)
 => n 최대값 대입: 1,000 * 1,000 = 10^6 << 2억 (2초)
*/

class Balloon {
	private int id;			// 풍선 번호
	private int value;		// 풍선에 적힌 값

	public Balloon(int id, int value) {
		this.id = id;
		this.value = value;
	}
	public int getId() { return id; }
	public int getValue() { return value; }
}

public class Main {
	static int n;		// 풍선 개수
	static Deque<Balloon> deque = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int value = Integer.parseInt(st.nextToken());
			deque.addLast(new Balloon(i, value));
		}

		StringBuilder sb = new StringBuilder();
		Balloon removed = deque.removeFirst();		// 처음은 맨 앞 풍선 제거
		sb.append(removed.getId()).append(" ");

		int value = removed.getValue();

		while (!deque.isEmpty()) {
			if (value > 0) {
				for (int i = 1; i < value; i++) {
					removed = deque.removeFirst();		// 맨 앞에서 v-1 개 꺼내서 뒤로 추가
					deque.addLast(removed);
				}
				removed = deque.removeFirst();			// 이후, 맨 앞에서 1개 꺼내서 제거
			}
			else if (value < 0) {
				for (int i = 1; i < -value; i++) {
					removed = deque.removeLast();		// 맨 뒤에서 -(v-1) 개 꺼내서 앞으로 추가
					deque.addFirst(removed);
				}
				removed = deque.removeLast();			// 이후, 맨 뒤에서 1개 꺼내서 제거
			}

			value = removed.getValue();
			sb.append(removed.getId()).append(" ");
		}

		System.out.println(sb.toString());
	}
}
