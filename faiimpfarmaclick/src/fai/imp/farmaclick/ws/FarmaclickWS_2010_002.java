package fai.imp.farmaclick.ws;

import org.apache.log4j.Logger;

import fai.common.util.ExceptionsTool;
import fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.DownloadListinoInputBean;
import fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.DownloadListinoOutputBean;
import fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002;
import fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002FCKDownloadListinoSoapBindingStub;
import fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002ServiceLocator;

class FarmaclickWS_2010_002 extends AbstractFarmaclickWS {
  
  
  static Logger logger = Logger.getLogger(FarmaclickWS_2010_002.class);


  public DownloadListinoOutputBean callDownloadListino(String IDSessione, String codiceFornitore, String tipoRichiesta) {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    Farmaclick2010002 binding = null;
    DownloadListinoOutputBean outBean = null;
    try {
      //
      // --- binding ---
      //
      Farmaclick2010002ServiceLocator locator = new Farmaclick2010002ServiceLocator();
      locator.setFarmaclick2010002FCKDownloadListinoEndpointAddress(endpointAddress);
      logger.info(LOG_PREFIX + "endpoint address: "+locator.getFarmaclick2010002FCKDownloadListinoAddress());
      binding = (Farmaclick2010002)locator.getFarmaclick2010002FCKDownloadListino();
      //
      // --- call ---
      //
      DownloadListinoInputBean inBean = new DownloadListinoInputBean(IDSessione, codiceFornitore, tipoRichiesta);
      logRequestAsXml(LOG_PREFIX, inBean);
      outBean = binding.FCKDownloadListino(inBean);
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
