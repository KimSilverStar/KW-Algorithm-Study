package DataStructure.HashMap.BOJ1755;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 1) 0 "zero" ~ 9 "nine" 을 String[] 에 저장
 2) for 문으로 m ~ n 사이의 정수들을 한 숫자씩 읽어서,
    정수의 값과 영어로 읽은 값을 Word[] 에 저장
 3) Word[] 를 사전 순으로 정렬
   - implements Comparable<Word>
     => public int compareTo(Word w) 메소드 오버라이딩
 4) 정렬된 Word[] 에서 정수 값들을 차례로 출력

2. 자료구조
 - Word[]: 각 정수들의 정수 값 int, 영어로 읽은 문자열 String 저장

3. 시간 복잡도
 1) 각 정수의 값과 영어로 읽은 문자열을 Word[] 에 저장: O(n)
 2) 정렬: O(n log n)
 3) 정렬된 문자열들을 정수로 출력: O(n)
 - 총 시간 복잡도: O(2n + n log n)		(n: 정수 개수)
   => n 최대값 대략 100 대입: 200 + (100 x 2) = 400 << 2억
*/

class Word implements Comparable<Word> {
	public int number;
	public String strByDigit;

	public Word(int number, String strByDigit) {
		this.number = number;
		this.strByDigit = strByDigit;
	}

	public int compareTo(Word w) {
		return this.strByDigit.compareTo(w.strByDigit);
	}
}

public class Main_Comparable {
	static int m, n;				// m 이상 n 이하의 정수
	static String[] numberStrArr;
	static Word[] words;
	static StringBuilder sb = new StringBuilder();

	static void solution() {
		// 1) 정수 값을 영어 문자열로 입력하여 Word[] 에 저장
		for (int i = m; i <= n; i++) {
			String numStr = String.valueOf(i);					// "80" 형태
			StringBuilder strByDigit = new StringBuilder();		// "eight zero" 형태

			for (int j = 0; j < numStr.length(); j++) {
				int digit = Character.getNumericValue(numStr.charAt(j));
				strByDigit.append(numberStrArr[digit])
						.append(" ");
			}

			words[i - m] = new Word(i, strByDigit.toString());
		}

		// 2) Word[] 를 사전 순으로 정렬
		Arrays.sort(words);

		// 3) Word[] 의 각 정수 값 출력
		int count = 1;
		for (Word w : words) {
			sb.append(w.number).append(" ");

			if (count % 10 == 0)
				sb.append("\n");

			count++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		numberStrArr = new String[] {
				"zero", "one", "two", "three", "four",
				"five", "six", "seven", "eight", "nine"
		};

		words = new Word[n - m + 1];

		solution();
		System.out.println(sb);
	}
}
