package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.module.ptme.HivService;

import java.text.SimpleDateFormat;

public class HivServiceXml implements Converter {

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        HivService hs = (HivService)value;
        writer.addAttribute("uuid", hs.getUuid());
        if (hs.isVoided()) {
            writer.addAttribute("voided", "1");
        }
        addOptionalElement(writer,"hivStatusAtReception", nullSafeString(hs.getHivStatusAtReception()));
        addOptionalElement(writer,"testProposal", nullSafeString(hs.getTestProposal()));
        addOptionalElement(writer,"testResult", nullSafeString(hs.getTestResult()));
        addOptionalElement(writer,"resultAnnouncement", nullSafeString(hs.getResultAnnouncement()));
        addOptionalElement(writer,"arvDiscount", nullSafeString(hs.getArvDiscount()));
        addOptionalElement(writer,"childArvProphylaxis", nullSafeString(hs.getChildArvProphylaxis()));
        addOptionalElement(writer,"arvStatus", nullSafeString(hs.getArvStatus()));
        addOptionalElement(writer,"arvTreatment", nullSafeString(hs.getArvTreatment()));
    }

    private static String nullSafeString(Object o) {
        if (o != null)
            return o.toString();
        return "";
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(HivService.class);
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
