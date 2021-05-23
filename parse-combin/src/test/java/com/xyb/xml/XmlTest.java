package com.xyb.xml;

import com.xyb.xml.entity.Book;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.util.List;

public class XmlTest {

    @Test
    public void parseXmlTest() throws DocumentException {

        List<Book> bookList = Dom4jParseXml.parseXml("src/main/resources/xmlFiles/1xml说明.xml");
    }

}
