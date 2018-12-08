package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.module.ptme.PregnantPatient;

import java.text.SimpleDateFormat;

public class PregnantPatientXml implements Converter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext context) {
        PregnantPatient pregnantPatient = (PregnantPatient) o;

        writer.addAttribute("uuid", pregnantPatient.getUuid());
        writer.addAttribute("locationUuid", nullSafeString(pregnantPatient.getLocation().getUuid()));
        if (pregnantPatient.isVoided()) {
            writer.addAttribute("voided", "1");
        }
        writer.startNode("pregnantNumber");
        writer.setValue(pregnantPatient.getPregnantNumber());
        writer.endNode();

        writer.startNode("age");
        writer.setValue(nullSafeString(pregnantPatient.getAge()));
        writer.endNode();

        addOptionalElement(writer, "familyName", pregnantPatient.getFamilyName());
        addOptionalElement(writer, "givenName", pregnantPatient.getGivenName());
        addOptionalElement(writer, "hivCareNumber", pregnantPatient.getHivCareNumber());
        addOptionalElement(writer, "screeningNumber", pregnantPatient.getScreeningNumber());
        addOptionalElement(writer, "maritalStatus", nullSafeString(pregnantPatient.getMaritalStatus()));
        addOptionalElement(writer, "spousalScreening", nullSafeString(pregnantPatient.getSpousalScreening()));
        addOptionalElement(writer, "spousalScreeningResult", nullSafeString(pregnantPatient.getSpousalScreeningResult()));
        addOptionalElement(writer, "spousalScreeningResult", nullSafeString(pregnantPatient.getSpousalScreeningResult()));
        addOptionalElement(writer, "patient", pregnantPatient.getPatient().getUuid());

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(PregnantPatient.class);
    }

    private static String nullSafeString(Object o) {
        if (o != null)
            return o.toString();
        return "";
    }

    /**
     * Convenience method for rendering XML elements
     *
     * @param writer   XML output writer
     * @param nodeName name of node for element
     * @param value    the value for the element. if null, then nothing is added to
     *                 the output buffer
     */
    private void addOptionalElement(HierarchicalStreamWriter writer,
                                    String nodeName, String value) {
        if (value != null && !value.isEmpty()) {
            writer.startNode(nodeName);
            writer.setValue(value);
            writer.endNode();
        }
    }

    private void addOptionalElementWithIdAttribute(
            HierarchicalStreamWriter writer, String nodeName, String id,
            String value) {
        if (value != null) {
            writer.startNode(nodeName);
            writer.addAttribute("id", id);
            writer.setValue(value);
            writer.endNode();
        }
    }
}
