import java.util.Arrays;

public class KMP {

    static int count = 0;

    public static int[] computeFail(String pattern) {
        int fail[] = new int[pattern.length()];
        fail[0] = 0;
        int m = pattern.length();
        int j = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(j) == pattern.charAt(i)) { //j+1 chars match
                fail[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) // j follows matching prefix
            {
                j = fail[j - 1];
            } else { // no match
                fail[i] = 0;
                i++;
            }
        }
        return fail;
    } // end of computeFail()

    public static int kmpMatch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int fail[] = computeFail(pattern);
        System.out.println("Fail = " + Arrays.toString(fail));

        int i = 0;
        int j = 0;
        while (i < n) {
            count++;
            System.out.println("count = " + count + "  i = " + i + "  j = " + j);
            if (pattern.charAt(j) == text.charAt(i)) {
                if (j == m - 1) {
                    return i - m + 1; // match else
                } else {
                    i++;
                    j++;
                }
            } else if (j > 0) {
                j = fail[j - 1];
            } else {
                i++;
            }
        }
        return -1; // no match
    } // end of kmpMatch()

    public static void main(String[] args) {
        String txt = "TTATAGATCTCGTATTCTTTTATAGATCTCCTATTCTT";
        String pattern = "TCCTATTCTT";

        System.out.println("length = " + pattern.length());
        int posn = kmpMatch(txt, pattern);
        if (posn == -1) {
            System.out.println("Pattern not found");
        } else {
            System.out.println("Pattern starts at posn " + posn);
        }

        System.out.println("Total number of comparisons = " + count);
    }

}
