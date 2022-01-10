package fai.imp.farmaclick.ws.bean;

/**
 * le versioni 2005.001 e 2010.001 sono troppo differenti per essere
 * uniformate, o, almeno, sono troppo differenti per essere uniformate
 * agevolmente.<br/>
 * <br>
 * Si consideri, ad esempio, il bean {@code ArticoloOutputBean}
 * <br>
 * La 2010.001 possiede l'attributo array {@code arrayArticoli}  
 * (di tipo/classe {@code ArticoloOutputBean})
 * il quale, a sua volta, contiene {@code arrayConsegna}
 * (tipo {@code ConsegnaOutputBean}).
 * In pratica, con sintassi XPath, {@code ArticoloOutputBean} 
 * dell'API 2010.001 è così strutturato:<br/> 
 * <pre>
 *    /ArticoloOutputBean/arrayArticoli[]/arrayConsegna[] // 2010.001
 * </pre>
 * Per gli stessi due attributi, {@code arrayArticoli} (che, però,
 * diventa di tipo {@code ArticoloValorizzatoOutputBean}}) e 
 * {@code arrayConsegna} ({@code ConsegnaOutputBean}), 
 * {@code ArticoloOutputBean}, invece, {@code ArticoloOutputBean} 
 * dell'API 2005.001 risulta così strutturato:<br/> 
 * <pre>
 *    /ArticoloOutputBean/arrayArticoli[] // 2005.001
 *    /ArticoloOutputBean/arrayConsegna[] // 2005.001
 * </pre>
 * Stante quanto sopra, si sceglie di:
 * <ul>
 * <li>adottare come attributi di questo bean, l'unione dei soli attributi scalari (facilmente gestibili in automatico dalla reflection)</li>
 * <li>prevedere due metodi {@code get} per l'accesso diretto al bean restituito dall'API 2005.001 e al bean restituito dall'API 2010.001
 * <li>aggiungere in futuro quanto si renderà necessario (es., {@code public Calendar getDataConsegna(...critieri...)})</li>
 * </ul>
 * 
 * @author Gianni Radice
 * 
 */


public class OrdineOutputBean {

  private fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineOutputBean ordineOutputBean2005001;
  private fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineOutputBean ordineOutputBean2010001;

  private java.lang.String noteOrdine;            // api_2005_001, api_2010_001
  private java.lang.String numeroOrdineFornitore; // api_2005_001
  private int delayProsRichiesta;                 //               api_2010_001
    private int esitoServizio;                      //               api_2010_001

  public OrdineOutputBean() {
   
  }

  public fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineOutputBean getOrdineOutputBean2005001() {
    return ordineOutputBean2005001;
  }

  public void setOrdineOutputBean2005001(fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.OrdineOutputBean ordineOutputBean2005001) {
    this.ordineOutputBean2005001 = ordineOutputBean2005001;
  }

  public fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineOutputBean getOrdineOutputBean2010001() {
    return ordineOutputBean2010001;
  }

  public void setOrdineOutputBean2010001(fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.OrdineOutputBean ordineOutputBean2010001) {
    this.ordineOutputBean2010001 = ordineOutputBean2010001;
  }

  public java.lang.String getNoteOrdine() {
    return noteOrdine;
  }

  public void setNoteOrdine(java.lang.String noteOrdine) {
    this.noteOrdine = noteOrdine;
  }

  public java.lang.String getNumeroOrdineFornitore() {
    return numeroOrdineFornitore;
  }

  public void setNumeroOrdineFornitore(java.lang.String numeroOrdineFornitore) {
    this.numeroOrdineFornitore = numeroOrdineFornitore;
  }

  public int getDelayProsRichiesta() {
    return delayProsRichiesta;
  }

  public void setDelayProsRichiesta(int delayProsRichiesta) {
    this.delayProsRichiesta = delayProsRichiesta;
  }

  public int getEsitoServizio() {
    return esitoServizio;
  }

  public void setEsitoServizio(int esitoServizio) {
    this.esitoServizio = esitoServizio;
  }
  
  
  
  

 
}
