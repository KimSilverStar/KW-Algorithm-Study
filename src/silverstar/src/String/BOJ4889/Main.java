package String.BOJ4889;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 안정적인 문자열: 빈 문자열 or 모든 괄호들이 올바르게 매칭된 문자열
 - Stack 으로 입력 문자열의 괄호들을 매칭시킴
   => 남은 문자열들: 불안정한 문자열
 1) 여는 괄호 {
   - Stack 에 push
 2) 닫는 괄호 }
   - Stack 이 not empty 한 경우 (여는 괄호가 있는 경우), Stack 에서 pop
   - Stack 이 empty 한 경우,
     닫는 괄호를 여는 괄호로 바꿔서 Stack 에 push 하고 연산 횟수 + 1
 3) 입력 문자열을 다 확인 후, Stack 의 size / 2 만큼 연산 횟수 증가
   - Stack 에 남은 여는 괄호들 중 절반만 닫는 괄호로 바꾸면 모두 매칭됨

2. 자료구조
 - Stack<Character>: 괄호 쌍 매칭

3. 시간 복잡도
 - 한 문자열 당 O(len)
   => 최대 2,000
*/

public class Main {
	static String str;			// 입력 문자열
	static Stack<Character> stack;

	static int solution(String str) {
		stack = new Stack<>();
		int count = 0;				// 연산 횟수

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (ch == '{')
				stack.push(ch);
			else {		// 닫는 괄호 '}'
				if (!stack.isEmpty())
					stack.pop();
				else {
					// 닫는 괄호를 여는 괄호로 바꾸는 연산 수행
					stack.push('{');
					count++;
				}
			}
		}

		// Stack 에 남은 여는 괄호들 개수 / 2 만큼 연산 추가
		return count + (stack.size() / 2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringBuilder sb = new StringBuilder();

		int index = 1;
		while (true) {
			str = br.readLine();
			if (str.contains("-"))
				break;

			sb.append(index).append(". ");
			sb.append(solution(str)).append("\n");

			index++;
		}

		System.out.println(sb.toString());
	}
}
