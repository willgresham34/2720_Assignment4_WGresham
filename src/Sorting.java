package src;

import java.util.Random;

class Sorting {

    private long compareCount;

    public Sorting() {
        this.compareCount = 0;
    }

    public long getCompareCount() {
        return this.compareCount;
    }

    /*
     * Selection sort method
     * 
     * This method is from https://geeksforgeeks.org/selection-sort/
     */

    public void selectionSort(int arr[]) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
                compareCount++;
            }
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * The following two methods make up merge sort
     * 
     * They are borrowed from https://www.geeksforgeeks.org/merge-sort/
     */

    public void mergeSort(int arr[], int l, int r) {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }

        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
            compareCount++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            // compareCount++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            // compareCount++;
        }
    }

    /*
     * The following two methods are Heap sort
     * 
     * They are provided from https://www.geeksforgeeks.org/heap-sort/
     */

    public void heapSort(int arr[]) {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(arr, N, i);
        }

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public void heapify(int arr[], int N, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest]) {
            largest = l;
            compareCount++;
        }
        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest]) {
            largest = r;
            compareCount++;
        }
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }

    /*
     * Below are the methods for quick sort
     * 
     * these methods are borrowed from https://www.geeksforgeeks.org/quick-sort/
     */

    // This first part is the implementation as first as pivot

    public int partitionFirst(int arr[], int low, int high) {
        // First element as pivot
        int pivot = arr[low];
        int st = low; // st points to the starting of array
        int end = high; // end points to the ending of the array
        int k = high;
        for (int i = high; i > low; i--) {
            if (arr[i] > pivot) {
                swap(arr, i, k--);
            }
            compareCount++;
        }
        swap(arr, low, k);

        // As we got pivot element index is end
        // now pivot element is at its sorted position
        // return pivot element index (end)
        return k;
    }

    // Function to swap two elements
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * The main function that implements QuickSort
     * arr[] --> Array to be sorted,
     * low --> Starting index,
     * high --> Ending index
     */
    void quickSortFirst(int arr[], int low, int high) {
        // If low is lesser than high
        if (low < high) {
            // idx is index of pivot element which is at its
            // sorted position
            int idx = partitionFirst(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSortFirst(arr, low, idx - 1);
            quickSortFirst(arr, idx + 1, high);
        }
        compareCount++;
    }

    public int partitionRandom(int arr[], int low, int high) {
        // pivot is chosen randomly
        random(arr, low, high);
        int pivot = arr[high];

        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            compareCount++;
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public void random(int arr[], int low, int high) {

        Random rand = new Random();
        int pivot = rand.nextInt(high - low) + low;

        int temp = arr[pivot];
        arr[pivot] = arr[high];
        arr[high] = temp;
    }

    public void quickSortRandom(int arr[], int low, int high) {
        if (low < high) {
            /*
             * pi is partitioning index, arr[pi] is
             * now at right place
             */
            int pi = partitionRandom(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSortRandom(arr, low, pi - 1);
            quickSortRandom(arr, pi + 1, high);
        }
        compareCount++;
    }

    public void printArray(int arr[]) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
