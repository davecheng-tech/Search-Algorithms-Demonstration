# SearchTimer: Linear vs. Binary Search Performance Comparison

## Overview

The `SearchTimer` Java program demonstrates and compares the performance of two fundamental search algorithms: **Linear Search** and **Binary Search**. It does so by measuring and printing the number of iterations and execution time each algorithm takes to search for randomly chosen elements in arrays of increasing size.

The key components of this program include:

- **`searchLinear`**: Implements linear search, which scans each element until the target is found or the array ends.
- **`searchBinary`**: Implements binary search, which repeatedly divides a sorted array in half to locate the target value.
- **`simSearch`**: Randomly selects a target and performs the designated search algorithm, returning the iteration count.
- **`generateNumArray`**: Creates an array of unique random integers.
- **`main` Method**: Iteratively runs both search algorithms across arrays of exponentially increasing sizes, collecting performance data.

## Output

For each array size, the program outputs:
- The size of the array.
- The type of search algorithm used.
- The average number of iterations per search.
- The total execution time in milliseconds.
- The average time per search in milliseconds.

This helps visually and quantitatively highlight the scalability and efficiency of each algorithm.

## Big O Summary and Efficiency

**Linear Search**
- **Time Complexity**: O(n)
- **Behavior**: Search time increases linearly with the array size.
- **Use Case**: Works on unsorted data, but becomes inefficient as data size grows.

**Binary Search**
- **Time Complexity**: O(log n)
- **Behavior**: Search time grows logarithmically, which is significantly faster for large data sets.
- **Use Case**: Requires sorted data but performs extremely well for large arrays.

### Key Takeaway

As data size grows, **binary search becomes dramatically more efficient** than linear search due to its logarithmic time complexity. This illustrates the practical impact of Big O notation in algorithm selection: choosing the right algorithm is critical for performance, especially at scale.