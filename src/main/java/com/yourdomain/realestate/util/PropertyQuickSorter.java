package com.yourdomain.realestate.util; // <-- Make sure this package declaration matches your project structure

import com.yourdomain.realestate.model.Property; // <-- Import your Property class from YOUR model package
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList; // Needed if you use ArrayList elsewhere or in helper methods not shown

/**
 * A utility class for sorting lists of Property objects using the Quick Sort algorithm.
 */
public class PropertyQuickSorter {

    /**
     * Sorts a list of Properties in place using the Quick Sort algorithm,
     * based on the Property's price.
     *
     * @param properties The list of properties to sort.
     */
    public void sortByPrice(List<Property> properties) {
        // Base case: if the list is null or has 0 or 1 element, it's already sorted.
        if (properties == null || properties.size() <= 1) {
            return;
        }
        // Start the recursive quick sort process on the entire list.
        // We use a Comparator to specify that sorting is based on the price.
        Comparator<Property> priceComparator = Comparator.comparing(Property::getPrice);
        quickSort(properties, 0, properties.size() - 1, priceComparator);
    }

    /**
     * The main recursive method that implements the Quick Sort algorithm.
     * It partitions the sub-list and recursively sorts the sub-lists before and after the pivot.
     *
     * @param list       The list being sorted.
     * @param low        The starting index of the sub-list.
     * @param high       The ending index of the sub-list (inclusive).
     * @param comparator The comparator used to determine the order of elements.
     * @param <T>        The type of elements in the list (which should be Property in this case).
     */
    private <T> void quickSort(List<T> list, int low, int high, Comparator<T> comparator) {
        // Recursive base case: only sort if the sub-list has more than one element.
        if (low < high) {
            // Partition the list around a pivot element.
            // The partition method returns the index where the pivot is placed after partitioning.
            int pi = partition(list, low, high, comparator);

            // Recursively sort the sub-list before the pivot element.
            quickSort(list, low, pi - 1, comparator);

            // Recursively sort the sub-list after the pivot element.
            quickSort(list, pi + 1, high, comparator);
        }
    }

    /**
     * Partitions a sub-list around a pivot element.
     * All elements less than or equal to the pivot are placed before the pivot,
     * and all elements greater than the pivot are placed after the pivot.
     *
     * @param list       The list being partitioned.
     * @param low        The starting index of the sub-list.
     * @param high       The ending index of the sub-list (index of the pivot).
     * @param comparator The comparator used for element comparisons.
     * @param <T>        The type of elements in the list.
     * @return The index of the pivot element after partitioning.
     */
    private <T> int partition(List<T> list, int low, int high, Comparator<T> comparator) {
        // Choose the pivot element (here, the last element of the sub-list).
        T pivot = list.get(high);

        // Initialize an index 'i' which will track the boundary between
        // elements less than or equal to the pivot and elements greater than the pivot.
        int i = (low - 1);

        // Iterate through the sub-list from the start ('low') up to, but not including, the pivot ('high').
        for (int j = low; j < high; j++) {
            // Compare the current element list.get(j) with the pivot using the comparator.
            // If the current element is less than or equal to the pivot:
            if (comparator.compare(list.get(j), pivot) <= 0) {
                // Increment 'i'. This 'i' indicates the position where the next
                // element smaller than the pivot should be placed.
                i++;

                // Swap the element at index 'i' with the current element at index 'j'.
                // This moves elements smaller than the pivot to the left side of the potential pivot position.
                swap(list, i, j);
            }
        }

        // After the loop, swap the pivot element (originally at 'high') with the element
        // at index (i + 1). This places the pivot in its correct sorted position.
        swap(list, i + 1, high);

        // Return the final index of the pivot element.
        return i + 1;
    }

    /**
     * A helper method to swap two elements in a list.
     *
     * @param list The list containing the elements to swap.
     * @param i    The index of the first element.
     * @param j    The index of the second element.
     * @param <T>  The type of elements in the list.
     */
    private <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i); // Store the element at index i temporarily
        list.set(i, list.get(j)); // Place the element from index j at index i
        list.set(j, temp); // Place the temporary element (originally from index i) at index j
    }

    // Optional: You could add another public method here to sort by location, etc.
    // public void sortByLocation(List<Property> properties) {
    //     if (properties == null || properties.size() <= 1) {
    //         return;
    //     }
    //     quickSort(properties, 0, properties.size() - 1, Comparator.comparing(Property::getLocation));
    // }
}