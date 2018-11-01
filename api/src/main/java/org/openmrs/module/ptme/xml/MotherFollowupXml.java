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
        writer.addAttribute("visitDate", dateFormatter.format(mf.getStartDate()));
        writer.addAttribute("endDate", dateFormatter.format(mf.getEndDate()));
        writer.addAttribute("arvStatusAtRegistering", nullSafeString(mf.getArvStatusAtRegistering()));
        writer.addAttribute("estimatedDeliveryDate", nullSafeString(dateFormatter.format(mf.getEstimatedDeliveryDate())));
        writer.addAttribute("spousalScreeningResult", nullSafeString(mf.getSpousalScreeningResult()));
        writer.addAttribute("spousalScreeningDate", nullSafeString(dateFormatter.format(mf.getSpousalScreeningDate())));
        writer.addAttribute("pregnancyOutcome", nullSafeString(mf.getPregnancyOutcome()));
        writer.addAttribute("deliveryType", nullSafeString(mf.getDeliveryType()));
        writer.addAttribute("pregnantPatient", nullSafeString(mf.getPregnantPatient().getUuid()));

        if (mf.isVoided()){
            writer.addAttribute("voided", "1");
        }

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
