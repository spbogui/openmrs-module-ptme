package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.module.ptme.MotherFollowup;
import org.openmrs.module.ptme.MotherFollowupVisit;

import java.text.SimpleDateFormat;

public class MotherFollowupXml implements Converter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        MotherFollowup mf = (MotherFollowup) value;
        writer.addAttribute("uuid", mf.getUuid());
        if (mf.isVoided()){
            writer.addAttribute("voided", "1");
        }

        addOptionalElement(writer,"visitDate", dateFormatter.format(mf.getStartDate()));
        addOptionalElement(writer,"endDate", dateFormatter.format(mf.getEndDate()));
        addOptionalElement(writer,"arvStatusAtRegistering", nullSafeString(mf.getArvStatusAtRegistering()));
        addOptionalElement(writer,"estimatedDeliveryDate", nullSafeString(dateFormatter.format(mf.getEstimatedDeliveryDate())));
        addOptionalElement(writer,"spousalScreeningResult", nullSafeString(mf.getSpousalScreeningResult()));
        addOptionalElement(writer,"spousalScreeningDate", nullSafeString(dateFormatter.format(mf.getSpousalScreeningDate())));
        addOptionalElement(writer,"pregnancyOutcome", nullSafeString(mf.getPregnancyOutcome()));
        addOptionalElement(writer,"deliveryType", nullSafeString(mf.getDeliveryType()));
        addOptionalElement(writer,"pregnantPatient", nullSafeString(mf.getPregnantPatient().getUuid()));

        writer.startNode("motherFollowupVisits");

        if (!mf.getMotherFollowupVisits().isEmpty()) {

            for (MotherFollowupVisit mfv : mf.getMotherFollowupVisits()) {
                writer.startNode("motherFollowupVisit");
                writer.addAttribute("uuid", mfv.getUuid());
                writer.addAttribute("visitDate", dateFormatter.format(mfv.getVisitDate()));
                writer.addAttribute("gestationalAge", nullSafeString(mfv.getGestationalAge()));
                writer.addAttribute("continuingArv", nullSafeString(mfv.getContinuingArv()));
                writer.addAttribute("continuingCtx", nullSafeString(mfv.getContinuingCtx()));
                if (mfv.isVoided()){
                    writer.addAttribute("voided", "1");
                }
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
        return aClass.equals(MotherFollowup.class);
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
