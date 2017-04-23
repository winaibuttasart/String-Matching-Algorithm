import java.util.Arrays;

public class BoyerMoore {

    public static int[] buildLast(String pattern) /* Return array storing index of last
occurrence of each ASCII char in pattern. */ {
        int last[] = new int[128]; // ASCII char set
        for (int i = 0; i < 128; i++) {
            last[i] = -1; // initialize array
        }
        for (int i = 0; i < pattern.length(); i++) {
            last[pattern.charAt(i)] = i;
        }
        return last;
    } // end of buildLast()

    public static int bmMatch(String text, String pattern) {
        int last[] = buildLast(pattern);

        int n = text.length();
        int m = pattern.length();
        int i = m - 1;
        if (i > n - 1) {
            return -1; // no match if pattern is
        }// longer than text
        int j = m - 1;
        do {
            if (pattern.charAt(j) == text.charAt(i)) {
                if (j == 0) {
                    return i; // match
                } else { // looking-glass technique
                    i--;
                    j--;
                }
            } else { // character jump technique
                int lo = last[text.charAt(i)]; //last occ
                i = i + m - Math.min(j, 1 + lo);
                j = m - 1;
            }
        } while (i <= n - 1);
        return -1; // no match
    } // end of bmMatch()

    public static void main(String[] args) {

        String txt = "TTATAGATCTCGTATTCTTTTATAGATCTCCTATTCTT";
        String pattern = "TCCTATTCTT";

        System.out.println("Text: " + txt);
        System.out.println("Pattern: " + pattern);
        int posn = bmMatch(txt, pattern);
        if (posn == -1) {
            System.out.println("Pattern not found");
        } else {
            System.out.println("Pattern starts at posn " + posn);
        }
    }

}
