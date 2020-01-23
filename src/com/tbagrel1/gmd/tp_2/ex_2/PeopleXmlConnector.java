package com.tbagrel1.gmd.tp_2.ex_2;

import com.tbagrel1.gmd.tp_2.PersonalInformation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PeopleXmlConnector {
    public static final String PEOPLE_TAG_NAME = "personne";
    public static final String SAX_PARSER_CLASS = "com.ibm.xml.parsers.SAXParser";

    protected Path filePath;
    protected Stack<String> stack;
    protected List<PersonalInformation> allInfo;
    protected SAXParser parser;
    protected DefaultHandler handler;

    public PeopleXmlConnector(Path filePath) throws ParserConfigurationException, SAXException {
        this.filePath = filePath;

        this.stack = new Stack<>();
        this.allInfo = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        this.parser = factory.newSAXParser();
        this.handler = new DefaultHandler() {
            @Override
            public void characters(char[] chars, int start, int length) throws SAXException {
                String field = new String(chars, start, length);
                stack.push(field);
            }

            @Override
            public void startDocument() throws SAXException {
            }

            @Override
            public void endDocument() throws SAXException {
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals(PEOPLE_TAG_NAME)) {
                    stack.push(attributes.getValue(0));
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equals(PEOPLE_TAG_NAME)) {
                    String email = stack.pop();
                    String city = stack.pop();
                    int age = Integer.parseInt(stack.pop());
                    String phoneNumber = stack.pop();
                    String lastname = stack.pop();
                    String firstname = stack.pop();
                    int id = Integer.parseInt(stack.pop());
                    allInfo.add(new PersonalInformation(
                        id, firstname, lastname, age, city, phoneNumber, email
                    ));
                }
            }
        };
    }

    public List<PersonalInformation> getAllInfo() throws IOException, SAXException {
        parser.parse(filePath.toFile(), handler);
        return allInfo;
    }
}
