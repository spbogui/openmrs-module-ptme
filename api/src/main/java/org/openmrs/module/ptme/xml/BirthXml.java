package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.module.ptme.Birth;

import java.text.SimpleDateFormat;

public class BirthXml implements Converter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Birth birth = (Birth) value;
        addOptionalElement(writer, "deliveryDate", dateFormatter.format(birth.getDeliveryDate()));
        addOptionalElement(writer, "homeBirth", nullSafeString(birth.getHomeBirth()));
        addOptionalElement(writer, "pregnancyIssue", nullSafeString(birth.getPregnancyIssue()));
        addOptionalElement(writer, "childState", nullSafeString(birth.getChildState()));

//        writer.addAttribute("deliveryDate", dateFormatter.format(birth.getDeliveryDate()));
//        writer.addAttribute("homeBirth", nullSafeString(birth.getHomeBirth()));
//        writer.addAttribute("pregnancyIssue", nullSafeString(birth.getPregnancyIssue()));
//        writer.addAttribute("childState", nullSafeString(birth.getChildState()));

        ConsultationXml consultationXml = new ConsultationXml();
        consultationXml.marshal(birth, writer, context);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Birth.class);
    }

    private static String nullSafeString(Object o) {
        if (o != null)
            return o.toString();
        return "";
    }

    /**
     * Convenience method for rendering XML elements
     *
     * @param writer
     *            XML output writer
     * @param nodeName
     *            name of node for element
     * @param value
     *            the value for the element. if null, then nothing is added to
     *            the output buffer
     */
    private void addOptionalElement(HierarchicalStreamWriter writer, String nodeName, String value) {
        if (value != null) {
            writer.startNode(nodeName);
            writer.setValue(value);
            writer.endNode();
        }
    }
}
