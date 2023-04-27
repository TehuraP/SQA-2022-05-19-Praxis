package at.campus02.sqa.filter;

import java.util.ArrayList;

public class FilterSet {
    private final ArrayList<Filter> filters = new ArrayList<>();

    /**
     * Adds a new word filter to the FilterSet
     * @param filter Word filter
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    /**
     * Checks all filters in the FilterSet in sequence.
     * If one filter returns false, then the function returns false.
     * The order of filters is irrelevant.
     * @param word Word to check
     * @param count Associated word count
     * @return true if all filters returned true, false otherwise
     */
    public boolean keepWord(String word, Integer count) {
        for (Filter filter : filters) {
            if (!filter.keepWord(word, count)) {
                return false;
            }
        }
        return true;
    }
}
