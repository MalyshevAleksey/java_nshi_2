package com.PB;

public class DataSerialization {
    private long time;
    private int[] sortNumbs;
    private boolean isSorted;

    public DataSerialization() {}

    public long getTime() { return time; }
    public boolean getIsSorted() { return isSorted; }
    public int[] getSortNumbs() { return sortNumbs; }

    public void setTime(long time) { this.time = time; }
    public void setSorted(boolean sorted) { this.isSorted = sorted; }
    public void setSortNumbs(int[] sortNumbs) { this.sortNumbs = sortNumbs; }
}
