package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.Location;
import org.openmrs.module.ptme.Consultation;
import org.openmrs.module.ptme.HivService;
import org.openmrs.module.ptme.PregnantPatient;

import java.text.SimpleDateFormat;

public class ConsultationXml implements Converter {

    /**
     * All dates are reported in YYYY-MM-DD format
     */
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Consultation consultation = (Consultation) value;
        writer.addAttribute("uuid", consultation.getUuid());
        if (consultation.isVoided()) {
            writer.addAttribute("voided", "1");
        }

        writer.startNode("consultationDate");
        writer.setValue(dateFormatter.format(consultation.getConsultationDate()));
        writer.endNode();

        writer.startNode("pregnantPatient");
        writer.setValue(consultation.getPregnantPatient().getUuid());
        writer.endNode();

        //writer.addAttribute("consultationDate", dateFormatter.format(consultation.getConsultationDate()));

        //if (consultation.getPregnantPatient() != null) {
//            writer.startNode("pregnantPatient");
//            writer.setValue(consultation.getPregnantPatient().getUuid());
//            PregnantPatient pregnantPatient = consultation.getPregnantPatient();
//            PregnantPatientXml patientXml = new PregnantPatientXml();
//            patientXml.marshal(pregnantPatient, writer, context);
//            writer.endNode();
        //}
        writer.startNode("location");
        writer.setValue(nullSafeString(consultation.getLocation().getPostalCode()));
//        Location location = consultation.getLocation();
//        LocationXml locationXml = new LocationXml();
//        locationXml.marshal(location, writer, context);
        writer.endNode();

        if (consultation.getHivService() != null) {
            HivServiceXml hivServiceXml = new HivServiceXml();
            writer.startNode("hivService");
            hivServiceXml.marshal(consultation.getHivService(), writer, context);
            writer.endNode();
        }

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Consultation.class);
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
