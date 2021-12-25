package fai.imp.farmaclick.ws;

import java.util.List;

import org.apache.log4j.Logger;

import fai.common.util.ExceptionsTool;
import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloInputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.DettaglioArticoliInputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.DettaglioArticoliOutputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKDownloadListino.DownloadListinoInputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKDownloadListino.DownloadListinoOutputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineOutputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineInputBean;

class FarmaclickWS_2005_001 extends AbstractFarmaclickWS {
  
  
  static Logger logger = Logger.getLogger(FarmaclickWS_2005_001.class);

  
  public DownloadListinoOutputBean callDownloadListino(String IDSessione, String codiceFornitore, String tipoRichiesta, String formatoOutput) {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    fai.imp.farmaclick.soap.api_2005_001.FCKDownloadListino.Farmaclick2005001 binding = null;
    DownloadListinoOutputBean outBean = null;
    try {
      //
      // --- binding ---
      //
      fai.imp.farmaclick.soap.api_2005_001.FCKDownloadListino.Farmaclick2005001ServiceLocator locator = new fai.imp.farmaclick.soap.api_2005_001.FCKDownloadListino.Farmaclick2005001ServiceLocator();
      locator.setFarmaclick2005001FCKDownloadListinoEndpointAddress(endpointAddress);
      logger.info(LOG_PREFIX + "endpoint address: "+locator.getFarmaclick2005001FCKDownloadListinoAddress());
      binding = (fai.imp.farmaclick.soap.api_2005_001.FCKDownloadListino.Farmaclick2005001)locator.getFarmaclick2005001FCKDownloadListino();
      //
      // --- call ---
      //
      DownloadListinoInputBean inBean = new DownloadListinoInputBean(IDSessione, codiceFornitore, formatoOutput, tipoRichiesta);
      logRequestAsXml(LOG_PREFIX, inBean);
      outBean = binding.FCKDownloadListino(inBean);
      logEsitoServizio(LOG_PREFIX, outBean);
      logResponseAsXml(LOG_PREFIX, outBean);
      //
      // --- gestione risposta ---
      //
    }
    catch (javax.xml.rpc.ServiceException jre) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre));
        if(jre.getLinkedCause()!=null) {
          logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause()));
          logger.error(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause());
        }
        throw new IllegalStateException(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre), jre);
    }
    catch (Throwable th) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", th));
    }
    return outBean;
  }
  
  public DettaglioArticoliOutputBean callDisponibilita(java.lang.String IDSessione, String codiceFornitore, boolean descrizioneArticoli, boolean descrizioneMotivazioneMancanza, boolean indicazioneDepositoAllestimento, List<ArticoloInputBean> articoli) throws java.rmi.RemoteException {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.Farmaclick2005001 binding = null;
    DettaglioArticoliOutputBean outBean = null;
    try {
      //
      // --- binding ---
      //
      fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.Farmaclick2005001ServiceLocator locator = new fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.Farmaclick2005001ServiceLocator();
      locator.setFarmaclick2005001FCKDisponibilitaEndpointAddress(endpointAddress);
      logger.info(LOG_PREFIX + "endpoint address: "+locator.getFarmaclick2005001FCKDisponibilitaAddress());
      binding = (fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.Farmaclick2005001)locator.getFarmaclick2005001FCKDisponibilita();
      //
      // --- call ---
      //
      DettaglioArticoliInputBean inBean = new DettaglioArticoliInputBean(
          IDSessione,
          codiceFornitore,
          (ArticoloInputBean[])articoli.toArray(new ArticoloInputBean[0]),
          descrizioneArticoli,
          descrizioneMotivazioneMancanza,
          indicazioneDepositoAllestimento
          );
      logRequestAsXml(LOG_PREFIX, inBean);
      outBean = binding.FCKDisponibilita(inBean);
      //
      // --- gestione risposta ---
      //
      logEsitoServizio(LOG_PREFIX, outBean);
      logResponseAsXml(LOG_PREFIX, outBean);
    }
    catch (javax.xml.rpc.ServiceException jre) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre));
        if(jre.getLinkedCause()!=null) {
          logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause()));
          logger.error(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause());
        }
        throw new IllegalStateException(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre), jre);
    }
    catch (Throwable th) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", th));
    }
    return outBean;
  }

  public OrdineOutputBean callOrdine(OrdineInputBean inBean) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.Farmaclick2005001 binding = null;
    OrdineOutputBean outBean = null;
    try {
      //
      // --- binding ---
      //
      fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.Farmaclick2005001ServiceLocator locator = new fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.Farmaclick2005001ServiceLocator();
      locator.setFarmaclick2005001FCKOrdineEndpointAddress(endpointAddress);
      logger.info(LOG_PREFIX + "endpoint address: "+locator.getFarmaclick2005001FCKOrdineAddress());
      binding = (fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.Farmaclick2005001)locator.getFarmaclick2005001FCKOrdine();
      //
      // --- call ---
      //
      logRequestAsXml(LOG_PREFIX, inBean);
      outBean = binding.FCKOrdine(inBean);
      binding.FCKOrdine(null);
      //
      // --- gestione risposta ---
      //
      logEsitoServizio(LOG_PREFIX, outBean);
      logResponseAsXml(LOG_PREFIX, outBean);
    }
    catch (javax.xml.rpc.ServiceException jre) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre));
        if(jre.getLinkedCause()!=null) {
          logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause()));
          logger.error(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause());
        }
        throw new IllegalStateException(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre), jre);
    }
    catch (Throwable th) {
      logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", th));
    }
    return outBean;
  }
 
  
}

