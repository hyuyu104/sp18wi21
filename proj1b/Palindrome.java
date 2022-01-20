public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnItems = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            returnItems.addLast(word.charAt(i));
        }
        return returnItems;
    }

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

    public boolean isPalindrome(String word) {
        Deque<Character> items = wordToDeque(word);
        return isPalindromeHelper(items);
    }

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

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> items = wordToDeque(word);
        return isPalindromeHelper(items, cc);
    }
}
