package com.PB.Sorters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SortersTest {
    @Test
    void bubbleTest() {
        int[] arrayNumbs = new int[]{7, 5, 33, 14, 1};
        int[] sortedNumbs = new Bubble().sort(arrayNumbs);
        sortTest(arrayNumbs, sortedNumbs);
    }

    @Test
    void insertSort() {
        int[] arrayNumbs = new int[]{7, 5, 33, 14, 1};
        int[] sortedNumbs = new InsertSort().sort(arrayNumbs);
        sortTest(arrayNumbs, sortedNumbs);
    }

    @Test
    void exceptionTest() {
        assertThrows(NullPointerException.class, () -> new Bubble().sort(null));
    }

    @Test
    void emptyArr() {
        Assertions.assertEquals(new int[]{}.length, new Bubble().sort(new int[]{}).length);
    }

    void sortTest(int[] arrayNumbs, int[] sortedNumbs) {
        boolean isSort = true;
        for(int i = 0; i < sortedNumbs.length - 1; i++){
            if(sortedNumbs[i] > sortedNumbs[i+1]) { isSort = false; }
        }
        Assertions.assertNotSame(arrayNumbs, sortedNumbs);
        Assertions.assertTrue(isSort);
        Assertions.assertArrayEquals(new int[]{1, 5, 7, 14, 33}, sortedNumbs);
    }
}
