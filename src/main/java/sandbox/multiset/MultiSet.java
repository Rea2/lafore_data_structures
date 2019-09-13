package sandbox.multiset;

import com.google.common.collect.*;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;


public class MultiSet {
    private static Multiset<String> multiset = HashMultiset.create();

    public static void main(String[] args) {
        readFile("C:\\Users\\Yauheni_Raik\\Work\\projects\\lafore\\src\\main\\resources\\words");
        System.out.println(multiset);
        System.out.println("");
        multiset.stream().distinct(). forEach( x-> System.out.println(x + " - " + multiset.count(x)));
    }

    private static void readFile(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))  {
            stream.forEach(s -> addWordsToMultiset(s));
        }
        catch (IOException e)  {
            e.printStackTrace();
        }
    }

    private static void addWordsToMultiset(String line) {
       String[] array =  line.split(" ");
        Arrays.stream(array).forEach( x -> multiset.add(x) );
    }
}
