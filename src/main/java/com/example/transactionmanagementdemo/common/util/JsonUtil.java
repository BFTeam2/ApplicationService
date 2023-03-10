package com.example.transactionmanagementdemo.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String objToString(Object object){
        if (object == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
