package fai.imp.farmaclick.ws.bean;

public class ArticoloInputBean {
	//
	// a meno che non sia diversamente indicato, gli attributi
	// sono supportati dall'api_2005_001 e dall'api_2010_001
	//

	private boolean prenotazione;                      
	private int accettazioneAllinemantoColloInVendita; // api_2005_001
	private int accettazioneConsegnaDifferita;         // api_2005_001
	private int accettazioneProdottoSostitutivo;       // api_2005_001
	private int quantitaRichiesta;                     
	private java.lang.String codiceProdotto;           
	private java.lang.String noteRiga;                 
	private boolean auto;                               // api_2010_001
	private java.lang.String rifRiga;                   // api_2010_001


	public ArticoloInputBean(boolean prenotazione, int accettazioneAllinemantoColloInVendita,
			int accettazioneConsegnaDifferita, int accettazioneProdottoSostitutivo, int quantitaRichiesta,
			String codiceProdotto, String noteRiga) {
		super();
		this.prenotazione = prenotazione;
		this.accettazioneAllinemantoColloInVendita = accettazioneAllinemantoColloInVendita;
		this.accettazioneConsegnaDifferita = accettazioneConsegnaDifferita;
		this.accettazioneProdottoSostitutivo = accettazioneProdottoSostitutivo;
		this.quantitaRichiesta = quantitaRichiesta;
		this.codiceProdotto = codiceProdotto;
		this.noteRiga = noteRiga;
	}
	
    public ArticoloInputBean(
            int accettazioneAllinemantoColloInVendita,
            int accettazioneConsegnaDifferita,
            int accettazioneProdottoSostitutivo,
            java.lang.String codiceProdotto,
            boolean prenotazione,
            int quantitaRichiesta) {
            this.accettazioneAllinemantoColloInVendita = accettazioneAllinemantoColloInVendita;
            this.accettazioneConsegnaDifferita = accettazioneConsegnaDifferita;
            this.accettazioneProdottoSostitutivo = accettazioneProdottoSostitutivo;
            this.codiceProdotto = codiceProdotto;
            this.prenotazione = prenotazione;
            this.quantitaRichiesta = quantitaRichiesta;
     }
	
	public boolean isPrenotazione() {
		return prenotazione;
	}
	public void setPrenotazione(boolean prenotazione) {
		this.prenotazione = prenotazione;
	}
	public int getAccettazioneAllinemantoColloInVendita() {
		return accettazioneAllinemantoColloInVendita;
	}
	/**
	 * ATTENZIONE! SOLO API 2005_001!!<br>
	 * <br>
	 * se del prodotto sono venduti solo multipli di 5 e se ne ordinano 4; 
	 * se -1 NON accetti l'arrotondamento e il prodotto viene dato mancante; 
	 * 1 si accetta l'arrotondamento; 
	 * 0 = default (-1 o 1) deciso dal grossista<br>
	 * 
	 * @param accettazioneAllinemantoColloInVendita
	 */
	public void setAccettazioneAllinemantoColloInVendita(int accettazioneAllinemantoColloInVendita) {
		this.accettazioneAllinemantoColloInVendita = accettazioneAllinemantoColloInVendita;
	}
	public int getAccettazioneConsegnaDifferita() {
		return accettazioneConsegnaDifferita;
	}
	/**
	 * ATTENZIONE! SOLO API 2005_001!!<br>
	 * <br>
	 * alcuni grossisti rigirano i loro mancati automaticamente a più deposito e quindi la erce arrivi in tempi diversi in farmacia: 
	 * -1 SOLO da magazzino principale al quale si sta facendo l'ordine; 
	 * 1 si accettanbo le deviazioni; 
	 * 0 default del grossista "come nel caso dell'ordine comproject"
	 * 
	 * @param accettazioneConsegnaDifferita
	 */
	public void setAccettazioneConsegnaDifferita(int accettazioneConsegnaDifferita) {
		this.accettazioneConsegnaDifferita = accettazioneConsegnaDifferita;
	}
	public int getAccettazioneProdottoSostitutivo() {
		return accettazioneProdottoSostitutivo;
	}
	/**
	 * ATTENZIONE! SOLO API 2005_001!!<br>
	 * <br>
	 * chiedo Acqua Ossigenata XX, ma il grossista ha disponibile Aqua Ossigenata YY: 
	 * -1 NON accetto il prodotto sostitutivo; 
	 * 1 accetto; 
	 * 0 default del grossista "come nell'ordine comproject"
	 *  
	 * @param accettazioneProdottoSostitutivo
	 */
	public void setAccettazioneProdottoSostitutivo(int accettazioneProdottoSostitutivo) {
		this.accettazioneProdottoSostitutivo = accettazioneProdottoSostitutivo;
	}
	public int getQuantitaRichiesta() {
		return quantitaRichiesta;
	}
	public void setQuantitaRichiesta(int quantitaRichiesta) {
		this.quantitaRichiesta = quantitaRichiesta;
	}
	public java.lang.String getCodiceProdotto() {
		return codiceProdotto;
	}
	public void setCodiceProdotto(java.lang.String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}
	public java.lang.String getNoteRiga() {
		return noteRiga;
	}
	public void setNoteRiga(java.lang.String noteRiga) {
		this.noteRiga = noteRiga;
	}
	public boolean isAuto() {
		return auto;
	}

	/**
	 * ATTENZIONE! SOLO API 2010_001!!<br>
	 * <br>
	 * Flag che indica la presenza del prodotto sull’automatico della farmacia.<br>
	 * Si faccia riferimento alla sezione Gestione Prodotti su Automatico in Farmacia
	 * 
	 * @param auto
	 */
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public java.lang.String getRifRiga() {
		return rifRiga;
	}
	/**
	 * ATTENZIONE! SOLO API 2010_001!!<br>
	 * <br>
	 * Riferimento riga ordine. Il valore specificato su questo campo viene riportato 
	 * sulle corrispondenti righe di output. Farmaclick non esegue nessun controllo sulla 
	 * presenza/congruenza/univocità di tale campo, si limita solo a riportare tale il 
	 * riferimento sulle righe di risposta.
	 * @param rifRiga
	 */
	public void setRifRiga(java.lang.String rifRiga) {
		this.rifRiga = rifRiga;
	}





}
