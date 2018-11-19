package org.openmrs.module.ptme.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.openmrs.module.ptme.utils.ReportDataSetIndicatorRun;
import org.openmrs.module.ptme.utils.ReportIndicatorValues;
import org.openmrs.module.ptme.utils.ReportRunIndicatorValue;

import java.text.SimpleDateFormat;

public class ReportIndicatorValuesXml implements Converter {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        ReportIndicatorValues riv = (ReportIndicatorValues) value;
        writer.addAttribute("startDate", nullSafeString(dateFormatter.format(riv.getReportStartDate())));
        writer.addAttribute("endDate", nullSafeString(dateFormatter.format(riv.getReportEndDate())));
        writer.addAttribute("generationDate", nullSafeString(dateFormatter.format(riv.getGenerationDate())));
        writer.addAttribute("location", nullSafeString(riv.getLocationUuid()));
        /*if (!riv.getReportRunIndicatorValues().isEmpty()) {
            writer.startNode("reportValues");
            for (ReportRunIndicatorValue rriv :
                    riv.getReportRunIndicatorValues()) {
                addOptionalElementWithIdAttribute(writer, "indicator", rriv.getIndicatorUuid(), nullSafeString(rriv.getValue()));
            }
            writer.endNode();
        }*/

        if (!riv.getReportDataSetIndicatorRuns().isEmpty()) {
            //writer.startNode("dataSet");
            for (ReportDataSetIndicatorRun rdi : riv.getReportDataSetIndicatorRuns()) {
                writer.startNode("dataSet");
                writer.addAttribute("uuid", rdi.getDataSetUuid());
                if (!rdi.getReportRunIndicatorValues().isEmpty()) {
                    //writer.startNode("indicators");
                    for (ReportRunIndicatorValue rriv :
                            rdi.getReportRunIndicatorValues()) {
                        writer.startNode("indicator");
                        writer.addAttribute("uuid", rriv.getIndicatorUuid());
                        writer.addAttribute("code", rriv.getCode());
                        addOptionalElement(writer, "value", nullSafeString(rriv.getValue()));
                        writer.endNode();
                        //addOptionalElementWithIdAttribute(writer, "indicator", rriv.getIndicatorUuid(), nullSafeString(rriv.getValue()));
                    }
                    //writer.endNode();
                }
                writer.endNode();
            }
            //writer.endNode();
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(ReportIndicatorValues.class);
    }

    private static String nullSafeString(Object o) {
        if (o != null)
            return o.toString();
        return "";
    }

    private void addOptionalElement(HierarchicalStreamWriter writer, String nodeName, String value) {
        if (value != null) {
            writer.startNode(nodeName);
            writer.setValue(value);
            writer.endNode();
        }
    }

    private void addOptionalElementWithIdAttribute(
            HierarchicalStreamWriter writer, String nodeName, String id,
            String value) {
        if (value != null) {
            writer.startNode(nodeName);
            writer.addAttribute("uuid", id);
            writer.setValue(value);
            writer.endNode();
        }
    }
}
