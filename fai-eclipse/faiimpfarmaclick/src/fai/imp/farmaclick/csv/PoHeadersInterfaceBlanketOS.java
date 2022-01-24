package fai.imp.farmaclick.csv;

import java.util.ArrayList;
import java.util.List;

import fai.common.csv.CsvOutputStream;
import fai.common.util.NumberFormatFactory;

public class PoHeadersInterfaceBlanketOS {
  
  
  private static List<String> headersWithDefaults = null;
  
  private static synchronized void initHeadersAndDefaults() throws Exception {
    if (headersWithDefaults != null) return;
    headersWithDefaults = new ArrayList<String>();
    headersWithDefaults.add("* Interface Header Key,intf_hdr_1");
    headersWithDefaults.add("* Action,ORIGINAL");
    headersWithDefaults.add("Batch ID,1245");
    headersWithDefaults.add("Import Source,");
    headersWithDefaults.add("Approval Action,");
    headersWithDefaults.add("Agreement,");
    headersWithDefaults.add("Document Type Code,BLANKET");
    headersWithDefaults.add("Style,");
    headersWithDefaults.add("Procurement BU,Vision Operations");
    headersWithDefaults.add("Buyer,Clare Furey");
    headersWithDefaults.add("Currency Code,USD");
    headersWithDefaults.add("Description,");
    headersWithDefaults.add("Supplier,CV_SuppA01");
    headersWithDefaults.add("Supplier Number,");
    headersWithDefaults.add("Supplier Site,CVSuppA01Site01");
    headersWithDefaults.add("Supplier Contact,");
    headersWithDefaults.add("Supplier Order,");
    headersWithDefaults.add("Fob,");
    headersWithDefaults.add("Carrier,");
    headersWithDefaults.add("Freight Terms,");
    headersWithDefaults.add("Pay On Code,");
    headersWithDefaults.add("Payment Terms,Immediate");
    headersWithDefaults.add("Initiating Party,BUYER");
    headersWithDefaults.add("Change Order Description,");
    headersWithDefaults.add("Required Acknowledgment,N");
    headersWithDefaults.add("Acknowledge Within (Days),");
    headersWithDefaults.add("Communication Method,");
    headersWithDefaults.add("Fax,");
    headersWithDefaults.add("E-mail,");
    headersWithDefaults.add("Confirming order,");
    headersWithDefaults.add("Agreement Amount ,");
    headersWithDefaults.add("Amount Limit ,");
    headersWithDefaults.add("Minimum Release Amount ,");
    headersWithDefaults.add("Start Date,");
    headersWithDefaults.add("End Date ,");
    headersWithDefaults.add("Note to Supplier ,Note to Vendor");
    headersWithDefaults.add("Note to Receiver ,Note to Receiver");
    headersWithDefaults.add("Automatically generate orders,");
    headersWithDefaults.add("Automatically submit for approval,");
    headersWithDefaults.add("Group requisitions,");
    headersWithDefaults.add("Group requisition lines,");
    headersWithDefaults.add("Use ship-to organization and location,");
    headersWithDefaults.add("Use need-by date,");
    headersWithDefaults.add("Catalog Administrator Authoring,");
    headersWithDefaults.add("Apply price updates to existing orders,");
    headersWithDefaults.add("Communicate price updates,");
    headersWithDefaults.add("ATTRIBUTE_CATEGORY,");
    headersWithDefaults.add("ATTRIBUTE1,Attribute1");
    headersWithDefaults.add("ATTRIBUTE2,");
    headersWithDefaults.add("ATTRIBUTE3,");
    headersWithDefaults.add("ATTRIBUTE4,");
    headersWithDefaults.add("ATTRIBUTE5,");
    headersWithDefaults.add("ATTRIBUTE6,");
    headersWithDefaults.add("ATTRIBUTE7,");
    headersWithDefaults.add("ATTRIBUTE8,");
    headersWithDefaults.add("ATTRIBUTE9,");
    headersWithDefaults.add("ATTRIBUTE10,");
    headersWithDefaults.add("ATTRIBUTE11,");
    headersWithDefaults.add("ATTRIBUTE12,");
    headersWithDefaults.add("ATTRIBUTE13,");
    headersWithDefaults.add("ATTRIBUTE14,");
    headersWithDefaults.add("ATTRIBUTE15,");
    headersWithDefaults.add("ATTRIBUTE16,");
    headersWithDefaults.add("ATTRIBUTE17,");
    headersWithDefaults.add("ATTRIBUTE18,");
    headersWithDefaults.add("ATTRIBUTE19,");
    headersWithDefaults.add("ATTRIBUTE20,Attribute20");
    headersWithDefaults.add("ATTRIBUTE_DATE1,");
    headersWithDefaults.add("ATTRIBUTE_DATE2,");
    headersWithDefaults.add("ATTRIBUTE_DATE3,");
    headersWithDefaults.add("ATTRIBUTE_DATE4,");
    headersWithDefaults.add("ATTRIBUTE_DATE5,");
    headersWithDefaults.add("ATTRIBUTE_DATE6,");
    headersWithDefaults.add("ATTRIBUTE_DATE7,");
    headersWithDefaults.add("ATTRIBUTE_DATE8,");
    headersWithDefaults.add("ATTRIBUTE_DATE9,");
    headersWithDefaults.add("ATTRIBUTE_DATE10,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER1,1");
    headersWithDefaults.add("ATTRIBUTE_NUMBER2,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER3,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER4,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER5,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER6,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER7,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER8,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER9,");
    headersWithDefaults.add("ATTRIBUTE_NUMBER10,10");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP1,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP2,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP3,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP4,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP5,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP6,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP7,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP8,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP9,");
    headersWithDefaults.add("ATTRIBUTE_TIMESTAMP10,");
    headersWithDefaults.add("Buyer E-mail,");
    headersWithDefaults.add("Mode of Transport,");
    headersWithDefaults.add("Service Level,");
    headersWithDefaults.add("Aging Period Days,");
    headersWithDefaults.add("Aging Onset Point,");
    headersWithDefaults.add("Consumption Advice Frequency,");
    headersWithDefaults.add("Consumption Advice Summary,");
    headersWithDefaults.add("Default line as consignment line,");
    headersWithDefaults.add("Pay on use,");
    headersWithDefaults.add("Billing Cycle Closing Date,");
    headersWithDefaults.add("Configuration Ordering Enabled,");
    headersWithDefaults.add("Use Customer Sales Order,");
    headersWithDefaults.add("Buyer Managed Transportation,");
    headersWithDefaults.add("Allow ordering from unassigned sites,N");
    headersWithDefaults.add("Outside Processing Enabled,N");
    headersWithDefaults.add("Master Contract Number,");
    headersWithDefaults.add("Master Contract Type,");
    headersWithDefaults.add("* END,END"); // terminatore atteso da Oracle per qualsiasi file CSV
  }

  public static CsvOutputStream newInstance() throws Exception {
    initHeadersAndDefaults();
    CsvOutputStream csv = new CsvOutputStream();
    csv.setCrlf("\r\n");
    csv.setFieldSeparator(",");
    csv.setIntNumberFormat(null);
    csv.setStringFormat("\"%s\"", '"', '"');
    csv.setDoubleNumberFormat(NumberFormatFactory.newNumberFormat("###0.##",'.', '\''));
    csv.setHeadersWithDefaults(headersWithDefaults, ",", "*");
    csv.setDefault("intf_hdr_1", null);
    csv.setDefault("Item Number", null);
    csv.setIncludeHeader(false);
    return csv;
  }
}
