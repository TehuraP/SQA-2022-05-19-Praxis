package at.campus02.sqa.filter;

public class CountFilter implements Filter {
    private final Integer minCount;

    public CountFilter(Integer minCount) {
        this.minCount = minCount;
    }

    @Override
    public boolean keepWord(String word, Integer count) {
        return count >= minCount;
    }
}
