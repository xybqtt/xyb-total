package com.xyb.xml;

import com.xyb.xml.entity.Book;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dom4jParseXml {

    public static void main(String[] args) throws DocumentException {
        Dom4jParseXml.parseXml("src/main/resources/xmlFiles/1xml说明.xml");
    }


    public static List<Book> parseXml(String path) throws DocumentException {

        // 1、创建saxReader输入流，去读取xml文件，生成document对象
        SAXReader reader = new SAXReader();

        // 2、读取xml文件获取doc对象
        Document doc = reader.read(path);

        // 3、读取根元素
        Element rootEle = doc.getRootElement();

        // 4、通过标签名获取子元素
        List<Element> bookEleList = rootEle.elements("book");

        List<Book> bookList = new ArrayList<>();
        // 5、遍历，处理每个book标签转换book类
        for(Element bookEle : bookEleList){
            // asXML()，将标签对象转换为标签字符串
            Element nameEle = bookEle.element("name");
            System.out.println(nameEle.asXML());

            // getText()，获取标签中的文本内容
            String nameTxt = nameEle.getText();

            // elementText()，直接获取标签文本内容
            String priceTxt = bookEle.elementText("price");
            String authorTxt = bookEle.elementText("author");

            // attributeValue()，获取标签属性值
            String sn = bookEle.attributeValue("sn");

            Book book = new Book();
            book.setAuthor(authorTxt);
            book.setName(nameTxt);
            book.setSn(sn);
            book.setPrice(new BigDecimal(priceTxt));

            System.out.println(book.toString());
            bookList.add(book);

        }

        return bookList;
    }

}
