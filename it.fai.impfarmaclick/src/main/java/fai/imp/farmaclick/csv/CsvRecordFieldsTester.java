package fai.imp.farmaclick.csv;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fai.common.csv.CsvException;
import fai.common.csv.CsvFixedSpaceRecord;
import fai.common.util.CollectionsTool;

public class CsvRecordFieldsTester {
  
  
  public static List<CsvException> testAllMethods(CsvRecordFarmaclick record) throws Exception {
    List<CsvException> errors = new ArrayList<CsvException>();
    String recordClassName = record.getClass().getName();
    Class recordClass = record.getClass();
    Method [] method = recordClass.getMethods();
    int calledMethods = 0;
    for (int i = 0; i < method.length; i++) {
      Method m = method[i];
      String methodName = m.getName();
      if (!m.getDeclaringClass().equals(CsvFixedSpaceRecord.class) && Modifier.isPublic(m.getModifiers()) && (m.getName().startsWith("is") || m.getName().startsWith("get"))) {
        Class [] t = m.getParameterTypes();
        if (t.length != 0) throw new NoSuchAlgorithmException("NOT IMPLEMENTED YET: 0 parameters expected for "+recordClassName+" class's method "+methodName+"; found: "+CollectionsTool.asJson(t));
        try {
          Object o = m.invoke(record);
        }
        catch (Exception e) {
          errors.add(new CsvException(e));
        }
        calledMethods++;
      }
      
    }
    return errors;
  }

}
