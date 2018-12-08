package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.module.ptme.Prenatal;

import java.text.SimpleDateFormat;

public class PrenatalXml implements Converter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Prenatal prenatal = (Prenatal) value;
        addOptionalElement(writer, "rank", prenatal.getRank());
        addOptionalElement(writer, "weekOfAmenorrhea", nullSafeString(prenatal.getWeekOfAmenorrhea()));
        addOptionalElement(writer, "spousalScreening", nullSafeString(prenatal.getSpousalScreening()));
        addOptionalElement(writer, "spousalScreeningResult", nullSafeString(prenatal.getSpousalScreeningResult()));
        addOptionalElement(writer, "appointmentDate", dateFormatter.format(prenatal.getAppointmentDate()));

        ConsultationXml consultationXml = new ConsultationXml();
        writer.startNode("consultation");
        consultationXml.marshal(prenatal, writer, context);
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Prenatal.class);
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
