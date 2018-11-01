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
        writer.addAttribute("hivStatusAtReception", nullSafeString(hs.getHivStatusAtReception()));
        writer.addAttribute("testProposal", nullSafeString(hs.getTestProposal()));
        writer.addAttribute("testResult", nullSafeString(hs.getTestResult()));
        writer.addAttribute("resultAnnouncement", nullSafeString(hs.getResultAnnouncement()));
        writer.addAttribute("arvDiscount", nullSafeString(hs.getArvDiscount()));
        writer.addAttribute("childArvProphylaxis", nullSafeString(hs.getChildArvProphylaxis()));
        writer.addAttribute("arvStatus", nullSafeString(hs.getArvStatus()));
        writer.addAttribute("arvTreatment", nullSafeString(hs.getArvTreatment()));
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
}
