import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * This is a basic set of unit tests for Sorting.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class SortingStudentTest {

    private static final int TIMEOUT = 200;
    private TeachingAssistant[] tas;
    private TeachingAssistant[] tasByName;
    private TeachingAssistant[] tasAge;
    private TeachingAssistant[] tasByAge;
    private ComparatorPlus<TeachingAssistant> comp;
    private ComparatorPlus<Integer> compInt;

    @Before
    public void setUp() {
        /*
         * Unsorted Names:
         * index 0: Alex
         * index 1: Ivan
         * index 2: Miguel
         * index 3: Paige
         * index 4: Brooke
         * index 5: Sanjana
         * index 6: Yotam
         * index 7: Nick
         * index 8: Reece
         * index 9: Destini
         */

        /*
         * Sorted Names:
         * index 0: Alex
         * index 1: Brooke
         * index 2: Destini
         * index 3: Ivan
         * index 4: Miguel
         * index 5: Nick
         * index 6: Paige
         * index 7: Reece
         * index 8: Sanjana
         * index 9: Yotam
         */

        tas = new TeachingAssistant[10];
        tas[0] = new TeachingAssistant("Alex");
        tas[1] = new TeachingAssistant("Ivan");
        tas[2] = new TeachingAssistant("Miguel");
        tas[3] = new TeachingAssistant("Paige");
        tas[4] = new TeachingAssistant("Brooke");
        tas[5] = new TeachingAssistant("Sanjana");
        tas[6] = new TeachingAssistant("Yotam");
        tas[7] = new TeachingAssistant("Nick");
        tas[8] = new TeachingAssistant("Reece");
        tas[9] = new TeachingAssistant("Destini");
        tasByName = new TeachingAssistant[10];
        tasByName[0] = tas[0];
        tasByName[1] = tas[4];
        tasByName[2] = tas[9];
        tasByName[3] = tas[1];
        tasByName[4] = tas[2];
        tasByName[5] = tas[7];
        tasByName[6] = tas[3];
        tasByName[7] = tas[8];
        tasByName[8] = tas[5];
        tasByName[9] = tas[6];

        // a group of TAs named that have different ages
        tasAge = new TeachingAssistant[11];
        tasAge[0] = new TeachingAssistant("Paul", 18);
        tasAge[1] = new TeachingAssistant("John", 19);
        tasAge[2] = new TeachingAssistant("John", 20);
        tasAge[3] = new TeachingAssistant("Paul", 21);
        tasAge[4] = new TeachingAssistant("Paul", 22);
        tasAge[5] = new TeachingAssistant("Ringo", 23);
        tasAge[6] = new TeachingAssistant("John", 24);
        tasAge[7] = new TeachingAssistant("George", 25);
        tasAge[8] = new TeachingAssistant("George", 26);
        tasAge[9] = new TeachingAssistant("Ringo", 27);
        tasAge[10] = new TeachingAssistant("John", 28);

        tasByAge = new TeachingAssistant[11];
        tasByAge[0] = tasAge[7];
        tasByAge[1] = tasAge[8];
        tasByAge[2] = tasAge[1];
        tasByAge[3] = tasAge[2];
        tasByAge[4] = tasAge[6];
        tasByAge[5] = tasAge[10];
        tasByAge[6] = tasAge[0];
        tasByAge[7] = tasAge[3];
        tasByAge[8] = tasAge[4];
        tasByAge[9] = tasAge[5];
        tasByAge[10] = tasAge[9];

        // initialize comparators
        comp = TeachingAssistant.getNameComparator();
        compInt = new ComparatorPlus<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                incrementCount();
                return a.compareTo(b);
            }
        };
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort() {
        Sorting.selectionSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 45 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortPlus() {
        Integer[] unsortedArray = new Integer[] { 445, 576, -979, 96, 738, 714, -808, -395 };
        Integer[] sortedArray = new Integer[] { -979, -808, -395, 96, 445, 576, 714, 738 };
        Sorting.selectionSort(unsortedArray, compInt);
        assertArrayEquals(sortedArray, unsortedArray);
        // comparison count is based on csvistool
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 28 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 24 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortPlus() {
        Integer[] unsortedArray = new Integer[] { 802, -941, -849, -342, 185, -327 };
        Integer[] sortedArray = new Integer[] { -941, -849, -342, -327, 185, 802 };
        Sorting.insertionSort(unsortedArray, compInt);
        assertArrayEquals(sortedArray, unsortedArray);
        // comparison count is based on csvistool
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 10 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSort() {
        Sorting.bubbleSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 44 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortPlus() {
        Integer[] unsortedArray = new Integer[] { 25, 958, 405, -730, 676, -163, 704, 995 };
        Integer[] sortedArray = new Integer[] { -730, -163, 25, 405, 676, 704, 958, 995 };
        Sorting.bubbleSort(unsortedArray, compInt);
        assertArrayEquals(sortedArray, unsortedArray);
        // comparison count is based on csvistool
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 18 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 21 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortPlus() {
        Integer[] unsortedArray = new Integer[] { 226, -195, 998, -530, -999, 262, 313, -809 };
        Integer[] sortedArray = new Integer[] { -999, -809, -530, -195, 226, 262, 313, 998 };
        Sorting.mergeSort(unsortedArray, compInt);
        assertArrayEquals(sortedArray, unsortedArray);
        // comparison count is based on csvistool
        assertTrue("Number of comparisons: " + compInt.getCount(),
                compInt.getCount() <= 17 && compInt.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] unsortedArray = new int[] { 54, 28, 58, 84, 20, 122, -85, 3 };
        int[] sortedArray = new int[] { -85, 3, 20, 28, 54, 58, 84, 122 };
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortPlus() {
        int[] unsortedArray = new int[] { -159, -43, -166, 449, 941, -585, 907, 509 };
        int[] sortedArray = new int[] { -585, -166, -159, -43, 449, 509, 907, 941 };
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortOverflow() {
        int[] unsortedArray = new int[] { 0, 15, Integer.MIN_VALUE };
        int[] sortedArray = new int[] { Integer.MIN_VALUE, 0, 15 };
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSort() {
        int[] unsortedArray = new int[] { 54, 28, 58, 84, 20, 122, -85, 3 };
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] sortedArray = new int[] { -85, 3, 20, 28, 54, 58, 84, 122 };
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSortPlus() {
        int[] unsortedArray = new int[] { 133, 870, 295, -62, -169, 483, -427 };
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] sortedArray = new int[] { -427, -169, -62, 133, 295, 483, 870 };
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortStability() {
        // bubble sort should be stable
        Sorting.bubbleSort(tasAge, comp);
        assertEquals(tasAge[0].getAge(), tasByAge[0].getAge());
        assertEquals(tasAge[1].getAge(), tasByAge[1].getAge());
        assertEquals(tasAge[2].getAge(), tasByAge[2].getAge());
        assertEquals(tasAge[3].getAge(), tasByAge[3].getAge());
        assertEquals(tasAge[4].getAge(), tasByAge[4].getAge());
        assertEquals(tasAge[5].getAge(), tasByAge[5].getAge());
        assertEquals(tasAge[6].getAge(), tasByAge[6].getAge());
        assertEquals(tasAge[7].getAge(), tasByAge[7].getAge());
        assertEquals(tasAge[8].getAge(), tasByAge[8].getAge());
        assertEquals(tasAge[9].getAge(), tasByAge[9].getAge());
        assertEquals(tasAge[10].getAge(), tasByAge[10].getAge());
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortStability() {
        // insertion sort should be stable
        Sorting.insertionSort(tasAge, comp);
        assertEquals(tasAge[0].getAge(), tasByAge[0].getAge());
        assertEquals(tasAge[1].getAge(), tasByAge[1].getAge());
        assertEquals(tasAge[2].getAge(), tasByAge[2].getAge());
        assertEquals(tasAge[3].getAge(), tasByAge[3].getAge());
        assertEquals(tasAge[4].getAge(), tasByAge[4].getAge());
        assertEquals(tasAge[5].getAge(), tasByAge[5].getAge());
        assertEquals(tasAge[6].getAge(), tasByAge[6].getAge());
        assertEquals(tasAge[7].getAge(), tasByAge[7].getAge());
        assertEquals(tasAge[8].getAge(), tasByAge[8].getAge());
        assertEquals(tasAge[9].getAge(), tasByAge[9].getAge());
        assertEquals(tasAge[10].getAge(), tasByAge[10].getAge());
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortStability() {
        // merge sort should be stable
        Sorting.mergeSort(tasAge, comp);
        assertEquals(tasAge[0].getAge(), tasByAge[0].getAge());
        assertEquals(tasAge[1].getAge(), tasByAge[1].getAge());
        assertEquals(tasAge[2].getAge(), tasByAge[2].getAge());
        assertEquals(tasAge[3].getAge(), tasByAge[3].getAge());
        assertEquals(tasAge[4].getAge(), tasByAge[4].getAge());
        assertEquals(tasAge[5].getAge(), tasByAge[5].getAge());
        assertEquals(tasAge[6].getAge(), tasByAge[6].getAge());
        assertEquals(tasAge[7].getAge(), tasByAge[7].getAge());
        assertEquals(tasAge[8].getAge(), tasByAge[8].getAge());
        assertEquals(tasAge[9].getAge(), tasByAge[9].getAge());
        assertEquals(tasAge[10].getAge(), tasByAge[10].getAge());
    }

    /**
     * Class for testing proper sorting.
     */
    private static class TeachingAssistant {
        private String name;
        private int age;

        /**
         * Create a teaching assistant with default age of 18.
         *
         * @param name name of TA
         */
        public TeachingAssistant(String name) {
            this(name, 18);
        }

        /**
         * Create a teaching assistant with name and age.
         *
         * @param name name of TA
         * @param age age of TA
         */
        public TeachingAssistant(String name, int age) {
            this.name = name;
            this.age = age;
        }

        /**
         * Get the name of the teaching assistant.
         *
         * @return name of teaching assistant
         */
        public String getName() {
            return name;
        }

        /**
         * Get the age of the teaching assistant.
         *
         * @return
         */
        public int getAge() {
            return age;
        }

        /**
         * Create a comparator that compares the names of the teaching
         * assistants.
         *
         * @return comparator that compares the names of the teaching assistants
         */
        public static ComparatorPlus<TeachingAssistant> getNameComparator() {
            return new ComparatorPlus<TeachingAssistant>() {
                @Override
                public int compare(TeachingAssistant ta1,
                                   TeachingAssistant ta2) {
                    incrementCount();
                    return ta1.getName().compareTo(ta2.getName());
                }
            };
        }

        @Override
        public String toString() {
            return "Name: " + name;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof TeachingAssistant
                    && ((TeachingAssistant) other).name.equals(this.name);
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }
}