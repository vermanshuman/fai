package fai.imp.farmaclick.ws;

import org.apache.log4j.Logger;

import fai.common.util.ExceptionsTool;
import fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.DownloadListinoInputBean2008001;
import fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.DownloadListinoOutputBean2008001;
import fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001;
import fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001FCKDownloadListinoSoapBindingStub;
import fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001ServiceLocator;

class FarmaclickWS_2008_001 extends AbstractFarmaclickWS {
  
  
  static Logger logger = Logger.getLogger(FarmaclickWS_2008_001.class);


  public DownloadListinoOutputBean2008001 callDownloadListino(String IDSessione, String codiceFornitore, String tipoRichiesta, String formatoOutput) {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    Farmaclick2008001 binding = null;
    DownloadListinoOutputBean2008001 outBean = null;
    try {
      //
      // --- binding ---
      //
      Farmaclick2008001ServiceLocator locator = new Farmaclick2008001ServiceLocator();
      locator.setFarmaclick2008001FCKDownloadListinoEndpointAddress(endpointAddress);
      logger.info(LOG_PREFIX + "endpoint address: "+locator.getFarmaclick2008001FCKDownloadListinoAddress());
      binding = (Farmaclick2008001)locator.getFarmaclick2008001FCKDownloadListino();
      //
      // --- call ---
      //
      DownloadListinoInputBean2008001 inBean = new DownloadListinoInputBean2008001(IDSessione, codiceFornitore, formatoOutput, tipoRichiesta);
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
