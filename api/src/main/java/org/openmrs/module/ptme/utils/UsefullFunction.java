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

    public static String escapeHTML(String s){
        StringBuilder sb = new StringBuilder(s.length());
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                //case '&': sb.append("&amp;"); break;
                case '"': sb.append("&quot;"); break;
                case 'à': sb.append("&agrave;");break;
                case 'À': sb.append("&Agrave;");break;
                case 'â': sb.append("&acirc;");break;
                case 'Â': sb.append("&Acirc;");break;
                case 'ä': sb.append("&auml;");break;
                case 'Ä': sb.append("&Auml;");break;
                case 'å': sb.append("&aring;");break;
                case 'Å': sb.append("&Aring;");break;
                case 'æ': sb.append("&aelig;");break;
                case 'Æ': sb.append("&AElig;");break;
                case 'ç': sb.append("&ccedil;");break;
                case 'Ç': sb.append("&Ccedil;");break;
                case 'é': sb.append("&eacute;");break;
                case 'É': sb.append("&Eacute;");break;
                case 'è': sb.append("&egrave;");break;
                case 'È': sb.append("&Egrave;");break;
                case 'ê': sb.append("&ecirc;");break;
                case 'Ê': sb.append("&Ecirc;");break;
                case 'ë': sb.append("&euml;");break;
                case 'Ë': sb.append("&Euml;");break;
                case 'ï': sb.append("&iuml;");break;
                case 'Ï': sb.append("&Iuml;");break;
                case 'ô': sb.append("&ocirc;");break;
                case 'Ô': sb.append("&Ocirc;");break;
                case 'ö': sb.append("&ouml;");break;
                case 'Ö': sb.append("&Ouml;");break;
                case 'ø': sb.append("&oslash;");break;
                case 'Ø': sb.append("&Oslash;");break;
                case 'ß': sb.append("&szlig;");break;
                case 'ù': sb.append("&ugrave;");break;
                case 'Ù': sb.append("&Ugrave;");break;
                case 'û': sb.append("&ucirc;");break;
                case 'Û': sb.append("&Ucirc;");break;
                case 'ü': sb.append("&uuml;");break;
                case 'Ü': sb.append("&Uuml;");break;
                case '®': sb.append("&reg;");break;
                case '©': sb.append("&copy;");break;
                case '€': sb.append("&euro;"); break;
                // be carefull with this one (non-breaking whitee space)
                // case ' ': sb.append("&nbsp;");break;

                default:  sb.append(c); break;
            }
        }
        return sb.toString();
    }

    public static String writeAccent(String s) {

        if (s == null || s.isEmpty())
            return null;

        s = s.contains("&lt;") ? s.replace("&lt;", "<") : s;
        s = s.contains("&gt;") ? s.replace("&gt;", ">") : s;
        s = s.contains("&quot;") ? s.replace("&quot;", "\"") : s;
        s = s.contains("&agrave;") ? s.replace("&agrave;", "à") : s;
        s = s.contains("&Agrave;") ? s.replace("&Agrave;", "À") : s;
        s = s.contains("&acirc;") ? s.replace("&acirc;", "â") : s;
        s = s.contains("&Acirc;") ? s.replace("&Acirc;", "Â") : s;
        s = s.contains("&auml;") ? s.replace("&auml;", "ä") : s;
        s = s.contains("&Auml;") ? s.replace("&Auml;", "Ä") : s;
        s = s.contains("&aring;") ? s.replace("&aring;", "å") : s;
        s = s.contains("&Aring;") ? s.replace("&Aring;", "Å") : s;
        s = s.contains("&aelig;") ? s.replace("&aelig;", "æ") : s;
        s = s.contains("&Aelig;") ? s.replace("&Aelig;", "Æ") : s;
        s = s.contains("&ccedil;") ? s.replace("&ccedil;", "ç") : s;
        s = s.contains("&Ccedil;") ? s.replace("&Ccedil;", "Ç") : s;
        s = s.contains("&eacute;") ? s.replace("&eacute;", "é") : s;
        s = s.contains("&Eacute;") ? s.replace("&Eacute;", "É") : s;
        s = s.contains("&egrave;") ? s.replace("&egrave;", "è") : s;
        s = s.contains("&Egrave;") ? s.replace("&Egrave;", "È") : s;
        s = s.contains("&ecirc;") ? s.replace("&ecirc;", "ê") : s;
        s = s.contains("&Ecirc;") ? s.replace("&Ecirc;", "Ê") : s;
        s = s.contains("&euml;") ? s.replace("&euml;", "ë") : s;
        s = s.contains("&Euml;") ? s.replace("&Euml;", "Ë") : s;
        s = s.contains("&iuml;") ? s.replace("&iuml;", "ï") : s;
        s = s.contains("&Iuml;") ? s.replace("&Iuml;", "Ï") : s;
        s = s.contains("&ocirc;") ? s.replace("&ocirc;", "ô") : s;
        s = s.contains("&Ocirc;") ? s.replace("&Ocirc;", "Ô") : s;
        s = s.contains("&ouml;") ? s.replace("&ouml;", "ö") : s;
        s = s.contains("&Ouml;") ? s.replace("&Ouml;", "Ö") : s;
        s = s.contains("&oslash;") ? s.replace("&oslash;", "ø") : s;
        s = s.contains("&Oslash;") ? s.replace("&Oslash;", "Ø") : s;
        s = s.contains("&szlig;") ? s.replace("&szlig;", "ß") : s;
        s = s.contains("&ugrave;") ? s.replace("&ugrave;", "ù") : s;
        s = s.contains("&Ugrave;") ? s.replace("&Ugrave;", "Ù") : s;
        s = s.contains("&ucirc;") ? s.replace("&ucirc;", "û") : s;
        s = s.contains("&ucirc;") ? s.replace("&ucirc;", "û") : s;
        s = s.contains("&Ucirc;") ? s.replace("&Ucirc;", "Û") : s;
        s = s.contains("&uuml;") ? s.replace("&uuml;", "ü") : s;
        s = s.contains("&Uuml;") ? s.replace("&Uuml;", "Ü") : s;
        s = s.contains("&reg;") ? s.replace("&reg;", "®") : s;
        s = s.contains("&copy;") ? s.replace("&copy;", "©") : s;
        s = s.contains("&euro;") ? s.replace("&euro;", "€") : s;

        return s;
    }

}
