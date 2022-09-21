package com.github.entropyfeng.apartment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonUtil {
    private static final Logger logger= LoggerFactory.getLogger(JsonUtil.class);
    private static  final ObjectMapper objectMapper=new ObjectMapper();
    private static final TypeReference<List<String>> stringListReference=new TypeReference<List<String>>() {
    };
    public static  <T>  String toJsonString(T obj){

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  static <T> T parseJson(TypeReference<T> typeReference,String json){
        try {
           return objectMapper.readerFor(typeReference).readValue(json);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> parseStringListJson(String json){

        try {
            return objectMapper.readerFor(stringListReference).readValue(json);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}
