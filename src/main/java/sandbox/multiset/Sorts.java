package sandbox.multiset;


import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class Sorts {
    static int[] array;

    public static void main(String[] args) {
        array = IntStream.generate(() -> new Random().nextInt(100)).limit(20).toArray();

        System.out.println(array.length);
        printArray(array);

        array =  insertionSort(array);

        System.out.println(array.length);
        printArray(array);
    }

    public static void sort () {
        array = IntStream.generate(() -> new Random().nextInt(100)).limit(20).toArray();

        System.out.println(array.length);
        printArray(array);

        array =  insertionSort(array);

        System.out.println(array.length);
        printArray(array);
    }

    public static int[] bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++ ) {
            for(int j = 0; j < arr.length - i - 1; j++){
                if (arr[j] > arr[j+1] ) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] selectSort(int[] arr) {
        for(int i = 0; i < arr.length; i++ ) {
            int min = arr[i];
            int indexMin =  i;

            for(int j = i+1; j < arr.length; j++){
                if (arr[j] < min ) {
                    indexMin = j;
                    min= arr[j];
                }
            }
            int temp = arr [i];
            arr[i] = min;
            arr[indexMin] = temp;
        }
        return arr;
    }

    public static int[] insertionSort(int[] arr) {

        for (int i = 1; i< arr.length; i++ ) {
            int temp = arr[i];
                int j = i;
                while (j > 0 && arr[j-1] >= temp) {
                    arr[j] = arr[j-1];
                    j--;
                }
                arr[j] = temp;



        }
    return arr;
    }

    private  static void  printArray( int[] array) {
        Arrays.stream(array).forEach(x -> System.out.print (x + " "));
        System.out.println("");
    }

}
