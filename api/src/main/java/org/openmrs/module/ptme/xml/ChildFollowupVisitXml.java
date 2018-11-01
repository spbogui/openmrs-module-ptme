package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.module.ptme.ChildFollowupVisit;

import java.text.SimpleDateFormat;

public class ChildFollowupVisitXml implements Converter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        ChildFollowupVisit cfv = (ChildFollowupVisit) value;
        writer.addAttribute("uuid", cfv.getUuid());

        if (cfv.isVoided()) {
            writer.addAttribute("voided", "1");
        }
        addOptionalElement(writer, "visitDate", dateFormatter.format(cfv.getVisitDate()));
        addOptionalElement(writer, "modernContraceptiveMethod", cfv.getModernContraceptiveMethod().toString());
        addOptionalElement(writer, "ageInDay", nullSafeString(cfv.getAgeInDay()));
        addOptionalElement(writer, "ageInWeek", nullSafeString(cfv.getAgeInWeek()));
        addOptionalElement(writer, "ageInMonth", nullSafeString(cfv.getAgeInMonth()));
        addOptionalElement(writer, "continuingCtx", nullSafeString(cfv.getContinuingCtx()));
        addOptionalElement(writer, "continuingInh", nullSafeString(cfv.getContinuingInh()));
        //writer.addAttribute("visitDate", dateFormatter.format(cfv.getVisitDate()));
//        writer.addAttribute("modernContraceptiveMethod", cfv.getModernContraceptiveMethod().toString());
//        writer.addAttribute("ageInDay", nullSafeString(cfv.getAgeInDay()));
//        writer.addAttribute("ageInMonth", nullSafeString(cfv.getAgeInMonth()));
//        writer.addAttribute("ageInWeek", nullSafeString(cfv.getAgeInWeek()));
//        writer.addAttribute("continuingCtx", nullSafeString(cfv.getContinuingCtx()));
//        writer.addAttribute("continuingInh", nullSafeString(cfv.getContinuingInh()));

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(ChildFollowupVisit.class);
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
