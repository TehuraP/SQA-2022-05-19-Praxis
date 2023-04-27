package at.campus02.sqa.filter;

public class LengthFilter implements Filter {
    private final int minLength;

    public LengthFilter(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean keepWord(String word, Integer count) {
        return word.length() >= minLength;
    }
}
