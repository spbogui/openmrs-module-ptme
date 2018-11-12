package org.openmrs.module.ptme.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UsefullFunction {
    public static Date formatDateToyyyyMMdd(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    //Convert Calendar to Date
    public static Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

    public static Date formatDateToddMMyyyy(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static String formatDateToyyyyMMddString(Date d) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date ddf = formatter.parse(formatter.format(d));
            return ddf.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d.toString();
    }

    public static Date formatDateToddMMyyyyhms(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date formatDateToddMMyyyyhmsStart(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 00:00:00");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date formatDateToddMMyyyyhmsEnd(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 23:59:59");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Document obtenerDocumentDeByte(byte[] documentoXml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(documentoXml));
    }

    private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int i = 0; i < nodeMap.getLength(); i++) {

                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());

                    }

                }

                if (tempNode.hasChildNodes()) {

                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());

                }

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }

        }

    }

}
