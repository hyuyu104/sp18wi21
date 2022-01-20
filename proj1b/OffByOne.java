public class OffByOne implements CharacterComparator {

    /* equalChars checks whether x and y has a difference of 1. */
    @Override
    public boolean equalChars(char x, char y) {
        if (((x - y) == 1) || ((x - y) == -1)) {
            return true;
        }
        return false;
    }
}
