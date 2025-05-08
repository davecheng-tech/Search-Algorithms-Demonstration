package searchAlgorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * A class that demonstrates the performance of linear and binary search algorithms for various array sizes.
 */
public class SearchTimer {

    /**
     * Performs a linear search for a target value in an array.
     *
     * @param x        the target value to search for.
     * @param numArray the array to search within.
     * @return an array where the first element is the index of the target value (or -1 if not found)
     *         and the second element is the number of iterations.
     */
    public static int[] searchLinear(int x, int[] numArray) {
        int iterations = 0;
        for (int i = 0; i < numArray.length; i++) {
            iterations++;
            if (numArray[i] == x) {
                return new int[]{i, iterations};
            }
        }
        return new int[]{-1, iterations}; // -1 if not found
    }

    /**
     * Performs a binary search for a target value in a sorted array.
     *
     * @param x        the target value to search for.
     * @param numArray the sorted array to search within.
     * @return an array where the first element is the index of the target value (or -1 if not found)
     *         and the second element is the number of iterations.
     */
    public static int[] searchBinary(int x, int[] numArray) {
        int low = 0, high = numArray.length - 1, iterations = 0;

        while (low <= high) {
            iterations++;
            int mid = (low + high) / 2;

            if (numArray[mid] == x) {
                return new int[]{mid, iterations};
            } else if (x < numArray[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{-1, iterations}; // -1 if not found
    }

    /**
     * Simulates a search using either linear or binary search.
     *
     * @param searchType the type of search to perform ("linear" or "binary").
     * @param numRange   the range of numbers in the array.
     * @param simArray   the array to search within.
     * @return the number of iterations taken during the search.
     */
    public static int simSearch(String searchType, int numRange, int[] simArray) {
        Random rand = new Random();
        int x = rand.nextInt(numRange) + 1; // Random target value
        return searchType.equalsIgnoreCase("linear")
                ? searchLinear(x, simArray)[1]
                : searchBinary(x, simArray)[1];
    }

    /**
     * Generates a random array of unique integers within a specified range.
     *
     * @param size     the number of elements in the array.
     * @param numRange the range of values (inclusive).
     * @return an array of unique random integers.
     */
    public static int[] generateNumArray(int size, int numRange) {
        Random rand = new Random();
        int[] numArray = new int[size];
        boolean[] used = new boolean[numRange + 1];

        for (int i = 0; i < size; i++) {
            int num;
            do {
                num = rand.nextInt(numRange) + 1;
            } while (used[num]);
            numArray[i] = num;
            used[num] = true;
        }
        return numArray;
    }

    /**
     * Main method to execute and compare the performance of linear and binary search algorithms for various sizes.
     */
    public static void main(String[] args) {
        // List sizes to test
        int[] listSizes = {
                1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192,
                16384, 32768, 65536, 131072, 262144, 524288
        };

        // Table header
        System.out.printf("%-12s %-12s %-20s %-20s %-20s%n", "List Size", "Search Type", "Avg. Iterations",
                "Total Time (ms)", "Time/Search (ms)");
        System.out.println("-----------------------------------------------------------------------------------------");

        // Loop through each list size
        for (int size : listSizes) {
            int[] searchArray = generateNumArray(size, size);
            int[] sortedSearchArray = Arrays.copyOf(searchArray, searchArray.length);
            Arrays.sort(sortedSearchArray);

            // Measure Linear Search
            long linearPassCount = 0;
            long linearStart = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linearPassCount += simSearch("linear", size, searchArray);
            }
            long linearEnd = System.nanoTime();
            double linearTotalTimeMs = (linearEnd - linearStart) / 1_000_000.0;
            double linearTimePerSearch = linearTotalTimeMs / size;

            // Output Linear Search Results
            System.out.printf("%-12d %-12s %-20.2f %-20.2f %-20.6f%n", size, "Linear",
                    (double) linearPassCount / size, linearTotalTimeMs, linearTimePerSearch);

            // Measure Binary Search
            long binaryPassCount = 0;
            long binaryStart = System.nanoTime();
            for (int i = 0; i < size; i++) {
                binaryPassCount += simSearch("binary", size, sortedSearchArray);
            }
            long binaryEnd = System.nanoTime();
            double binaryTotalTimeMs = (binaryEnd - binaryStart) / 1_000_000.0;
            double binaryTimePerSearch = binaryTotalTimeMs / size;

            // Output Binary Search Results
            System.out.printf("%-12d %-12s %-20.2f %-20.2f %-20.6f%n", size, "Binary",
                    (double) binaryPassCount / size, binaryTotalTimeMs, binaryTimePerSearch);
        }
    }
}