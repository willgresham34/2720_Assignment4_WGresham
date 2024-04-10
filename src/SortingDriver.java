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

            int[] selectionArray = new int[10000];
            int index = 0;

            while (fileRead.hasNextInt() && index < selectionArray.length) {
                selectionArray[index] = fileRead.nextInt();
                index++;
            } // while to add elements to the selectionArray

            Sorting selection = new Sorting();

            String sort = "";

            switch (algorithm) {
                case 's':
                    sort = "Selection-sort";
                    selection.selectionSort(selectionArray);
                    break;
                case 'm':
                    sort = "Merge-sort";
                    selection.mergeSort(selectionArray, 0, selectionArray.length - 1);
                    break;
                case 'h':
                    sort = "Heap-sort";
                    selection.heapSort(selectionArray);
                    break;
                case 'q':
                    sort = "Quick-sort-fp";
                    selection.quickSortFirst(selectionArray, 0, selectionArray.length - 1);
                    break;
                case 'r':
                    sort = "Quick-sort-rp";
                    selection.quickSortRandom(selectionArray, 0, selectionArray.length - 1);
                    break;
                default:
                    // Handle the case where algorithm is not any of the specified values
                    break;
            }

            selection.printArray(selectionArray);

            System.out.println("#" + sort + " comparisons: " + selection.getCompareCount());

            fileRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error when reading file!\nError Message: " + e.getMessage());
        } // try-catch

        INPUT.close();
    }
}
