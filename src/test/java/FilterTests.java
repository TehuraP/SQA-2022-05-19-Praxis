import at.campus02.sqa.WordCounter;
import at.campus02.sqa.filter.CountFilter;
import at.campus02.sqa.filter.FilterSet;
import at.campus02.sqa.filter.LengthFilter;
import at.campus02.sqa.filter.StopWordFilter;
import com.sun.source.tree.Tree;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class FilterTests {

    @Test
    public void filterAreTrue() {
        FilterSet filterSet = new FilterSet();
        filterSet.addFilter(new StopWordFilter());
        filterSet.addFilter(new CountFilter(1));

      boolean  result = filterSet.keepWord("cat", 1);
        Assert.assertTrue(result);

    }
    @Test
    public void filtersReturnFalse() {
        FilterSet filterSet = new FilterSet();
        filterSet.addFilter(new LengthFilter(20));
        Assert.assertFalse(filterSet.keepWord("manolo",3));
    }
    @Test
    public void testOutputWithValidWord() throws IOException {
        //given
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.merge("hello",10,Integer::sum);

        FilterSet filters = new FilterSet();
        filters.addFilter(new CountFilter(1));
        filters.addFilter(new StopWordFilter());
        filters.addFilter(new LengthFilter(1));

        FileWriter fileWriter = Mockito.mock(FileWriter.class);

        //when
        WordCounter.output(fileWriter, filters, treeMap);

        //then
        Mockito.verify(fileWriter, Mockito.atLeast(1)).write("word,count\n");
        Mockito.verify(fileWriter, Mockito.atLeast(1)).write("hello,10\n");

    }

    @Test
    public void testOutputWithNotValidWord() throws IOException {
        //given
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.merge("he",10, Integer::sum);

        FilterSet filters = new FilterSet();
        filters.addFilter(new CountFilter(1));
        filters.addFilter(new StopWordFilter());
        filters.addFilter(new LengthFilter(3));

        FileWriter fileWriter = Mockito.mock(FileWriter.class);

        //when

        WordCounter.output(fileWriter, filters, treeMap);

        //then
        Mockito.verify(fileWriter, Mockito.atLeast(1)).write("word,count\n");
        Mockito.verify(fileWriter, Mockito.never()).write("hello,10\n");

    }


}
