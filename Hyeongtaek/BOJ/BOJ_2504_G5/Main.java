import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {

        String input = br.readLine();
        boolean wrong = false;
        int value = 0;
        int weight = 1;

        for (int i = 0, length = input.length(); i < length; i++) {
            if (input.charAt(i) == '(') {
                stack.add('(');
                weight *= 2;
            } else if (input.charAt(i) == '[') {
                stack.add('[');
                weight *= 3;
            } else if (input.charAt(i) == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    wrong = true;
                    break;
                }

                if (input.charAt(i - 1) == '(') {
                    value += weight;
                }
                weight /= 2;
            } else if (input.charAt(i) == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    wrong = true;
                    break;
                }

                if (input.charAt(i - 1) == '[') {
                    value += weight;
                }
                weight /= 3;
            } else {
                wrong = true;
                break;
            }

        }

        if (wrong || !stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(value);
        }
    }
}
