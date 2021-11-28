/*
package com.surabi.restaurants.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import sun.plugin2.message.Serializer;

import java.util.Map;

public class msgSer implements Serializer {

        @Override
        public void configure(Map<String, ?> map, boolean b) {
        }
        @Override
        public byte[] serialize(String arg0, Message arg1) {
            byte[] retVal = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                retVal = objectMapper.writeValueAsString(arg1).getBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return retVal;
        }
        @Override
        public void close() {
        }
    }

*/
