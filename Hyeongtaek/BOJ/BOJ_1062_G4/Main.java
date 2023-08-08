import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * default letters
     * a, c, i, n, t
     */
    static int defaultLetter = 0b10000010000100000101;
    static int totalLetterFlag = 0;
    static int totalLetterLength = 0, unusedLetterLength = 0;
    static int[] totalLetters, unusedLetters;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] wordFlags;
    static int max = 0;


    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        wordFlags = new int[n];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 4, size = word.length(); j < size - 4; j++) {
                int index = word.charAt(j) - 'a';
                wordFlags[i] |= 1 << index;
            }
        }

        if (k < 5) {
            System.out.println(0);
            return;
        }

        for (int wordFlag : wordFlags) {
            totalLetterFlag |= wordFlag;
        }

        totalLetterFlag &= ~defaultLetter;

        for (int i = 0; i < 26; i++) {
            if ((totalLetterFlag & 1 << i) != 0) {
                totalLetterLength++;
            }
        }

        /* 단어에 사용된 알파벳 (default 제외) */
        totalLetters = new int[totalLetterLength];

        unusedLetterLength = 21 - totalLetterLength;
        /* 단어에 사용되지 않은 알파벳 */
        unusedLetters = new int[unusedLetterLength];

        for (int i = 0, index1 = 0, index2 = 0; i < 26; i++) {
            int totalLettersWithDefault = totalLetterFlag | defaultLetter;
            if ((totalLetterFlag & 1 << i) != 0) {
                totalLetters[index1++] = i;
            } else if ((totalLettersWithDefault & 1 << i) == 0) {
                unusedLetters[index2++] = i;
            }
        }

        /**
         *  nCk에서 k >= n/2 일 경우 nCn-k를 구하는 것이 재귀 깊이에서 효율적.
         */
        if (k >= totalLetterLength + 5) {
            /**
             * 전체 단어를 모두 배우기 위해 필요한 알파벳의 종류가 k보다 적은 경우,
             * 조합을 구할 필요없이 n개의 단어 모두 다 배울 수 있다.
             */
            max = n;
        } else if (k - 5 < totalLetterLength / 2) {
            // k < n/2 일 경우 nCk를 직접 탐색
            combination(0, 0, k - 5, defaultLetter);
        } else {
            // k >= n/2일 경우 nCn-k (배우지 않을 알파벳의 조합)을 탐색
            combinationUnused(0, 0, 26 - k - unusedLetterLength, 0);
        }

        System.out.println(max);

    }

    static void combination(int cnt, int index, int r, int comb) {
        if (cnt == r) {
            int num = 0;
            for (int wordFlag : wordFlags) {
                if ((comb & wordFlag) == wordFlag) {
                    num++;
                }
            }

            max = Math.max(max, num);
            return;
        }

        if (index < totalLetterLength) {
            for (int i = index; i < totalLetterLength; i++) {
                combination(cnt + 1, i + 1, r, comb | 1 << totalLetters[i]);
            }
        } else {
            combination(r, index, r, comb);
        }
    }

    static void combinationUnused(int cnt, int index, int r, int comb) {
        if (cnt == r) {
            /* 여기서 계산된 comb는 배우지 않을 알파벳의 비트 자릿수가 0인 수 */
            comb = ~comb;
            int num = 0;
            for (int wordFlag : wordFlags) {
                if ((comb & wordFlag) == wordFlag) {
                    num++;
                }
            }

            max = Math.max(max, num);
            return;
        }

        for (int i = index; i < totalLetterLength; i++) {
            combinationUnused(cnt + 1, i + 1, r, comb | 1 << totalLetters[i]);
        }
    }
}
