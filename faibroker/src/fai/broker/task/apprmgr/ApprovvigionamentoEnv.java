package fai.broker.task.apprmgr;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import fai.broker.models.ApprovToRiga;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.Fornitore;
import fai.broker.models.Magazzino;
import fai.broker.supplier.SupplierService;
import fai.broker.util.AnagraficaFarmaciMinSanEanCache;

/**
 * Raccoglie tutte le istanze degli oggetti utili nel contesto
 * è utilizzato
 * 
 * @author Gianni Radice
 *
 */
class ApprovvigionamentoEnv {

  private AnagraficaFarmaciMinSanEanCache anagrafica = null;
  
  private Hashtable<String, SupplierService> supplierServicesByMagazzinoAcronym = new Hashtable<String, SupplierService>();

  private Hashtable<String, SupplierService> supplierServicesByFornitoreAcronym = new Hashtable<String, SupplierService>();
  
  private List<Fornitore> fornitori = new ArrayList<Fornitore>();
  private Hashtable<Long, Fornitore> fornitoreByOid = new Hashtable<Long, Fornitore>();
  private List<Magazzino> magazzini = new ArrayList<Magazzino>();
  private Set<Long> selectedFornitori = new HashSet<Long>();

  public AnagraficaFarmaciMinSanEanCache getAnagrafica() {
    return anagrafica;
  }

  public void setAnagrafica(AnagraficaFarmaciMinSanEanCache anagrafica) {
    this.anagrafica = anagrafica;
  }
  
  public void addMagazzinoSupplierService(String magazzinoAcronym, SupplierService ss) {
    supplierServicesByMagazzinoAcronym.put(magazzinoAcronym, ss);
  }
  
  public void addFornitoreSupplierService(String fornitoreAcronym, SupplierService ss) {
    supplierServicesByFornitoreAcronym.put(fornitoreAcronym, ss);
  }

  public SupplierService getMagazzinoSupplierService(String magazzinoAcronym) {
    return supplierServicesByMagazzinoAcronym.get(magazzinoAcronym);
  }
  
  public SupplierService getFornitoreSupplierService(String fornitoreAcronym) {
    return supplierServicesByFornitoreAcronym.get(fornitoreAcronym);
  }

  public List<Fornitore> getFornitori() {
    return fornitori;
  }
  
  public Fornitore getFornitoreByOid(Long oid, boolean mustExists) throws Exception {
    if (oid == null) throw new IllegalArgumentException("OID null non ammesso");
    //
    Fornitore f = fornitoreByOid.get(oid);
    if (f == null && mustExists) throw new IllegalStateException("inammissibile, nessun Fornitore per l'OID "+oid);
    //
    return f;
  }

  public void setFornitori(List<Fornitore> fornitori) {
    fornitoreByOid.clear();
    this.fornitori = fornitori;
    for (Fornitore fornitore : fornitori) {
      fornitoreByOid.put(fornitore.getOid(), fornitore);
    }
  }

  public List<Magazzino> getMagazzini() {
    return magazzini;
  }

  public void setMagazzini(List<Magazzino> magazzini) {
    this.magazzini = magazzini;
  }

  public void setAllSupplierServiceCacheEnabled(boolean value) throws Exception {
    List<Hashtable<String, SupplierService>> list = new ArrayList<Hashtable<String,SupplierService>>();
    for (Hashtable<String, SupplierService> ht : list) {
      Enumeration<SupplierService> en = ht.elements();
      while (en.hasMoreElements()) {
        SupplierService ss = en.nextElement();
        ss.setCacheEnabled(value);
      }
    }
  }
  
  public Set<Long> getSelectedFornitori() {
	return selectedFornitori;
  }
	
  public void setSelectedFornitori(Set<Long> selectedFornitori) {
	this.selectedFornitori = selectedFornitori;
  }
}
