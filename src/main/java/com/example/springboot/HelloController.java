package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@RestController
public class HelloController {
    Transformer transformer;
    DocumentBuilder builder;

    private void setTransformer(Source xslInput)
            throws TransformerConfigurationException
    {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Templates precompiledTemplates = transformerFactory.newTemplates(xslInput);
        transformer = precompiledTemplates.newTransformer();
        transformer.reset();
    }

    HelloController() throws ParserConfigurationException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        builder = documentBuilderFactory.newDocumentBuilder();

        try {
            setTransformer(new StreamSource(new FileInputStream("default.xsl")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/setTransformRules", method = RequestMethod.POST, consumes = "text/plain")
    public String setRules(@RequestBody String postPayload) {
        try {
            setTransformer(new StreamSource(new StringReader(postPayload)));
            return "Suceess";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Fail";
    }

    @RequestMapping(value = "/transform", method = RequestMethod.POST, consumes = "text/plain")
    public String transform(@RequestBody String postPayload) {
        try {
            InputSource xmlInput = new InputSource(new StringReader(postPayload));

	    Source source = new DOMSource(builder.parse(xmlInput));

            StringWriter writer = new StringWriter();
            transformer.transform(source, new StreamResult(writer));

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Fail";
    }

}
