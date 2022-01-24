package fai.imp.farmaclick.ws;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.DettaglioArticoliOutputBean;
import fai.imp.farmaclick.ws.bean.OrdineOutputBean;
import fai.imp.farmaclick.ws.bean.ArticoloInputBean;
import fai.imp.farmaclick.ws.bean.DownloadListinoOutputBean;
import fai.imp.farmaclick.ws.bean.OrdineInputBean;

class UniversalAPIUtil {
  
  private static boolean debugMode = false;
  
  
  
  public static boolean isDebugMode() {
    return debugMode;
  }


  public static void setDebugMode(boolean debugMode) {
    UniversalAPIUtil.debugMode = debugMode;
  }

  public static DettaglioArticoliOutputBean getDettaglioArticoliOutputBean(Object o) throws Exception {
    if (o == null) return null;
    //
    // --- al momento supportato da una ed una sola API, non necessita di alcuna conversione ---
    //
    return (DettaglioArticoliOutputBean)o;
  }

  
  public static DownloadListinoOutputBean getDownloadListinoOutputBean(Object o) throws Exception {
    if (o == null) return null;
    //
    DownloadListinoOutputBean dl = new DownloadListinoOutputBean();
    Class c = o.getClass();
    Method [] method = c.getMethods();
    for (int i = 0; i < method.length; i++) {
      Method m = method[i];
      String methodName = m.getName();
      if ("getDataOraGenerazioneListino".equals(methodName)) {
        Calendar dataOraGenerazioneListino = (Calendar)m.invoke(o);
        dl.setDataOraGenerazioneListino(dataOraGenerazioneListino);
      }
      else if ("getDelayProsRichiesta".equals(methodName)) {
        int delayProsRichiesta = (Integer)m.invoke(o);
        dl.setDelayProsRichiesta(delayProsRichiesta);
      }
      else if ("getEsitoServizio".equals(methodName)) {
        int esitoServizio = (Integer)m.invoke(o);
        dl.setEsitoServizio(esitoServizio);
      }
      else if ("getTipoRisposta".equals(methodName)) {
        String tipoRisposta = (String)m.invoke(o);
        dl.setTipoRisposta(tipoRisposta);
      }
      else if ("getUrlConfermaDownload".equals(methodName)) {
        String urlConfermaDownload = (String)m.invoke(o);
        dl.setUrlConfermaDownload(urlConfermaDownload);
      }
      else if ("getUrlDownload".equals(methodName)) {
        String urlDownload = (String)m.invoke(o);
        dl.setUrlDownload(urlDownload);
      }
    }
    return dl;
    
    
  }


  
  public static OrdineOutputBean getOrdineOutputBean(Object source) throws Exception {
    if (source == null) return null;
    //
    OrdineOutputBean outBean = (OrdineOutputBean) getGetSetAutoAssign(source, new OrdineOutputBean());
    if (source instanceof fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineOutputBean) {
      outBean.setOrdineOutputBean2005001((fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineOutputBean)source);
    }
    else if (source instanceof fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineOutputBean) {
      outBean.setOrdineOutputBean2010001((fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineOutputBean)source);
    }
    else {
      throw new IllegalArgumentException("classe non gestita: "+source.getClass().getName());
    }
    return outBean;
  }
  
  public static fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean getArticoloInputBean_2005_001(ArticoloInputBean source) throws Exception {
    if (source == null) return null;
    //
    fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean target = new fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean();
    return (fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean)getGetSetAutoAssign(source, target);
  }

  public static fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean getArticoloInputBean_2010_001(ArticoloInputBean source) throws Exception {
    if (source == null) return null;
    //
    fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean target = new fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean();
    return (fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean)getGetSetAutoAssign(source, target);
  }

  public static fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineInputBean getOrdineInputBean_2005_001(OrdineInputBean source) throws Exception {
    if (source == null) return null;
    //
    fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineInputBean target = new fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineInputBean();
    target = (fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineInputBean)getGetSetAutoAssign(source, target);
    List<fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean> articoli = new ArrayList<fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean>();
    for (ArticoloInputBean articoloInputBean : source.getArticoli()) {
      articoli.add(getArticoloInputBean_2005_001(articoloInputBean));
    }
    target.setArrayArticoliInput((fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean[])articoli.toArray(new fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean[0]));
    return target;
  }

  public static fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineInputBean getOrdineInputBean_2010_001(OrdineInputBean source) throws Exception {
    if (source == null) return null;
    //
    fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineInputBean target = new fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineInputBean();
    target = (fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineInputBean)getGetSetAutoAssign(source, target);
    List<fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean> articoli = new ArrayList<fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean>(); 
    for (ArticoloInputBean articoloInputBean : source.getArticoli()) {
      articoli.add(getArticoloInputBean_2010_001(articoloInputBean));
    }
    target.setArrayArticoliInput((fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean[])articoli.toArray(new fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean[0]));
    return target;
  }




  /**
   * Metodo di utilità ... anziché continuare a gestire in modo semi-manuale 
   * i get/set come fatto all'inzio (es., {@link #getDownloadListinoOutputBean(Object)),
   * questo metodo automatizza questa parte generalizzabile, limitando
   * l'intervento manuale ad eventuali trascodifiche
   * 
   * @param source
   * @param target
   * @return
   * @throws Exception
   */
  private static Object getGetSetAutoAssign(Object source, Object target) throws Exception {
    if (source == null) return null;
    //
    Class sourceClass = source.getClass();
    Class targetClass = target.getClass();
    Method [] srcMethods = sourceClass.getMethods();
    for (int i = 0; i < srcMethods.length; i++) {
      Method getMethod = srcMethods[i];
      String getMethodName = getMethod.getName();
      if (getMethodName.startsWith("get")) {
        String setMethodName = "set"+getMethodName.substring(3, getMethodName.length());
        try {
          Method setMethod = targetClass.getMethod(setMethodName, getMethod.getReturnType());
          setMethod.invoke(target, getMethod.invoke(source));
        }
        catch (NoSuchMethodException e) {
          // si tatta di metodi che esistono sull'oggetto "source",
          // ma non su "target" in quanto non necessari.
          // Esempio: "getTypeDesc" sul "source" che restituisce una
          // variabile Apache AXIS e che non avrebbe senzo sul 
          // target dove, appunto, per "setTypeDesc" si avrà
          // NoSuchMethodException
          if (debugMode) System.out.println(e.getMessage());
          
        }
      }
    }
    return target;
  }
  
  
}
