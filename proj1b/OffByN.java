public class OffByN implements CharacterComparator {
    private int distance;

    /* Constructor for OffByN, will take an integer of N. */
    public OffByN(int N) {
        distance = N;
    }

    /* Equal chars will check the whether the difference between x and y equals N. */
    @Override
    public boolean equalChars(char x, char y) {
        if (((x - y) == distance) || ((x - y) == -distance)) {
            return true;
        }
        return false;
    }
}
