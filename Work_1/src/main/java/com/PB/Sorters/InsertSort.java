package com.PB.Sorters;

public class InsertSort implements Sorter {
    @Override
    public int[] sort(int[] array) {
        int j;
        int[] sortArr = array.clone();
        for (int i = 1; i < sortArr.length; i++) {
            int swap = sortArr[i];
            for (j = i; j > 0 && swap < sortArr[j - 1]; j--) {
                sortArr[j] = sortArr[j - 1];
            }
            sortArr[j] = swap;
        }
        return sortArr;
    }
}