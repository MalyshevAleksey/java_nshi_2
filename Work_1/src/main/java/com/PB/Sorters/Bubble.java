package com.PB.Sorters;

public class Bubble implements Sorter {
    @Override
    public int[] sort(int[] numbers) {
        int[] sortedArr = numbers.clone();
        int tempSwap;
        boolean flagOk = false;
        while(!flagOk){
            flagOk = true;
            for (int i = 0; i < sortedArr.length - 1; i++){
                if(sortedArr[i] > sortedArr[i+1]){
                    flagOk = false;
                    sortedArr[i] = sortedArr[i] + sortedArr[i+1] - (sortedArr[i+1] = sortedArr[i]);
                }
            }
        }
        return sortedArr;
    }
}
