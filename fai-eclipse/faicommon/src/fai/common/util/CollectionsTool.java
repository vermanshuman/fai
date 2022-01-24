package fai.common.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import com.google.gson.Gson;

public class CollectionsTool {
  
  public static boolean contains(Object valueToSearch,  Object [] targetList) {
    if (targetList == null) return false;
    for (int i = 0; i < targetList.length; i++) {
      if (targetList[i] == null) {
        if (valueToSearch == null)
          return true;
      }
      else {
        if (targetList[i].equals(valueToSearch)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static String asJson(Object [] o) {
    String [] s = new String[o.length];
    for (int i = 0; i < s.length; i++) {
      s[i] = o[i] != null ? ""+o[i] : null;
    }
    return asJson(s);
  }
  
  private static String asJson(String [] s) {
    return (new Gson()).toJson(s);
  }

  public static String asCSV(List<String> list, String sep) {
    return asCSV(list.toArray(new String[0]), sep);
  }

  public static List<String> asList(String csv, String sep) {
    List<String> list = new ArrayList<String>();
    StringTokenizer st = new StringTokenizer(csv, sep);
    while (st.hasMoreTokens()) {
      list.add(st.nextToken());
    }
    return list;
  }

  private static String asCSVExcecute(Object [] o, String sep) {
    StringBuffer sb = new StringBuffer("");
    for (int i = 0; i < o.length; i++) {
      sb.append(o[i]);
      if (i < o.length - 1) {
        sb.append(sep);
      }
    }
    return sb.toString();
  }


  public static String asCSV(String [] s, String sep) {
    return asCSVExcecute(s, sep);
  }

  public static String asCSV(Long [] s, String sep) {
    return asCSVExcecute(s, sep);
  }

  public static String asCSV(Integer [] s, String sep) {
    return asCSVExcecute(s, sep);
  }

  public static String keysAsJson(Hashtable ht) {
    return asJson(ht.keySet().toArray());
  }
  
}
