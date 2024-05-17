package com.rgr.webtransferback.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class DevTools {

    public static void dumpObjectOnConsole(Object obj) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(obj);
            ObjectMapper mapper = new ObjectMapper();
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(json));
            System.out.println(prettyJson);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }        
    }
}
