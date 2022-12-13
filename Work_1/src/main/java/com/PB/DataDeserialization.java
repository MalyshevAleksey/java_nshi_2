package com.PB;

public class DataDeserialization {
    private int[] numbers;
    private String algorithm;
    public DataDeserialization(String alg, int[] numbs) {
        this.algorithm = alg;
        this.numbers = numbs;
    }
    public void setAlgorithm(String alg) { this.algorithm = alg; }
    public void setNumbers(int[] numbs) { this.numbers = numbs; }
    public String getAlgorithm() { return algorithm; }
    public int[] getNumbers() { return numbers; }
}

