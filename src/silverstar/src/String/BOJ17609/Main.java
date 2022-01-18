package String.BOJ17609;
import java.io.*;

/*
- 회문: 0, 유사 회문: 1, 둘 다 아니면: 2

1. 아이디어
 1) 단어 문자열에서 [i], [len-1-i] 비교
   - 모두 같으면 회문
   - 다르면, 유사 회문인지 확인
 2) 유사 회문 판별
   - [i] 문자 삭제 후, 다시 회문인지 확인
   - [len-1-i] 문자 삭제 후, 다시 회문인지 확인
   => 둘 중 하나라도 회문이라고 판단되면, 해당 단어 문자열은 유사 회문
   => 둘 다 회문이 아니라고 판단되면, 해당 단어 문자열은 회문, 유사 회문도 아님

2. 자료구조
 - String[]: 입력 단어들 저장

3. 시간 복잡도
 - 1개 단어가 회문인지 확인: O(len / 2)	(len: 단어 문자열 길이)
 - 1개 단어가 회문이 아니여서 유사 회문인지 확인
   => 회문인지 확인 2번 수행 (맨 처음 회문 확인 후, 한 문자 삭제 후 다시 확인)
   => O(2 x len / 2) = O(len)
 - 최악의 총 시간 복잡도: O(t x len) = 30 x 10^5 = 3,000,000 << 1억 (1초)
*/

public class Main {
	static int t;				// 입력 단어 개수
	static String[] words;		// 입력 단어들

	static int solution(String word) {
		for (int i = 0; i <= word.length() / 2; i++) {
			// 회문이 아닌 경우 => 유사 회문인지 확인
			if (word.charAt(i) !=
					word.charAt(word.length() - 1 - i)) {
				// [i] 문자 삭제 후, 유사 회문인지 확인
				String removed = word.substring(0, i) + word.substring(i + 1);
				if (isPalindrome(removed))
					return 1;

				// [len - 1- i] 문자 삭제 후, 유사 회문인지 확인
				removed = word.substring(0, word.length() - 1 - i) + word.substring(word.length() - i);
				if (isPalindrome(removed))
					return 1;

				return 2;		// 회문, 유사 회문 둘 다 아닌 경우
			}
		}
		return 0;
	}

	static boolean isPalindrome(String word) {
		for (int i = 0; i <= word.length() / 2; i++) {
			if (word.charAt(i) !=
					word.charAt(word.length() - 1 - i))
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringBuilder sb = new StringBuilder();

		t = Integer.parseInt(br.readLine());
		words = new String[t];
		for (int i = 0; i < t; i++)
			words[i] = br.readLine();

		for (String word : words)
			sb.append(solution(word)).append("\n");

		System.out.println(sb.toString());
	}
}
