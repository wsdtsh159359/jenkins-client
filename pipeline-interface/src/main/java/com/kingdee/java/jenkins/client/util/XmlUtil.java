package com.kingdee.java.jenkins.client.util;


import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;

/**
* @author 高伟
* @date  2020/1/19 15:05
* @descrption
*/
public class XmlUtil {

    /**
      * @Description //将XML转换成String输出
      * @param: file
      * @Return
      * @Exception
    **/
    public static String xml2Str(String file) throws IOException,JDOMException {
        File file1=new File(file);
        if (!file1.exists()){
            throw new RuntimeException("config.xml配置文件不存在");
        }
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(new FileInputStream(file1));
        Format format = Format.getCompactFormat();
        format.setEncoding("UTF-8");// 设置xml文件的字符为UTF-8，解决中文问题
        XMLOutputter xmlOut = new XMLOutputter();
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        xmlOut.output(document, bo);
        return bo.toString().trim();

    }



}
