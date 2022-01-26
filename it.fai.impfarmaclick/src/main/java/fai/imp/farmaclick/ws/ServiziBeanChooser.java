package fai.imp.farmaclick.ws;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import fai.imp.farmaclick.config.PropertiesLoader;
import org.apache.log4j.Logger;

import fai.common.util.CalendarWrapper;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ErogazioneServiziBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ServiziBean;

class ServiziBeanChooser {

  
  static Logger logger = Logger.getLogger(ServiziBeanChooser.class);

  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss/SSS");
  
  private static final int SERVIZIO_DISABILITATO = 0;
  private static final int SERVIZIO_ABILITATO_SENZA_REGOLAMENTAZIONI = 1;
  private static final int SERVIZIO_ABILITATO_CON_REGOLAMENTAZIONI = -1;
  
  private static final int TUTTI_I_GIORNI = 0;
  
  private FornitoreBean fornitore = null;
  private Calendar now = null;
  private int dayOfWeek = 0;
  
  private Hashtable<Integer, Integer> farmaclickDayOfWeekByCalendar = new Hashtable<Integer, Integer>();
  {
    farmaclickDayOfWeekByCalendar.put(Calendar.MONDAY   , 1);
    farmaclickDayOfWeekByCalendar.put(Calendar.TUESDAY  , 2);
    farmaclickDayOfWeekByCalendar.put(Calendar.WEDNESDAY, 3);
    farmaclickDayOfWeekByCalendar.put(Calendar.THURSDAY , 4);
    farmaclickDayOfWeekByCalendar.put(Calendar.FRIDAY   , 5);
    farmaclickDayOfWeekByCalendar.put(Calendar.SATURDAY , 6);
    farmaclickDayOfWeekByCalendar.put(Calendar.SUNDAY   , 7);

  }
  
  public ServiziBeanChooser setFornitoreBean(FornitoreBean fornitore) throws Exception {
    this.fornitore = fornitore;
    now = Calendar.getInstance();
    dayOfWeek = farmaclickDayOfWeekByCalendar.get(now.get(Calendar.DAY_OF_WEEK));
    return this;
  }
  
  public ServiziBean getServiziBean(String serviceName, String serviceApiLevel ) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME+": ";
    ServiziBean [] servizi = fornitore.getArrayServizi();
    ServiziBean selezionato = null;
    for (int i = 0; i < servizi.length; i++) {
      if (passed(servizi[i], serviceName) && (serviceApiLevel == null || servizi[i].getLivelloAPI().equalsIgnoreCase(serviceApiLevel))) {
        if (selezionato == null || servizi[i].getLivelloAPI().compareTo(selezionato.getLivelloAPI()) > 0) {
          selezionato = servizi[i];
        }
      }
    }
    if (selezionato == null) {
      logger.warn(LOG_PREFIX+"nessuna interfaccia disponibile per il servizio "+serviceName);
    }
    return selezionato;
  }
  
  private boolean passed(ServiziBean serviziBean, String serviceName) throws Exception {
    if (!serviceName.equals(serviziBean.getCodiceServizio())) return false;
    int abilitazione = serviziBean.getAbilitato();
    if (abilitazione == SERVIZIO_DISABILITATO) { 
      return false;
    }
    else if (abilitazione == SERVIZIO_ABILITATO_SENZA_REGOLAMENTAZIONI) {
      return true;
    }
    else if (abilitazione == SERVIZIO_ABILITATO_CON_REGOLAMENTAZIONI) {
      ErogazioneServiziBean [] es = serviziBean.getArrayErogazioneServizi();
      ErogazioneServiziBean selezionato = null;
      for (int i = 0; i < es.length && selezionato == null; i++) {
        if (passed(es[i])) {
          selezionato = es[i];
        }
      }
      return selezionato != null;
    }
    else {
      throw new IllegalStateException("inammissibile, abilitazione "+abilitazione+" non gestita (API aggiornata?)");
    }
    
  }
  
  
  private boolean passed(ErogazioneServiziBean es) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME+": ";
    boolean passed = false;
    if (es == null) {
      logger.error(ErogazioneServiziBean.class.getName()+" istanza null non attesa; sarà restituito "+passed );
      return passed ;
    }
    //
    Calendar orarioInizio = es.getOraInizio();
    Calendar orarioFine = es.getOraFine(); 
    if (orarioInizio != null || orarioFine != null) {
      // nel caso in cui uno dei due estremi fosse null, lo considero
      // "da/a tempo indeterminato", assegnadogli timestamp 
      // antecedente/successiovo a now
      if (orarioInizio == null) {
        orarioInizio = CalendarWrapper.getNowInstance().add(Calendar.DAY_OF_MONTH, -1).getCalendar();
        logger.warn(LOG_PREFIX+"inizio inizio disponibililtà del servizoi non specificato; assegnato "+df.format(orarioInizio.getTime()));
      }
      if (orarioFine == null) {
        orarioFine = CalendarWrapper.getNowInstance().add(Calendar.DAY_OF_MONTH, 1).getCalendar();
        logger.warn(LOG_PREFIX+"inizio fine disponibililtà del servizio non specificato; assegnato "+df.format(orarioFine.getTime()));
      }
      if (now.before(orarioInizio) || now.after(orarioFine)) {
        logger.info(LOG_PREFIX+"servizio disponibile dalle "+df.format(orarioInizio.getTime())+" alle "+df.format(orarioFine.getTime())+"");
        return false;
      }
    }
    int giorno = es.getGiorno();
    if (giorno == TUTTI_I_GIORNI) {
      return true;
    }
    else {
      return giorno == dayOfWeek;
    }
  }

}
