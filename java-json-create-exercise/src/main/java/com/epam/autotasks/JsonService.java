package com.epam.autotasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.File;
import java.util.List;

public class JsonService {

    public void createAnimalJson(String filePath, List<Animal> animals) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);


            objectMapper.writeValue(new File(filePath), animals);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
