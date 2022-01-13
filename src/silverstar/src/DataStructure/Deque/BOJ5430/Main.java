package DataStructure.Deque.BOJ5430;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

/*
1. 풀이
 1) 함수 R => isReversed flag 변수 값 변경
 2) 함수 D
   - isReversed == true 인 경우, Deque 에서 removeLast()
   - isReversed == false 인 경우, Deque 에서 removeFirst()
   !!! Deque 이 empty 하면, "error" 출력 후 해당 테스트 케이스 종료
 => 수행할 함수들이 끝난 후, 마지막 출력은
   - isReversed == true 인 경우, Deque 의 앞에서부터 출력
   - isReversed == false 인 경우, Deque 의 뒤에서부터 출력

2. 자료구조
 - Deque<Integer>, ArrayDeque<Integer>: 입력 배열 저장 후 함수 수행
 - boolean: R 함수 상태 여부

3. 시간 복잡도
 1) 문자열 파싱
   - 테스트 케이스의 입력 배열 한 번 파싱: 대략 O(3n)		(n: 배열 원소 개수)
     (3n: 배열 원소가 최대 100 => 문자열 "100" 으로 3개 문자 파싱)
   => 전체 테스트 케이스에서 문자열 파싱: 대략 O(3 x t x n)
 2) 입력 함수 수행
   - 테스트 케이스 한번: O(p)		(p: 함수 문자열 길이)
   => 전체 테스트 케이스에서 입력 함수 수행: O(t x p)
 => 총 시간 복잡도: O(3 x t x n) + O(t x p)
    = t(3n + p)
    => t, n, p 최대값 대입: 100(3 x 10^5 + 10^5)
       = 30,100,000 << 1억 (1초)
*/

public class Main {
	static int t;			// 테스트 케이스 개수
	static String p;		// 수행할 함수들
	static int n;			// 배열 원소 개수
	static Deque<Integer> deque;		// 입력 배열
	static boolean isReversed;		// R 함수 상태 여부

	static StringTokenizer st;

	/* 입력 배열 문자열 파싱하여 Deque 에 배열 원소 저장 */
	static void parseArr() {
		StringBuilder parsedNum;

		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			parsedNum = new StringBuilder();		// 숫자 파싱하여 저장

			for (int i = 0; i < token.length(); i++) {
				if (Character.isDigit(token.charAt(i)))
					parsedNum.append(token.charAt(i));
			}

			if (parsedNum.length() != 0) {
				int num = Integer.parseInt(parsedNum.toString());
				deque.addLast(num);
			}
		}
	}

	static String ac() {
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == 'R')
				isReversed = !isReversed;
			else {			// 'D' 함수
				if (!deque.isEmpty()) {
					if (isReversed)
						deque.removeLast();
					else
						deque.removeFirst();
				}
				else		// deque 이 empty 한 경우
					return "error";
			}
		}

		output.append("[");

		while (!deque.isEmpty()) {
			int popped = isReversed ?
					deque.removeLast() : deque.removeFirst();
			output.append(popped);

			if (!deque.isEmpty())
				output.append(",");
		}

		output.append("]");
		return output.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringBuilder sb = new StringBuilder();		// 전체 테스트 케이스 결과 저장

		t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			p = br.readLine();
			n = Integer.parseInt(br.readLine());		// 배열 원소 개수

			deque = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine(), ",");		// 콤마 기준으로 토큰화
			parseArr();

			isReversed = false;
			sb.append(ac()).append("\n");
		}

		System.out.println(sb.toString());
	}
}
