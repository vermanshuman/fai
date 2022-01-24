package fai.broker.db;

import java.util.Hashtable;

import fai.broker.models.ApprovvigionamentoFarmaco;
import it.swdes.models.AbstractModel;

/**
 * Restituisce il nome del campo della tabella corrispondente all'attributo 
 * dell' {@link AbstractModel} passato
 * 
 * @author Gianni Radice
 *
 */
public class ModelAttr2TableColConverter {
  
  private static Hashtable<Integer, String>  approvvigionamentoFarmacoTableColByAmField = new Hashtable<Integer, String>();
  static {
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.OID                          , "OID");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.MAGAZZINO                    , "OID_MAGAZZINO");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.FORNITORE                    , "OID_FORNITORE");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.CODICE_MIN_SAN               , "CODICE_MINSAN");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.CODICE_EAN                   , "CODICE_EAN");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.QUANTITA                     , "QUANTITA");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_REQ  , "DISPONIBILITA_RICHIESTA_REQ");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_RES  , "DISPONIBILITA_RICHIESTA_RES");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_REQ , "DISPONIBILITA_CONFERMATA_REQ");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_RES , "DISPONIBILITA_CONFERMATA_RES");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.ID_ORDINE                    , "ID_ORDINE");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.ID_RICEVUTA                  , "ID_RICEVUTA");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.ORDINE_OUT                   , "OID_ORDINEOUT");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.PREZZO_UNITARIO              , "PREZZO_UNITARIO");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.PREZZO_TOTALE                , "PREZZO_TOTALE");
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.ALIQUOTA_IVA                 , "ALIQUOTA_IVA");   
    approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.ALIQUOTA_IVA_INCLUSA         , "ALIQUOTA_IVA_INCLUSA");
    // approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.STATUS               <-- non gestito, è un campo composto                       
    // approvvigionamentoFarmacoTableColByAmField.put(ApprovvigionamentoFarmaco.APPROV_RIGA_DETT     <-- non gestito, c'è l'associativa        

    
  }
  
  public String getTableColName(Class am, int fieldIdx) {
    if (am == null) throw new IllegalArgumentException("istanza null di "+AbstractModel.class.getName()+" non ammessa");
    if (am.getName().equals(ApprovvigionamentoFarmaco.class.getName())) {
      return getApprovvigionamentoFarmaco(fieldIdx);
    }
    new IllegalArgumentException("sottoclasse di "+AbstractModel.class.getName()+" non gestita: "+am.getName());
    return null; // <-- inutile, dovrebbe essere "unreachable code", ma il compilatore non si fa capace e vuole la return ...
  }

  private String getApprovvigionamentoFarmaco(int fieldIdx) {
    String columnName = approvvigionamentoFarmacoTableColByAmField.get(fieldIdx);
    if (columnName == null) throw new IllegalArgumentException("inammissibile, nessuna corrispondenza per l'attributo di indice "+fieldIdx);
    return columnName;
  }
}
