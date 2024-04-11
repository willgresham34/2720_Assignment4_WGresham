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
            File input = new File("resources/ordered.txt");
            Scanner fileRead = new Scanner(input);

            int[] sorterArray = new int[10000];
            int index = 0;

            while (fileRead.hasNextInt() && index < sorterArray.length) {
                sorterArray[index] = fileRead.nextInt();
                index++;
            } // while to add elements to the sorterArray

            Sorting sorter = new Sorting();

            String sort = "";

            switch (algorithm) {
                case 's':
                    sort = "sorter-sort";
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
                    // Handle the case where algorithm is not any of the specified values
                    break;
            }

            sorter.printArray(sorterArray);

            System.out.println("#" + sort + " comparisons: " + sorter.getCompareCount());

            fileRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error when reading file!\nError Message: " + e.getMessage());
        } // try-catch

        INPUT.close();
    }
}
