package String.BOJ9935;
import java.io.*;
import java.util.Stack;

/*
1. 풀이
 1) 입력 문자열을 한 문자씩 읽어가면서, Stack 에 저장
 2) Stack 의 size >= 폭발 문자열 길이인 경우
   - Stack 에 폭발 문자열과 동일한 문자열이 존재하는지 확인

2. 자료구조
 - Stack<Character>: 입력 문자열을 한 문자씩 읽으면서, Stack 에 추가 및 삭제

3. 시간 복잡도
 - 입력 문자열 최대 길이: 10^6 (100만)
   => O(n^2) 미만으로 풀어야 함 (n, n long n 까지 가능)
*/

public class Main_Stack {
	static String input, bomb;			// 입력 문자열, 폭발 문자열
	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		input = br.readLine();
		bomb = br.readLine();

		for (int i = 0; i < input.length(); i++) {
			stack.push(input.charAt(i));

			if (stack.size() >= bomb.length()) {
				boolean isSame = true;
				int idx = stack.size() - bomb.length();

				for (int j = idx; j < stack.size(); j++) {
					if (stack.get(j) != bomb.charAt(j - idx)) {
						isSame = false;
						break;
					}
				}

				if (isSame) {
					for (int j = 0; j < bomb.length(); j++)
						stack.pop();
				}
			}
		}

		if (stack.isEmpty()) {
			System.out.println("FRULA");
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (char ch : stack)
			sb.append(ch);
		System.out.println(sb.toString());
	}
}
