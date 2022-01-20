import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("niuuin"));
        assertFalse(palindrome.isPalindrome("Niuuin"));
        assertTrue(palindrome.isPalindrome("niunuin"));
        assertFalse(palindrome.isPalindrome("Niunuin"));
        assertTrue(palindrome.isPalindrome("n"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testIsPalindromeCC() {
        assertTrue(palindrome.isPalindrome("abcdcb", new OffByOne()));
        assertFalse(palindrome.isPalindrome("abcDcb", new OffByOne()));
        assertTrue(palindrome.isPalindrome("abccdcb", new OffByOne()));
        assertFalse(palindrome.isPalindrome("abccDcb", new OffByOne()));
        assertTrue(palindrome.isPalindrome("n", new OffByOne()));
        assertTrue(palindrome.isPalindrome("", new OffByOne()));
    }
}
