package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class SortingDriver {
    public static final Scanner INPUT = new Scanner(System.in);

    private static char algorithm = 'z';
    private static boolean menu = true;

    public static void main(String[] args) {

        System.out.println(args);

        while (menu) {
            System.out.println(
                    "selection-sort (s) merge-sort (m) heap-sort (h) quick-sort-first (q) quick-sort-random (r)");
            algorithm = INPUT.next().charAt(0);

            if (algorithm != 's' && algorithm != 'm' && algorithm != 'h' && algorithm != 'q' && algorithm != 'r') {
                System.out.print("Please enter a valid input");
            } else {
                menu = false;
            }
        }

        try {

            int[] sorterArray;

            if (args.length > 0) {
                File input = new File(args[0]);
                Scanner fileRead = new Scanner(input);

                sorterArray = new int[10000];
                int index = 0;

                while (fileRead.hasNextInt() && index < sorterArray.length) {
                    sorterArray[index] = fileRead.nextInt();
                    index++;
                }
                fileRead.close();
            } else {
                sorterArray = createRandomArray();
            }

            Sorting sorter = new Sorting();

            String sort = "";

            switch (algorithm) {
                case 's':
                    sort = "Selection-sort";
                    sorter.selectionSort(sorterArray);
                    break;
                case 'm':
                    sort = "Merge-sort";
                    sorter.mergeSort(sorterArray, 0, sorterArray.length - 1);
                    break;
                case 'h':
                    sort = "Heap-sort";
                    sorter.heapSort(sorterArray);
                    break;
                case 'q':
                    sort = "Quick-sort-fp";
                    sorter.quickSortFirst(sorterArray, 0, sorterArray.length - 1);
                    break;
                case 'r':
                    sort = "Quick-sort-rp";
                    sorter.quickSortRandom(sorterArray, 0, sorterArray.length - 1);
                    break;
                default:
                    break;
            }

            sorter.printArray(sorterArray);

            System.out.println("#" + sort + " comparisons: " + sorter.getCompareCount());

        } catch (FileNotFoundException e) {
            System.out.println("Error when reading file!\nError Message: " + e.getMessage());
        } // try-catch

        INPUT.close();
    }

    public static int[] createRandomArray() {

        System.out.print("Please enter the size of the array you wish to create: ");
        int arraySize = INPUT.nextInt();

        int[] randomArray = new int[arraySize];

        Random r = new Random();

        for (int i = 0; i < arraySize; i++) {
            randomArray[i] = r.nextInt();
        }
        return randomArray;
    }
}
