package com.kegmil.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.underscore.lodash.U;
import org.junit.Test;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertTrue;

public class ParserXmlTojson {

    private static final String xmlString = "<?xml version='1.0' encoding='UTF-8'?>" +
            "<RequestTicketForm>" +
            "<section span='col-12' order='1'><field>name</field><field>title</field><field>priority</field></section>" +
            "<section span='col-12'>" +
            "<field>name</field>" +
            "<field>title</field>" +
            "<field>priority</field>" +
            "</section>" +
            "</RequestTicketForm>";

    @Test
    public void testConvertXmlToJsonMustBeSuccessfullyWithJackson() {
        try {

            // Create a new XmlMapper to read XML tags
            XmlMapper xmlMapper = new XmlMapper();
            File xmlFile = new File(getClass().getClassLoader().getResource("data.xml").getFile());
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            //Reading the XML
            JsonNode jsonNode = xmlMapper.readTree(fileInputStream);

            //Create a new ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            //Get JSON as a string
            String value = objectMapper.writeValueAsString(jsonNode);

            System.out.println("*** Converting XML to JSON ***");
            System.out.println(value);
            assertTrue( value != null && value.length() > 0 );
        } catch (IOException | SecurityException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    public void testConvertXmlToJsonMustBeSuccessfullyWithJsonLibrary() throws IOException {
        InputStream xmlInputStream = getClass().getClassLoader().getResourceAsStream("data.xml");

        String text = new String(IOUtils.readFully(xmlInputStream, -1, true), StandardCharsets.UTF_8.name());
        String jsonData =  U.xmlToJson(text);
        System.out.println(jsonData);
    }

}
