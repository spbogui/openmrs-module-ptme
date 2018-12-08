package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.module.ptme.Child;
import org.openmrs.module.ptme.ChildFollowupVisit;

import java.text.SimpleDateFormat;

public class ChildXml implements Converter {

    /**
     * All dates are reported in YYYY-MM-DD format
     */
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Child child = (Child) value;

        writer.addAttribute("uuid", child.getUuid());

        addOptionalElement(writer,"childFollowupNumber", child.getChildFollowupNumber());
        addOptionalElement(writer,"birthDate", dateFormatter.format(child.getBirthDate()));
        addOptionalElement(writer,"gender", child.getGender());
        addOptionalElement(writer,"familyName", child.getFamilyName());
        addOptionalElement(writer,"givenName", child.getGivenName());

        if (child.getMother() != null) {
            writer.startNode("mother");
            writer.setValue(child.getMother().getUuid());
            //PatientXml patientXml = new PatientXml();
            //patientXml.marshal(child.getMother(), writer, context);
            writer.endNode();
        }

        if (child.getPatient() != null) {
            writer.startNode("patientLinked");
            writer.setValue(child.getPatient().getUuid());
//            PatientXml patientXml = new PatientXml();
//            patientXml.marshal(child.getPatient(), writer, context);
            writer.endNode();
        }

        if (child.getChildFollowup() != null) {
            writer.startNode("childFollowup");
            ChildFollowupXml childFollowupXml = new ChildFollowupXml();
            childFollowupXml.marshal(child.getChildFollowup(), writer, context);
            writer.endNode();
        }

        writer.startNode("childFollowupVisits");
        if(!child.getChildFollowupVisits().isEmpty()) {
            for (ChildFollowupVisit cfv : child.getChildFollowupVisits()){
                writer.startNode("childFollowupVisit");
                ChildFollowupVisitXml childFollowupVisitXml = new ChildFollowupVisitXml();
                childFollowupVisitXml.marshal(cfv, writer, context);
                writer.endNode();
            }
        }
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Child.class);
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
