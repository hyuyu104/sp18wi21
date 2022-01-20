public class Palindrome {
    /* This method will convert a string into an ArrayDeque. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnItems = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            returnItems.addLast(word.charAt(i));
        }
        return returnItems;
    }

    /* This is the helper method of isPalindrome, will use recursion to
    * check whether "items" is a palindrome. */
    private boolean isPalindromeHelper(Deque<Character> items) {
        Character front = items.removeFirst();
        Character last = items.removeLast();
        if ((front == null) || (last == null)) {
            return true;
        } else if (front != last) {
            return false;
        }
        return isPalindromeHelper(items);
    }

    /* This method will check whether the input string is a palindrome. */
    public boolean isPalindrome(String word) {
        Deque<Character> items = wordToDeque(word);
        return isPalindromeHelper(items);
    }

    /* This is the helper method of overloaded isPalindrome method, will use recursion
    * to check whether "items" is a palindrome according to the rule in cc. */
    private boolean isPalindromeHelper(Deque<Character> items, CharacterComparator cc) {
        Character front = items.removeFirst();
        Character last = items.removeLast();
        if ((front == null) || (last == null)) {
            return true;
        } else if (!cc.equalChars(front, last)) {
            return false;
        }
        return isPalindromeHelper(items, cc);
    }

    /* This method will check whether the input string is a palindrome based on cc. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> items = wordToDeque(word);
        return isPalindromeHelper(items, cc);
    }
}
