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
        writer.addAttribute("familyName", nullSafeString(pregnantPatient.getFamilyName()));
        writer.addAttribute("givenName", nullSafeString(pregnantPatient.getGivenName()));
        writer.addAttribute("age", nullSafeString(pregnantPatient.getAge()));
        writer.addAttribute("pregnantNumber", pregnantPatient.getPregnantNumber());
        writer.addAttribute("hivCareNumber", nullSafeString(pregnantPatient.getHivCareNumber()));
        writer.addAttribute("hivCareNumber", nullSafeString(pregnantPatient.getHivCareNumber()));
        writer.addAttribute("screeningNumber", nullSafeString(pregnantPatient.getScreeningNumber()));
        if (pregnantPatient.isVoided()) {
            writer.addAttribute("voided", "1");
        }

        if (pregnantPatient.getPatient() != null) {
            Patient patient = pregnantPatient.getPatient();
            writer.startNode("patient");
            PatientXml patientXml = new PatientXml();
            patientXml.marshal(patient, writer, context);
            writer.endNode();
        }
        writer.addAttribute("patientUuid", nullSafeString(pregnantPatient.getPatient().getUuid()));
        writer.addAttribute("maritalStatus", nullSafeString(pregnantPatient.getMaritalStatus()));
        writer.addAttribute("spousalScreening", nullSafeString(pregnantPatient.getSpousalScreening()));
        writer.addAttribute("spousalScreeningResult", nullSafeString(pregnantPatient.getSpousalScreeningResult()));
        writer.addAttribute("locationUuid", nullSafeString(pregnantPatient.getLocation().getUuid()));


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
     * @param writer
     *            XML output writer
     * @param nodeName
     *            name of node for element
     * @param value
     *            the value for the element. if null, then nothing is added to
     *            the output buffer
     */
    private void addOptionalElement(HierarchicalStreamWriter writer,
                                    String nodeName, String value) {
        if (value != null) {
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
