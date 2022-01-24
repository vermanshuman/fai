package fai.broker.supplier;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.DisponibilitaReqTemp;
import fai.broker.models.DisponibilitaResTemp;
import fai.broker.models.DisponibilitaTemp;
import fai.broker.models.Fornitore;
import fai.broker.models.Magazzino;
import fai.broker.models.StatusInfo;
import fai.broker.supplier.SupplierService.ManagedRequest;
import fai.broker.supplier.SupplierService.RequestMode;

/**
 * Si occupa, essenzialmente, della conversione tra le strutture
 * dati {@link ApprovvigionamentoFarmaco} e {@link DisponibilitaTemp}
 * 
 * @author Gianni Radice
 *
 */
public class ApprovFarmaco2DispTempConverter {
  
  protected Magazzino magazzino = null;
  protected Fornitore fornitore = null;
  protected Connection conn = null;
  protected List<ApprovvigionamentoFarmaco> fabbisognoRequest = null;
  // protected List<ApprovvigionamentoFarmaco> fabbisognoResponse = null;
  protected Hashtable<String, ApprovvigionamentoFarmaco> fabbisognoByMinSanEan = null; 
  protected List<DisponibilitaTemp> disponibilitaTempList = null;
  protected List<ManagedRequest> managedRequests = null;
  
  public void setup(Magazzino magazzino, Fornitore fornitore) throws Exception {
    this.magazzino = magazzino;
    this.fornitore = fornitore;
  }
  
  private boolean equals(Magazzino m1, Magazzino m2) throws Exception {
    if (m1 == null && m2 == null)
      return true;
    else if (m1 == null && m2 != null)
      return false;
    else if (m1 != null && m2 == null)
      return false;
    // 
    if (m1.getAcronym() != null && m2.getAcronym() != null) return m1.getAcronym().equals(m2.getAcronym());
    if (m1.getOid() != null && m2.getOid() != null) return m1.getOid().equals(m2.getOid());
    //
    throw new IllegalStateException("inammissibile, elementi insufficienti per confrontare i due Magazzini:\r\nMagazzino 1: "+m1.toXml()+"\r\nMagazzino 2: "+m2.toXml());
  }
  
  private boolean equals(Fornitore f1, Fornitore f2) throws Exception {
    if (f1 == null && f2 == null)
      return true;
    else if (f1 == null && f2 != null)
      return false;
    else if (f1 != null && f2 == null)
      return false;
    // 
    if (f1.getCodice() != null && f2.getCodice() != null) return f1.getCodice().equals(f2.getCodice());
    if (f1.getOid() != null && f2.getOid() != null) return f1.getOid().equals(f2.getOid());
    //
    throw new IllegalStateException("inammissibile, elementi insufficienti per confrontare i due Magazzini:\r\nFornitore 1: "+f1.toXml()+"\r\nFornitore 2: "+f2.toXml());
  }
  
  
  private String asKey(String minSan, String ean) {
    return minSan+"/"+ean;
  }

  private String asKey(ApprovvigionamentoFarmaco appr) {
    return asKey(appr.getCodiceMinSan(), appr.getCodiceEan());
  }

  private String asKey(DisponibilitaReqTemp dt) {
    return asKey(dt.getCodiceMinSan(), dt.getCodiceEan());
  }
  
  
  private void reset() {
    fabbisognoRequest = new ArrayList<ApprovvigionamentoFarmaco>(); 
    // fabbisognoResponse = new ArrayList<ApprovvigionamentoFarmaco>();
    disponibilitaTempList = new ArrayList<DisponibilitaTemp>();
    managedRequests = new ArrayList<ManagedRequest>();
    fabbisognoByMinSanEan = new Hashtable<String, ApprovvigionamentoFarmaco>();
  }

  
  
  
  public List<DisponibilitaTemp> setFabbisognoRequest(List<ApprovvigionamentoFarmaco> fabbisognoRequest, RequestMode requestMode) throws Exception {
    if (magazzino == null && fornitore == null) throw new IllegalStateException("istanza "+this.getClass().getName()+" non correttamente inizializzata: è necessario specificare o il Magazzino o il Fornitore");
    if (magazzino != null && fornitore != null) throw new IllegalStateException("istanza "+this.getClass().getName()+" non correttamente inizializzata: è necessario specificare o il Magazzino o il Fornitore; sono stati specificati entrambi");
    reset();
    this.fabbisognoRequest = fabbisognoRequest;
    //
    DisponibilitaTemp dt = null; 
    //
    for (ApprovvigionamentoFarmaco appr : fabbisognoRequest) {
      //
      if (RequestMode.OneProductOneRequest.equals(requestMode)|| (dt == null && RequestMode.MoreProductOneRequest.equals(requestMode))) {
        dt = new DisponibilitaTemp();
        dt.setMagazzino(magazzino);
        dt.setFornitore(fornitore);
        dt.setStatus(StatusInfo.newToProcessInstance(null, null));
        disponibilitaTempList.add(dt);
      }
      //
      if (equals(appr.getMagazzino(), magazzino) == false && equals(appr.getFornitore(), fornitore) == false) throw new IllegalStateException("inammissibile, Magazzino o Fornitore specificato per il fabbisogno (FAI_APPROVVIGIONAMENTO_FARMACO) identificato dall'OID "+appr.getOid()+" non coincidente col Magazzino o Fornitore per quale opera questa implementazione di "+SupplierService.class.getName()+";\r\n"+appr.toXml());
      //
      String productAsKey = asKey(appr);
      if (fabbisognoByMinSanEan.get(productAsKey) != null) {
    	  throw new IllegalStateException("inammissibile coppia MinSan/Ean "+productAsKey+" per il fabbisogno (FAI_APPROVVIGIONAMENTO_FARMACO) identificato dall'OID "+appr.getOid()+" è già presente almeno un'altra volta in questa richiesta");
      }
      fabbisognoByMinSanEan.put(productAsKey, appr);
      //
      DisponibilitaReqTemp req = new DisponibilitaReqTemp();
      req.setCodiceMinSan(appr.getCodiceMinSan());
      req.setCodiceEan(appr.getCodiceEan());
      req.setQuantitaRichiesta(appr.getQuantita());
      dt.addReq(req);
    }
    return disponibilitaTempList;
  }
  
  
  public List<DisponibilitaTemp> getDisponibilitaTempListBeforeRequest() throws Exception {
    return disponibilitaTempList;
  }
  
  public void setDisponibilitaTempListAfterResponse(List<DisponibilitaTemp> disponibilitaTempList) throws Exception {
    this.disponibilitaTempList = disponibilitaTempList;
    for (DisponibilitaTemp dt : this.disponibilitaTempList) {
      DisponibilitaReqTemp [] req = dt.getAllReq();
      for (int i = 0; i < req.length; i++) {
        if (req[i].getRespCount() == 0) throw new IllegalStateException("nessun elemento "+DisponibilitaResTemp.class.getName()+" per la "+DisponibilitaResTemp.class.getName()+" MinSan/Ean "+req[i].getCodiceMinSan()+"/"+req[i].getCodiceEan()+", quantità richiesta "+req[i].getQuantitaRichiesta());
        //
        String productAsKey = asKey(req[i]);
        ApprovvigionamentoFarmaco fabbisogno = fabbisognoByMinSanEan.get(productAsKey);
        if (fabbisogno == null) throw new IllegalStateException("inammissibile, nessun fabbisogno ("+ApprovvigionamentoFarmaco.class.getName()+") per la coppia MinSan/Ean "+productAsKey+" benché sia disponibile una riposta per "+req[i].getRespCount()+" differenti opzioni di quantità disponibili");
        //
        List<ApprovvigionamentoFarmaco> disponibilitaList = new ArrayList<ApprovvigionamentoFarmaco>();
        DisponibilitaResTemp [] res = req[i].getAllResp();
        for (int j = 0; j < res.length; j++) {
          ApprovvigionamentoFarmaco disponibilita = new ApprovvigionamentoFarmaco();
          disponibilita.copyFrom(fabbisogno);
          disponibilita.setOid(null /* perché è una "risposta", l'OID non ce l'ha perché non ancora registrata in banca dati */ );
          disponibilita.setQuantita(res[j].getQuantitaDisponibile());
          disponibilitaList.add(disponibilita);
          // fabbisognoResponse.add(disponibilita);
        }
        ManagedRequest mr = new ManagedRequest(fabbisogno, disponibilitaList);
        managedRequests.add(mr);
   
      }
    }
  }
  
  
  public List<ManagedRequest> getManagedRequests() {
    return managedRequests;
  }
  
  
  

}
