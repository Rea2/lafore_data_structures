package multiset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sandbox.multiset.Sorts;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSorts {

    private int[] initialArray;

    @BeforeEach
    public void beforeAll () {
        initialArray =  IntStream.generate(() -> new Random().nextInt(100)).limit(20).toArray();
    }

    @Test
    public void  testBubbleSort () {
        int[] expectedArray =  initialArray;
        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(shuffleArray(expectedArray), Sorts.bubbleSort(initialArray));
        Assertions.assertEquals(Arrays.hashCode(expectedArray), Arrays.hashCode(Sorts.selectSort(initialArray)));
        Assertions.assertNotEquals(Arrays.hashCode( shuffleArray(expectedArray)), Arrays.hashCode(Sorts.bubbleSort(initialArray)));
    }

    @Test
    public void  testSelectSort () {
        int[] expectedArray =  initialArray;
        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, Sorts.selectSort(initialArray));
        Assertions.assertEquals(Arrays.hashCode(expectedArray), Arrays.hashCode(Sorts.selectSort(initialArray)));
        Assertions.assertNotEquals(Arrays.hashCode( shuffleArray(expectedArray)), Arrays.hashCode(Sorts.selectSort(initialArray)));
    }

    @Test
    public void  testInsertionSort () {
        int[] expectedArray =  initialArray;
        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, Sorts.insertionSort(initialArray));
        Assertions.assertEquals(Arrays.hashCode(expectedArray), Arrays.hashCode(Sorts.insertionSort(initialArray)));
        Assertions.assertNotEquals(Arrays.hashCode( shuffleArray(expectedArray)), Arrays.hashCode(Sorts.insertionSort(initialArray)));
    }

    private int[] shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int index1 = random.nextInt(array.length);
            int index2 = random.nextInt(array.length);

            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
        return array;
    }

}
