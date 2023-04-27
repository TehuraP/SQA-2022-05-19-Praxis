package at.campus02.sqa.filter;

import java.util.HashSet;

public class StopWordFilter implements Filter {
    public final HashSet<String> stopWords;

    public StopWordFilter() {
        stopWords = new HashSet<>();
        stopWords.add("to");
        stopWords.add("the");
        stopWords.add("he");
        stopWords.add("she");
        stopWords.add("and");
        stopWords.add("of");
    }

    @Override
    public boolean keepWord(String word, Integer count) {
        return !stopWords.contains(word);
    }
}
