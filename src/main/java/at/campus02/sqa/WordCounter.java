package at.campus02.sqa;

import at.campus02.sqa.filter.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class WordCounter {

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL pathURL = WordCounter.class.getResource("/data.txt");
        assert pathURL != null;
        File data = new File(pathURL.toURI());
        FilterSet filters = new FilterSet();
        filters.addFilter(new CountFilter(50));
        filters.addFilter(new StopWordFilter());
        filters.addFilter(new LengthFilter(3));
        WordCounter.process(data, "count.csv", filters);
    }

    public static void process(File file, String outPath, FilterSet filters) throws IOException {
        FileWriter output = new FileWriter(outPath);
        TreeMap<String, Integer> result = input(file);
        output(output, filters, result);
    }

     static TreeMap<String, Integer> input(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("[\\W_\\s]");
        // Using a TreeMap instead of a HashMap, because that way we have sorted keys.
        // Nicer for printing.
        TreeMap<String, Integer> result = new TreeMap<>();
        while (scanner.hasNext()) {
            String word = scanner.next();
            word = word.toLowerCase();
            result.merge(word, 1, Integer::sum);
        }
        return result;
    }

    public static void output(FileWriter output, FilterSet filters, TreeMap<String, Integer> result) throws IOException {
        output.write("word,count\n");
        for (String key : result.keySet()) {
            Integer count = result.get(key);
            if (filters.keepWord(key, count)) {
                output.write(key + "," + count + "\n");
            }
        }
        output.close();
    }
}
