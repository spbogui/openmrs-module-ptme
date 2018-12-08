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
        if (cf.isVoided()) {
            writer.addAttribute("voided", "1");
        }
        addOptionalElement(writer,"arvProphylaxisGiven", nullSafeString(cf.getArvProphylaxisGiven()));
        addOptionalElement(writer,"pcr1SamplingDate", nullSafeString(dateFormatter.format(cf.getPcr1SamplingDate())));
        addOptionalElement(writer,"ageInMonthOnPcr1Sampling", nullSafeString(cf.getAgeInMonthOnPcr1Sampling()));
        addOptionalElement(writer,"ageInWeekOnPcr1Sampling", nullSafeString(cf.getAgeInWeekOnPcr1Sampling()));
        addOptionalElement(writer,"pcr1Result", nullSafeString(cf.getPcr1Result()));
        addOptionalElement(writer,"pcr2SamplingDate", nullSafeString(dateFormatter.format(cf.getPcr2SamplingDate())));
        addOptionalElement(writer,"ageInMonthOnPcr2Sampling", nullSafeString(cf.getAgeInMonthOnPcr2Sampling()));
        addOptionalElement(writer,"ageInWeekOnPcr2Sampling", nullSafeString(cf.getAgeInWeekOnPcr2Sampling()));
        addOptionalElement(writer,"pcr2Result", nullSafeString(cf.getPcr2Result()));
        addOptionalElement(writer,"pcr3SamplingDate", nullSafeString(dateFormatter.format(cf.getPcr3SamplingDate())));
        addOptionalElement(writer,"ageInMonthOnPcr3Sampling", nullSafeString(cf.getAgeInMonthOnPcr3Sampling()));
        addOptionalElement(writer,"ageInWeekOnPcr3Sampling", nullSafeString(cf.getAgeInWeekOnPcr3Sampling()));
        addOptionalElement(writer,"pcr3Result", nullSafeString(cf.getPcr3Result()));
        addOptionalElement(writer,"ctxInitiationDate", nullSafeString(dateFormatter.format(cf.getCtxInitiationDate())));
        addOptionalElement(writer,"ageInMonthOnCtxInitiation", nullSafeString(cf.getAgeInMonthOnCtxInitiation()));
        addOptionalElement(writer,"ageInWeekOnCtxInitiation", nullSafeString(cf.getAgeInWeekOnCtxInitiation()));
        addOptionalElement(writer,"inhInitiationDate", nullSafeString(dateFormatter.format(cf.getInhInitiationDate())));
        addOptionalElement(writer,"ageInMonthOnInhInitiation", nullSafeString(cf.getAgeInMonthOnInhInitiation()));
        addOptionalElement(writer,"ageInWeekOnInhInitiation", nullSafeString(cf.getAgeInWeekOnInhInitiation()));
        addOptionalElement(writer,"hivSerology1Date", nullSafeString(dateFormatter.format(cf.getHivSerology1Date())));
        addOptionalElement(writer,"ageInMonthOnHivSerology1", nullSafeString(cf.getAgeInMonthOnHivSerology1()));
        addOptionalElement(writer,"hivSerology1Result", nullSafeString(cf.getHivSerology1Result()));
        addOptionalElement(writer,"hivSerology2Date", nullSafeString(dateFormatter.format(cf.getHivSerology2Date())));
        addOptionalElement(writer,"ageInMonthOnHivSerology2", nullSafeString(cf.getAgeInMonthOnHivSerology2()));
        addOptionalElement(writer,"ageInWeekOnHivSerology2", nullSafeString(cf.getAgeInWeekOnHivSerology2()));
        addOptionalElement(writer,"hivSerology2Result", nullSafeString(cf.getHivSerology2Result()));
        addOptionalElement(writer,"followupResult", nullSafeString(cf.getFollowupResult()));
        addOptionalElement(writer,"followupResultDate", nullSafeString(dateFormatter.format(cf.getFollowupResultDate())));
        addOptionalElement(writer,"referenceLocation", nullSafeString(cf.getReferenceLocation()));

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
        if (value != null && !value.isEmpty()) {
            writer.startNode(nodeName);
            writer.setValue(value);
            writer.endNode();
        }
    }
}
