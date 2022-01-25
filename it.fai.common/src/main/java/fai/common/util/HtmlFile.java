package fai.common.util;

import java.io.BufferedWriter;
import java.io.File;

public class HtmlFile {
  
  private BufferedWriter writer = null;
  
  
  public HtmlFile(String filename, String title) throws Exception {
    writer = new BufferedWriter(Filesystem.getWriter(new File(filename)));
    writeln("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
    writeln("<html>");
    writeln("<head>");
    writeln("<meta content=\"text/html; charset=ISO-8859-1\" http-equiv=\"content-type\">");
    writeln("<title>"+title+"</title>");
    writeln("</head>");
    writeln("<body>");
    
  }
  
  public HtmlFile writeln(String s) throws Exception {
    writer.write(s+"\r\n");
    return this;
  }
  
  public HtmlFile commit() throws Exception {
    writeln("</body>");
    writeln("</html>");
    writer.flush();
    writer.close();
    return this;
  }
  
  public HtmlFile tableBegin(String [] header, String width, int border, int cellpadding, int cellspacing) throws Exception {
    writeln("<table style=\"text-align: left; "+(width == null ? "" : "width: "+width+";")+"\" border=\""+border+"\" cellpadding=\""+cellpadding+"\" cellspacing=\""+cellspacing+"\">");
    writeln("<tbody>");
    writeln("<tr>");
    for (int i = 0; i < header.length; i++) {
      writeln("<th>"+header[i]+"</th>");
    }
    writeln("</tr>");
    
    return this;
  }
  
  public String getAnchorName(String anchorName, String anchorContent) {
    return "<a name=\""+anchorName+"\">"+anchorContent+"</a>";
  }

  public String getHRef(String ref, String refDescr) {
    return "<a href=\""+ref+"\">"+refDescr+"</a>";
  }
  
  
  public HtmlFile tableRow(String [] value) throws Exception {
    writeln("<tr>");
    for (int i = 0; i < value.length; i++) {
      writeln("<td>"+value[i]+"</td>");
    }
    writeln("</tr>");
    
    return this;
  }

  public HtmlFile tableEnd() throws Exception {
     writeln("</tbody>");
     writeln("</table>");
     return this;
  }
  

}
