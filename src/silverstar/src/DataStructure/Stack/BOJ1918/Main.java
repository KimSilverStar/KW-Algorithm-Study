package DataStructure.Stack.BOJ1918;
import java.io.*;
import java.util.Stack;

/*
1. 풀이
 - 입력 중위 표기식을 한 문자씩 차례로 확인

 1) 문자가 알파벳(피연산자)인 경우
   - 출력
 2) 문자가 연산자(+, -, *, /)인 경우
   - 우선순위에 따라 Stack 에서 처리
   - Stack 에서 본인보다 우선순위가 높거나 같은 연산자들을 모두 pop 하여 출력 후,
     본인을 Stack 에 push
     !!! 여는 괄호 '('는 닫는 괄호 ')' 만이 pop 해야하므로, '('가 우선순위 가장 낮음
 3) 여는 괄호 '('
   - Stack 에 push
 4) 닫는 괄호 ')'
   - Stack 에서 여는 괄호 '('가 나올 때까지 pop 하여 출력
 5) 입력 중위 표기식의 모든 문자를 확인 한 후,
    Stack 에 남은 연산자들을 모두 출력

2. 자료구조
 - String: 입력 중위 표기식
 - Stack<Character>

3. 시간 복잡도
*/

public class Main {
	static String expression;		// 입력 중위 표기식
	static Stack<Character> stack = new Stack<>();

	/* 연산자 우선순위 반환 */
	static int getPriority(char op) {
		if (op == '*' || op == '/')
			return 2;
		else if (op == '+' || op == '-')
			return 1;
		else			// 여는 괄호 '('의 우선순위가 가장 낮음
			return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringBuilder sb = new StringBuilder();

		expression = br.readLine();

		// 입력 중위 표기식을 한 문자씩 확인
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (Character.isAlphabetic(ch)) {		// 피연산자 (알파벳)
				sb.append(ch);
			}
			else if (ch == '(') {
				stack.push(ch);
			}
			else if (ch == ')') {
				// Stack 에서 여는 괄호 '('가 나올 때까지 pop 하여 출력
				while (!stack.isEmpty()) {
					if (stack.peek() == '(') {
						stack.pop();			// Stack 에서 여는 괄호 '(' 제거
						break;
					}
					sb.append(stack.pop());		// 연산자들 pop 하여 출력
				}
			}
			else {		// 연산자 (+, -, *, /)
				// 본인보다 우선순위가 같거나 더 높은 Stack 의 연산자들을 pop 하여 출력 후, 본인을 push
				while (!stack.isEmpty()
						&& (getPriority(ch) <= getPriority(stack.peek()))) {
					sb.append(stack.pop());
				}
				stack.push(ch);
			}
		}

		while (!stack.isEmpty())
			sb.append(stack.pop());

		System.out.println(sb.toString());
	}
}
