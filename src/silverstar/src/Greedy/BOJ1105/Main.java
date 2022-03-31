package Greedy.BOJ1105;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 1) L과 R의 자릿 수가 다른 경우
   - 0 출력
 2) L과 R의 자릿 수가 같은 경우
   - L, R 각각 맨 앞의 큰 자릿 수부터 서로 비교
     => 서로 자릿수 값이 다르면, 비교 종료
     => 서로 자릿수 값이 8로 같으면, 8 개수 + 1

2. 자료구조

3. 시간 복잡도
 - O(len)		(len: L 또는 R의 문자열 길이)
   => 최대값 대입: 10
*/

public class Main {
	static String L, R;			// 1 <= L <= R
	static int minCount;		// 출력, 가장 작은 8 개수

	static void solution() {
		// 1) L과 R의 자릿 수가 다른 경우
		if (L.length() != R.length()) {
			minCount = 0;
			return;
		}

		// 2) L과 R의 자릿 수가 같은 경우
		for (int i = 0; i < L.length(); i++) {
			if (L.charAt(i) != R.charAt(i))
				break;
			else if (L.charAt(i) == '8' && R.charAt(i) == '8')
				minCount++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = st.nextToken();
		R = st.nextToken();

		solution();
		System.out.println(minCount);
	}
}
