package com.PB;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;

public class JsonServlet extends HttpServlet {
    public final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        if(!Objects.equals(req.getContentType(), "application/json")){
            resp.setStatus(400);
            return;
        }

        byte[] in = req.getInputStream().readAllBytes();
        OutputStream out = resp.getOutputStream();
        if(!validate(new ByteArrayInputStream(in))){
            resp.setStatus(400);
            JSONParsing.chooseAlg(new DataDeserialization("", null), out);
        } else {
            DataDeserialization data = JSONParsing.deserialization(new ByteArrayInputStream(in));

            if (Objects.equals(data.getAlgorithm(), "")) {
                resp.setStatus(200);
                data.setAlgorithm("bubble");
                DataSerialization outData = JSONParsing.chooseAlg(data, out);
                JSONParsing.serialization(outData, out);
            }

            DataSerialization outData = JSONParsing.chooseAlg(data, out);
            if (!outData.getIsSorted()) {
                resp.setStatus(404);
                JSONParsing.chooseAlg(new DataDeserialization("", null), out);
            }
            JSONParsing.serialization(outData, out);
        }
    }

    public boolean validate(InputStream jsonStream) throws IOException {
        ObjectNode json = (ObjectNode) mapper.readTree(jsonStream);
        if(!json.has("values")) return false;

        if(json.get("values").isNull()) return false;

        for(JsonNode number: (ArrayNode) json.get("values")){
            if(!number.isInt()) return false;
        }

        return true;
    }
}
