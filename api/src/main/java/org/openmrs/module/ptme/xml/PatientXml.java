package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;

import java.text.SimpleDateFormat;

public class PatientXml implements Converter {

    /**
     * All dates are reported in YYYY-MM-DD format
     */
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Patient patient = (Patient) value;

        if (patient.getBirthdate() != null)
            writer.addAttribute("birthdate", dateFormatter.format(patient.getBirthdate()));
        writer.addAttribute("birthdateEstimated", nullSafeString(patient.getBirthdateEstimated()));
        writer.addAttribute("gender", patient.getGender());
        writer.addAttribute("uuid", patient.getUuid());

        writer.startNode("identifierList");
        for (PatientIdentifier pid : patient.getIdentifiers()) {
            writer.startNode("identifier");
            if (pid.isVoided())
                writer.addAttribute("voided", "1");
            if (pid.isPreferred())
                writer.addAttribute("preferred", "1");
            writer.addAttribute("type", pid.getIdentifierType().getName());
            writer.addAttribute("uuid", pid.getIdentifierType().getUuid());
            // TODO: should encode invalid chars in name
            writer.setValue(pid.getIdentifier());
            writer.endNode();
        }
        writer.endNode();

        PersonName name = patient.getPersonName();
        if (name != null) {
            writer.startNode("name");
            addOptionalElement(writer, "prefix", name.getPrefix());
            addOptionalElement(writer, "givenName", name.getGivenName());
            addOptionalElement(writer, "middleName", name.getMiddleName());
            addOptionalElement(writer, "familyName", name.getFamilyName());
            addOptionalElement(writer, "familyName2", name.getFamilyName2());
            addOptionalElement(writer, "degree", name.getDegree());
            addOptionalElement(writer, "uuid", name.getUuid());
            writer.endNode();
        }

        writer.startNode("addressList");
        for (PersonAddress address : patient.getAddresses()) {
            writer.startNode("address");
            if (address.getPreferred())
                writer.addAttribute("preferred", "1");
            addOptionalElement(writer, "address1", address.getAddress1());
            addOptionalElement(writer, "address2", address.getAddress2());
            addOptionalElement(writer, "cityVillage", address.getCityVillage());
            addOptionalElement(writer, "countyDistrict", address.getCountyDistrict());
            addOptionalElement(writer, "stateProvince", address.getStateProvince());
            addOptionalElement(writer, "country", address.getCountry());
            addOptionalElement(writer, "uuid", address.getUuid());
            writer.endNode();
        }
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Patient.class);
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
