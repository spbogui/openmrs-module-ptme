package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.module.ptme.ChildFollowup;

import java.text.SimpleDateFormat;

public class ChildFollowupXml implements Converter {

    /**
     * All dates are reported in YYYY-MM-DD format
     */
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        ChildFollowup cf = (ChildFollowup) value;

        writer.addAttribute("uuid", cf.getUuid());
        writer.addAttribute("arvProphylaxisGiven", nullSafeString(cf.getArvProphylaxisGiven()));
        writer.addAttribute("pcr1SamplingDate", nullSafeString(dateFormatter.format(cf.getPcr1SamplingDate())));
        writer.addAttribute("ageInMonthOnPcr1Sampling", nullSafeString(cf.getAgeInMonthOnPcr1Sampling()));
        writer.addAttribute("ageInWeekOnPcr1Sampling", nullSafeString(cf.getAgeInWeekOnPcr1Sampling()));
        writer.addAttribute("pcr1Result", nullSafeString(cf.getPcr1Result()));
        writer.addAttribute("pcr2SamplingDate", nullSafeString(dateFormatter.format(cf.getPcr2SamplingDate())));
        writer.addAttribute("ageInMonthOnPcr2Sampling", nullSafeString(cf.getAgeInMonthOnPcr2Sampling()));
        writer.addAttribute("ageInWeekOnPcr2Sampling", nullSafeString(cf.getAgeInWeekOnPcr2Sampling()));
        writer.addAttribute("pcr2Result", nullSafeString(cf.getPcr2Result()));
        writer.addAttribute("pcr3SamplingDate", nullSafeString(dateFormatter.format(cf.getPcr3SamplingDate())));
        writer.addAttribute("ageInMonthOnPcr3Sampling", nullSafeString(cf.getAgeInMonthOnPcr3Sampling()));
        writer.addAttribute("ageInWeekOnPcr3Sampling", nullSafeString(cf.getAgeInWeekOnPcr3Sampling()));
        writer.addAttribute("pcr3Result", nullSafeString(cf.getPcr3Result()));
        writer.addAttribute("ctxInitiationDate", nullSafeString(dateFormatter.format(cf.getCtxInitiationDate())));
        writer.addAttribute("ageInMonthOnCtxInitiation", nullSafeString(cf.getAgeInMonthOnCtxInitiation()));
        writer.addAttribute("ageInWeekOnCtxInitiation", nullSafeString(cf.getAgeInWeekOnCtxInitiation()));
        writer.addAttribute("inhInitiationDate", nullSafeString(dateFormatter.format(cf.getInhInitiationDate())));
        writer.addAttribute("ageInMonthOnInhInitiation", nullSafeString(cf.getAgeInMonthOnInhInitiation()));
        writer.addAttribute("ageInWeekOnInhInitiation", nullSafeString(cf.getAgeInWeekOnInhInitiation()));
        writer.addAttribute("hivSerology1Date", nullSafeString(dateFormatter.format(cf.getHivSerology1Date())));
        writer.addAttribute("ageInMonthOnHivSerology1", nullSafeString(cf.getAgeInMonthOnHivSerology1()));
        writer.addAttribute("hivSerology1Result", nullSafeString(cf.getHivSerology1Result()));
        writer.addAttribute("hivSerology2Date", nullSafeString(dateFormatter.format(cf.getHivSerology2Date())));
        writer.addAttribute("ageInMonthOnHivSerology2", nullSafeString(cf.getAgeInMonthOnHivSerology2()));
        writer.addAttribute("ageInWeekOnHivSerology2", nullSafeString(cf.getAgeInWeekOnHivSerology2()));
        writer.addAttribute("hivSerology2Result", nullSafeString(cf.getHivSerology2Result()));
        writer.addAttribute("followupResult", nullSafeString(cf.getFollowupResult()));
        writer.addAttribute("followupResultDate", nullSafeString(dateFormatter.format(cf.getFollowupResultDate())));
        writer.addAttribute("referenceLocation", nullSafeString(cf.getReferenceLocation()));

        if (cf.isVoided()) {
            writer.addAttribute("voided", "1");
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return false;
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
