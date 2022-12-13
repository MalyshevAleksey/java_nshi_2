package com.PB;

import com.PB.Sorters.Bubble;
import com.PB.Sorters.InsertSort;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.ArrayList;

public class JSONParsing {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static DataDeserialization deserialization(InputStream in) throws IOException {
        JsonNode root = mapper.readTree(new InputStreamReader(in));
        ArrayNode values = (ArrayNode) root.get("values");
        ArrayList<Integer> numbers = new ArrayList<>();
        for(JsonNode node: values) {
            numbers.add(node.asInt());
        }
        String alg = "";
        if(root.has("algorithm")) {
            alg = root.get("algorithm").asText();
        } else alg = "bubble";
        DataDeserialization data = new DataDeserialization(alg, numbers.stream().mapToInt(i -> i).toArray());
        in.close();
        return data;
    }

    public static DataSerialization chooseAlg(DataDeserialization data, OutputStream out){
        String algorithm = data.getAlgorithm();
        int[] numbers = data.getNumbers();
        long start = System.currentTimeMillis();
        DataSerialization dataSerialization = new DataSerialization();
        if(numbers == null) {
            try {
                ObjectNode json = mapper.createObjectNode().put("errorMessage", "Array is null");
                mapper.writer(new DefaultPrettyPrinter()).writeValue(out, json);
                return null;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        switch (algorithm) {
            case "bubble":
                dataSerialization.setSortNumbs(new Bubble().sort(numbers));
                break;
            case "insert":
                dataSerialization.setSortNumbs(new InsertSort().sort(numbers));
                break;
            default:
                dataSerialization.setSorted(false);
                dataSerialization.setSortNumbs(data.getNumbers());
                dataSerialization.setTime(-1);
                return dataSerialization;
        }
        long total = System.currentTimeMillis() - start;
        dataSerialization.setTime(total);
        dataSerialization.setSorted(true);
        return dataSerialization;
    }

    public static void serialization(DataSerialization data, OutputStream out) {
        long time = data.getTime();
        ObjectNode json = mapper.createObjectNode();
        ArrayNode values = mapper.createArrayNode();
        for(int value: data.getSortNumbs()){
            values.add(value);
        }
        try {
            json.put("time", time);
            json.put("values", values);
            mapper.writer(new DefaultPrettyPrinter()).writeValue(out, json);
        } catch (IOException ioException) {}
    }

    public static void main(String[] args) throws IOException {
        serialization(chooseAlg(deserialization(
                new FileInputStream(new File("C:\\Users\\alexa\\IdeaProjects\\java_nshi_2\\Work_1\\src\\main\\resources\\example.json"))),
                new FileOutputStream(new File("C:\\Users\\alexa\\IdeaProjects\\java_nshi_2\\Work_1\\src\\main\\resources\\result.json"))),
                new FileOutputStream(new File("C:\\Users\\alexa\\IdeaProjects\\java_nshi_2\\Work_1\\src\\main\\resources\\result.json")));
    }
}


