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
        writer.addAttribute("testProposal", nullSafeString(hs.getHivStatusAtReception()));
        writer.addAttribute("testResult", nullSafeString(hs.getHivStatusAtReception()));
        writer.addAttribute("resultAnnouncement", nullSafeString(hs.getHivStatusAtReception()));
        writer.addAttribute("arvDiscount", nullSafeString(hs.getHivStatusAtReception()));
        writer.addAttribute("childArvProphylaxis", nullSafeString(hs.getHivStatusAtReception()));
        writer.addAttribute("arvStatus", nullSafeString(hs.getHivStatusAtReception()));
        writer.addAttribute("arvTreatment", nullSafeString(hs.getHivStatusAtReception()));
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
