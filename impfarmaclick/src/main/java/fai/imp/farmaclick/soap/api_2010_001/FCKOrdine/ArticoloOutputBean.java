/**
 * ArticoloOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKOrdine;

public class ArticoloOutputBean  implements java.io.Serializable {
    private fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ConsegnaOutputBean[] arrayConsegna;

    private fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ConsegnaDifferitaOutputBean[] arrayConsegnaDifferita;

    private fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.FatturazioneOutputBean[] arrayFatturazione;

    private java.lang.String codiceMancanza;

    private java.lang.String codiceOmaggioAlternativo;

    private java.lang.String codiceProdotto;

    private java.lang.String codiceProdottoSostituito;

    private boolean consegnaRitardata;

    private java.util.Calendar dataPrevistaConsegnaPrenotazione;

    private java.util.Calendar dataPrevistaDisponibilita;

    private java.lang.String descrizioneMancanza;

    private java.lang.String descrizioneOmaggioAlternativo;

    private java.lang.String descrizioneProdotto;

    private java.lang.String descrizioneProdottoSostituito;

    private double importoNettoRiga;

    private java.lang.String note;

    private boolean prenotabile;

    private double prezzoConsigliato;

    private double prezzoNettoUnitario;

    private int quantitaConsegnata;

    private int quantitaMancante;

    private int quantitaOmaggio;

    private int quantitaOmaggioAlternativo;

    private int quantitaPrenotata;

    private int quantitaRichiesta;

    private java.lang.String rifRiga;

    public ArticoloOutputBean() {
    }

    public ArticoloOutputBean(
           fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ConsegnaOutputBean[] arrayConsegna,
           fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ConsegnaDifferitaOutputBean[] arrayConsegnaDifferita,
           fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.FatturazioneOutputBean[] arrayFatturazione,
           java.lang.String codiceMancanza,
           java.lang.String codiceOmaggioAlternativo,
           java.lang.String codiceProdotto,
           java.lang.String codiceProdottoSostituito,
           boolean consegnaRitardata,
           java.util.Calendar dataPrevistaConsegnaPrenotazione,
           java.util.Calendar dataPrevistaDisponibilita,
           java.lang.String descrizioneMancanza,
           java.lang.String descrizioneOmaggioAlternativo,
           java.lang.String descrizioneProdotto,
           java.lang.String descrizioneProdottoSostituito,
           double importoNettoRiga,
           java.lang.String note,
           boolean prenotabile,
           double prezzoConsigliato,
           double prezzoNettoUnitario,
           int quantitaConsegnata,
           int quantitaMancante,
           int quantitaOmaggio,
           int quantitaOmaggioAlternativo,
           int quantitaPrenotata,
           int quantitaRichiesta,
           java.lang.String rifRiga) {
           this.arrayConsegna = arrayConsegna;
           this.arrayConsegnaDifferita = arrayConsegnaDifferita;
           this.arrayFatturazione = arrayFatturazione;
           this.codiceMancanza = codiceMancanza;
           this.codiceOmaggioAlternativo = codiceOmaggioAlternativo;
           this.codiceProdotto = codiceProdotto;
           this.codiceProdottoSostituito = codiceProdottoSostituito;
           this.consegnaRitardata = consegnaRitardata;
           this.dataPrevistaConsegnaPrenotazione = dataPrevistaConsegnaPrenotazione;
           this.dataPrevistaDisponibilita = dataPrevistaDisponibilita;
           this.descrizioneMancanza = descrizioneMancanza;
           this.descrizioneOmaggioAlternativo = descrizioneOmaggioAlternativo;
           this.descrizioneProdotto = descrizioneProdotto;
           this.descrizioneProdottoSostituito = descrizioneProdottoSostituito;
           this.importoNettoRiga = importoNettoRiga;
           this.note = note;
           this.prenotabile = prenotabile;
           this.prezzoConsigliato = prezzoConsigliato;
           this.prezzoNettoUnitario = prezzoNettoUnitario;
           this.quantitaConsegnata = quantitaConsegnata;
           this.quantitaMancante = quantitaMancante;
           this.quantitaOmaggio = quantitaOmaggio;
           this.quantitaOmaggioAlternativo = quantitaOmaggioAlternativo;
           this.quantitaPrenotata = quantitaPrenotata;
           this.quantitaRichiesta = quantitaRichiesta;
           this.rifRiga = rifRiga;
    }


    /**
     * Gets the arrayConsegna value for this ArticoloOutputBean.
     * 
     * @return arrayConsegna
     */
    public fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ConsegnaOutputBean[] getArrayConsegna() {
        return arrayConsegna;
    }


    /**
     * Sets the arrayConsegna value for this ArticoloOutputBean.
     * 
     * @param arrayConsegna
     */
    public void setArrayConsegna(fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ConsegnaOutputBean[] arrayConsegna) {
        this.arrayConsegna = arrayConsegna;
    }


    /**
     * Gets the arrayConsegnaDifferita value for this ArticoloOutputBean.
     * 
     * @return arrayConsegnaDifferita
     */
    public fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ConsegnaDifferitaOutputBean[] getArrayConsegnaDifferita() {
        return arrayConsegnaDifferita;
    }


    /**
     * Sets the arrayConsegnaDifferita value for this ArticoloOutputBean.
     * 
     * @param arrayConsegnaDifferita
     */
    public void setArrayConsegnaDifferita(fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ConsegnaDifferitaOutputBean[] arrayConsegnaDifferita) {
        this.arrayConsegnaDifferita = arrayConsegnaDifferita;
    }


    /**
     * Gets the arrayFatturazione value for this ArticoloOutputBean.
     * 
     * @return arrayFatturazione
     */
    public fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.FatturazioneOutputBean[] getArrayFatturazione() {
        return arrayFatturazione;
    }


    /**
     * Sets the arrayFatturazione value for this ArticoloOutputBean.
     * 
     * @param arrayFatturazione
     */
    public void setArrayFatturazione(fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.FatturazioneOutputBean[] arrayFatturazione) {
        this.arrayFatturazione = arrayFatturazione;
    }


    /**
     * Gets the codiceMancanza value for this ArticoloOutputBean.
     * 
     * @return codiceMancanza
     */
    public java.lang.String getCodiceMancanza() {
        return codiceMancanza;
    }


    /**
     * Sets the codiceMancanza value for this ArticoloOutputBean.
     * 
     * @param codiceMancanza
     */
    public void setCodiceMancanza(java.lang.String codiceMancanza) {
        this.codiceMancanza = codiceMancanza;
    }


    /**
     * Gets the codiceOmaggioAlternativo value for this ArticoloOutputBean.
     * 
     * @return codiceOmaggioAlternativo
     */
    public java.lang.String getCodiceOmaggioAlternativo() {
        return codiceOmaggioAlternativo;
    }


    /**
     * Sets the codiceOmaggioAlternativo value for this ArticoloOutputBean.
     * 
     * @param codiceOmaggioAlternativo
     */
    public void setCodiceOmaggioAlternativo(java.lang.String codiceOmaggioAlternativo) {
        this.codiceOmaggioAlternativo = codiceOmaggioAlternativo;
    }


    /**
     * Gets the codiceProdotto value for this ArticoloOutputBean.
     * 
     * @return codiceProdotto
     */
    public java.lang.String getCodiceProdotto() {
        return codiceProdotto;
    }


    /**
     * Sets the codiceProdotto value for this ArticoloOutputBean.
     * 
     * @param codiceProdotto
     */
    public void setCodiceProdotto(java.lang.String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }


    /**
     * Gets the codiceProdottoSostituito value for this ArticoloOutputBean.
     * 
     * @return codiceProdottoSostituito
     */
    public java.lang.String getCodiceProdottoSostituito() {
        return codiceProdottoSostituito;
    }


    /**
     * Sets the codiceProdottoSostituito value for this ArticoloOutputBean.
     * 
     * @param codiceProdottoSostituito
     */
    public void setCodiceProdottoSostituito(java.lang.String codiceProdottoSostituito) {
        this.codiceProdottoSostituito = codiceProdottoSostituito;
    }


    /**
     * Gets the consegnaRitardata value for this ArticoloOutputBean.
     * 
     * @return consegnaRitardata
     */
    public boolean isConsegnaRitardata() {
        return consegnaRitardata;
    }


    /**
     * Sets the consegnaRitardata value for this ArticoloOutputBean.
     * 
     * @param consegnaRitardata
     */
    public void setConsegnaRitardata(boolean consegnaRitardata) {
        this.consegnaRitardata = consegnaRitardata;
    }


    /**
     * Gets the dataPrevistaConsegnaPrenotazione value for this ArticoloOutputBean.
     * 
     * @return dataPrevistaConsegnaPrenotazione
     */
    public java.util.Calendar getDataPrevistaConsegnaPrenotazione() {
        return dataPrevistaConsegnaPrenotazione;
    }


    /**
     * Sets the dataPrevistaConsegnaPrenotazione value for this ArticoloOutputBean.
     * 
     * @param dataPrevistaConsegnaPrenotazione
     */
    public void setDataPrevistaConsegnaPrenotazione(java.util.Calendar dataPrevistaConsegnaPrenotazione) {
        this.dataPrevistaConsegnaPrenotazione = dataPrevistaConsegnaPrenotazione;
    }


    /**
     * Gets the dataPrevistaDisponibilita value for this ArticoloOutputBean.
     * 
     * @return dataPrevistaDisponibilita
     */
    public java.util.Calendar getDataPrevistaDisponibilita() {
        return dataPrevistaDisponibilita;
    }


    /**
     * Sets the dataPrevistaDisponibilita value for this ArticoloOutputBean.
     * 
     * @param dataPrevistaDisponibilita
     */
    public void setDataPrevistaDisponibilita(java.util.Calendar dataPrevistaDisponibilita) {
        this.dataPrevistaDisponibilita = dataPrevistaDisponibilita;
    }


    /**
     * Gets the descrizioneMancanza value for this ArticoloOutputBean.
     * 
     * @return descrizioneMancanza
     */
    public java.lang.String getDescrizioneMancanza() {
        return descrizioneMancanza;
    }


    /**
     * Sets the descrizioneMancanza value for this ArticoloOutputBean.
     * 
     * @param descrizioneMancanza
     */
    public void setDescrizioneMancanza(java.lang.String descrizioneMancanza) {
        this.descrizioneMancanza = descrizioneMancanza;
    }


    /**
     * Gets the descrizioneOmaggioAlternativo value for this ArticoloOutputBean.
     * 
     * @return descrizioneOmaggioAlternativo
     */
    public java.lang.String getDescrizioneOmaggioAlternativo() {
        return descrizioneOmaggioAlternativo;
    }


    /**
     * Sets the descrizioneOmaggioAlternativo value for this ArticoloOutputBean.
     * 
     * @param descrizioneOmaggioAlternativo
     */
    public void setDescrizioneOmaggioAlternativo(java.lang.String descrizioneOmaggioAlternativo) {
        this.descrizioneOmaggioAlternativo = descrizioneOmaggioAlternativo;
    }


    /**
     * Gets the descrizioneProdotto value for this ArticoloOutputBean.
     * 
     * @return descrizioneProdotto
     */
    public java.lang.String getDescrizioneProdotto() {
        return descrizioneProdotto;
    }


    /**
     * Sets the descrizioneProdotto value for this ArticoloOutputBean.
     * 
     * @param descrizioneProdotto
     */
    public void setDescrizioneProdotto(java.lang.String descrizioneProdotto) {
        this.descrizioneProdotto = descrizioneProdotto;
    }


    /**
     * Gets the descrizioneProdottoSostituito value for this ArticoloOutputBean.
     * 
     * @return descrizioneProdottoSostituito
     */
    public java.lang.String getDescrizioneProdottoSostituito() {
        return descrizioneProdottoSostituito;
    }


    /**
     * Sets the descrizioneProdottoSostituito value for this ArticoloOutputBean.
     * 
     * @param descrizioneProdottoSostituito
     */
    public void setDescrizioneProdottoSostituito(java.lang.String descrizioneProdottoSostituito) {
        this.descrizioneProdottoSostituito = descrizioneProdottoSostituito;
    }


    /**
     * Gets the importoNettoRiga value for this ArticoloOutputBean.
     * 
     * @return importoNettoRiga
     */
    public double getImportoNettoRiga() {
        return importoNettoRiga;
    }


    /**
     * Sets the importoNettoRiga value for this ArticoloOutputBean.
     * 
     * @param importoNettoRiga
     */
    public void setImportoNettoRiga(double importoNettoRiga) {
        this.importoNettoRiga = importoNettoRiga;
    }


    /**
     * Gets the note value for this ArticoloOutputBean.
     * 
     * @return note
     */
    public java.lang.String getNote() {
        return note;
    }


    /**
     * Sets the note value for this ArticoloOutputBean.
     * 
     * @param note
     */
    public void setNote(java.lang.String note) {
        this.note = note;
    }


    /**
     * Gets the prenotabile value for this ArticoloOutputBean.
     * 
     * @return prenotabile
     */
    public boolean isPrenotabile() {
        return prenotabile;
    }


    /**
     * Sets the prenotabile value for this ArticoloOutputBean.
     * 
     * @param prenotabile
     */
    public void setPrenotabile(boolean prenotabile) {
        this.prenotabile = prenotabile;
    }


    /**
     * Gets the prezzoConsigliato value for this ArticoloOutputBean.
     * 
     * @return prezzoConsigliato
     */
    public double getPrezzoConsigliato() {
        return prezzoConsigliato;
    }


    /**
     * Sets the prezzoConsigliato value for this ArticoloOutputBean.
     * 
     * @param prezzoConsigliato
     */
    public void setPrezzoConsigliato(double prezzoConsigliato) {
        this.prezzoConsigliato = prezzoConsigliato;
    }


    /**
     * Gets the prezzoNettoUnitario value for this ArticoloOutputBean.
     * 
     * @return prezzoNettoUnitario
     */
    public double getPrezzoNettoUnitario() {
        return prezzoNettoUnitario;
    }


    /**
     * Sets the prezzoNettoUnitario value for this ArticoloOutputBean.
     * 
     * @param prezzoNettoUnitario
     */
    public void setPrezzoNettoUnitario(double prezzoNettoUnitario) {
        this.prezzoNettoUnitario = prezzoNettoUnitario;
    }


    /**
     * Gets the quantitaConsegnata value for this ArticoloOutputBean.
     * 
     * @return quantitaConsegnata
     */
    public int getQuantitaConsegnata() {
        return quantitaConsegnata;
    }


    /**
     * Sets the quantitaConsegnata value for this ArticoloOutputBean.
     * 
     * @param quantitaConsegnata
     */
    public void setQuantitaConsegnata(int quantitaConsegnata) {
        this.quantitaConsegnata = quantitaConsegnata;
    }


    /**
     * Gets the quantitaMancante value for this ArticoloOutputBean.
     * 
     * @return quantitaMancante
     */
    public int getQuantitaMancante() {
        return quantitaMancante;
    }


    /**
     * Sets the quantitaMancante value for this ArticoloOutputBean.
     * 
     * @param quantitaMancante
     */
    public void setQuantitaMancante(int quantitaMancante) {
        this.quantitaMancante = quantitaMancante;
    }


    /**
     * Gets the quantitaOmaggio value for this ArticoloOutputBean.
     * 
     * @return quantitaOmaggio
     */
    public int getQuantitaOmaggio() {
        return quantitaOmaggio;
    }


    /**
     * Sets the quantitaOmaggio value for this ArticoloOutputBean.
     * 
     * @param quantitaOmaggio
     */
    public void setQuantitaOmaggio(int quantitaOmaggio) {
        this.quantitaOmaggio = quantitaOmaggio;
    }


    /**
     * Gets the quantitaOmaggioAlternativo value for this ArticoloOutputBean.
     * 
     * @return quantitaOmaggioAlternativo
     */
    public int getQuantitaOmaggioAlternativo() {
        return quantitaOmaggioAlternativo;
    }


    /**
     * Sets the quantitaOmaggioAlternativo value for this ArticoloOutputBean.
     * 
     * @param quantitaOmaggioAlternativo
     */
    public void setQuantitaOmaggioAlternativo(int quantitaOmaggioAlternativo) {
        this.quantitaOmaggioAlternativo = quantitaOmaggioAlternativo;
    }


    /**
     * Gets the quantitaPrenotata value for this ArticoloOutputBean.
     * 
     * @return quantitaPrenotata
     */
    public int getQuantitaPrenotata() {
        return quantitaPrenotata;
    }


    /**
     * Sets the quantitaPrenotata value for this ArticoloOutputBean.
     * 
     * @param quantitaPrenotata
     */
    public void setQuantitaPrenotata(int quantitaPrenotata) {
        this.quantitaPrenotata = quantitaPrenotata;
    }


    /**
     * Gets the quantitaRichiesta value for this ArticoloOutputBean.
     * 
     * @return quantitaRichiesta
     */
    public int getQuantitaRichiesta() {
        return quantitaRichiesta;
    }


    /**
     * Sets the quantitaRichiesta value for this ArticoloOutputBean.
     * 
     * @param quantitaRichiesta
     */
    public void setQuantitaRichiesta(int quantitaRichiesta) {
        this.quantitaRichiesta = quantitaRichiesta;
    }


    /**
     * Gets the rifRiga value for this ArticoloOutputBean.
     * 
     * @return rifRiga
     */
    public java.lang.String getRifRiga() {
        return rifRiga;
    }


    /**
     * Sets the rifRiga value for this ArticoloOutputBean.
     * 
     * @param rifRiga
     */
    public void setRifRiga(java.lang.String rifRiga) {
        this.rifRiga = rifRiga;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArticoloOutputBean)) return false;
        ArticoloOutputBean other = (ArticoloOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arrayConsegna==null && other.getArrayConsegna()==null) || 
             (this.arrayConsegna!=null &&
              java.util.Arrays.equals(this.arrayConsegna, other.getArrayConsegna()))) &&
            ((this.arrayConsegnaDifferita==null && other.getArrayConsegnaDifferita()==null) || 
             (this.arrayConsegnaDifferita!=null &&
              java.util.Arrays.equals(this.arrayConsegnaDifferita, other.getArrayConsegnaDifferita()))) &&
            ((this.arrayFatturazione==null && other.getArrayFatturazione()==null) || 
             (this.arrayFatturazione!=null &&
              java.util.Arrays.equals(this.arrayFatturazione, other.getArrayFatturazione()))) &&
            ((this.codiceMancanza==null && other.getCodiceMancanza()==null) || 
             (this.codiceMancanza!=null &&
              this.codiceMancanza.equals(other.getCodiceMancanza()))) &&
            ((this.codiceOmaggioAlternativo==null && other.getCodiceOmaggioAlternativo()==null) || 
             (this.codiceOmaggioAlternativo!=null &&
              this.codiceOmaggioAlternativo.equals(other.getCodiceOmaggioAlternativo()))) &&
            ((this.codiceProdotto==null && other.getCodiceProdotto()==null) || 
             (this.codiceProdotto!=null &&
              this.codiceProdotto.equals(other.getCodiceProdotto()))) &&
            ((this.codiceProdottoSostituito==null && other.getCodiceProdottoSostituito()==null) || 
             (this.codiceProdottoSostituito!=null &&
              this.codiceProdottoSostituito.equals(other.getCodiceProdottoSostituito()))) &&
            this.consegnaRitardata == other.isConsegnaRitardata() &&
            ((this.dataPrevistaConsegnaPrenotazione==null && other.getDataPrevistaConsegnaPrenotazione()==null) || 
             (this.dataPrevistaConsegnaPrenotazione!=null &&
              this.dataPrevistaConsegnaPrenotazione.equals(other.getDataPrevistaConsegnaPrenotazione()))) &&
            ((this.dataPrevistaDisponibilita==null && other.getDataPrevistaDisponibilita()==null) || 
             (this.dataPrevistaDisponibilita!=null &&
              this.dataPrevistaDisponibilita.equals(other.getDataPrevistaDisponibilita()))) &&
            ((this.descrizioneMancanza==null && other.getDescrizioneMancanza()==null) || 
             (this.descrizioneMancanza!=null &&
              this.descrizioneMancanza.equals(other.getDescrizioneMancanza()))) &&
            ((this.descrizioneOmaggioAlternativo==null && other.getDescrizioneOmaggioAlternativo()==null) || 
             (this.descrizioneOmaggioAlternativo!=null &&
              this.descrizioneOmaggioAlternativo.equals(other.getDescrizioneOmaggioAlternativo()))) &&
            ((this.descrizioneProdotto==null && other.getDescrizioneProdotto()==null) || 
             (this.descrizioneProdotto!=null &&
              this.descrizioneProdotto.equals(other.getDescrizioneProdotto()))) &&
            ((this.descrizioneProdottoSostituito==null && other.getDescrizioneProdottoSostituito()==null) || 
             (this.descrizioneProdottoSostituito!=null &&
              this.descrizioneProdottoSostituito.equals(other.getDescrizioneProdottoSostituito()))) &&
            this.importoNettoRiga == other.getImportoNettoRiga() &&
            ((this.note==null && other.getNote()==null) || 
             (this.note!=null &&
              this.note.equals(other.getNote()))) &&
            this.prenotabile == other.isPrenotabile() &&
            this.prezzoConsigliato == other.getPrezzoConsigliato() &&
            this.prezzoNettoUnitario == other.getPrezzoNettoUnitario() &&
            this.quantitaConsegnata == other.getQuantitaConsegnata() &&
            this.quantitaMancante == other.getQuantitaMancante() &&
            this.quantitaOmaggio == other.getQuantitaOmaggio() &&
            this.quantitaOmaggioAlternativo == other.getQuantitaOmaggioAlternativo() &&
            this.quantitaPrenotata == other.getQuantitaPrenotata() &&
            this.quantitaRichiesta == other.getQuantitaRichiesta() &&
            ((this.rifRiga==null && other.getRifRiga()==null) || 
             (this.rifRiga!=null &&
              this.rifRiga.equals(other.getRifRiga())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getArrayConsegna() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayConsegna());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayConsegna(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getArrayConsegnaDifferita() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayConsegnaDifferita());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayConsegnaDifferita(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getArrayFatturazione() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayFatturazione());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayFatturazione(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodiceMancanza() != null) {
            _hashCode += getCodiceMancanza().hashCode();
        }
        if (getCodiceOmaggioAlternativo() != null) {
            _hashCode += getCodiceOmaggioAlternativo().hashCode();
        }
        if (getCodiceProdotto() != null) {
            _hashCode += getCodiceProdotto().hashCode();
        }
        if (getCodiceProdottoSostituito() != null) {
            _hashCode += getCodiceProdottoSostituito().hashCode();
        }
        _hashCode += (isConsegnaRitardata() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDataPrevistaConsegnaPrenotazione() != null) {
            _hashCode += getDataPrevistaConsegnaPrenotazione().hashCode();
        }
        if (getDataPrevistaDisponibilita() != null) {
            _hashCode += getDataPrevistaDisponibilita().hashCode();
        }
        if (getDescrizioneMancanza() != null) {
            _hashCode += getDescrizioneMancanza().hashCode();
        }
        if (getDescrizioneOmaggioAlternativo() != null) {
            _hashCode += getDescrizioneOmaggioAlternativo().hashCode();
        }
        if (getDescrizioneProdotto() != null) {
            _hashCode += getDescrizioneProdotto().hashCode();
        }
        if (getDescrizioneProdottoSostituito() != null) {
            _hashCode += getDescrizioneProdottoSostituito().hashCode();
        }
        _hashCode += new Double(getImportoNettoRiga()).hashCode();
        if (getNote() != null) {
            _hashCode += getNote().hashCode();
        }
        _hashCode += (isPrenotabile() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Double(getPrezzoConsigliato()).hashCode();
        _hashCode += new Double(getPrezzoNettoUnitario()).hashCode();
        _hashCode += getQuantitaConsegnata();
        _hashCode += getQuantitaMancante();
        _hashCode += getQuantitaOmaggio();
        _hashCode += getQuantitaOmaggioAlternativo();
        _hashCode += getQuantitaPrenotata();
        _hashCode += getQuantitaRichiesta();
        if (getRifRiga() != null) {
            _hashCode += getRifRiga().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArticoloOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ArticoloOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayConsegna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayConsegna"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ConsegnaOutputBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayConsegnaDifferita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayConsegnaDifferita"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ConsegnaDifferitaOutputBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayFatturazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayFatturazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "FatturazioneOutputBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceMancanza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceMancanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceOmaggioAlternativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceOmaggioAlternativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceProdotto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceProdotto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceProdottoSostituito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceProdottoSostituito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consegnaRitardata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "consegnaRitardata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPrevistaConsegnaPrenotazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPrevistaConsegnaPrenotazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPrevistaDisponibilita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPrevistaDisponibilita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneMancanza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneMancanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneOmaggioAlternativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneOmaggioAlternativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneProdotto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneProdotto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneProdottoSostituito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneProdottoSostituito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoNettoRiga");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importoNettoRiga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("note");
        elemField.setXmlName(new javax.xml.namespace.QName("", "note"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prenotabile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prenotabile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prezzoConsigliato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prezzoConsigliato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prezzoNettoUnitario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prezzoNettoUnitario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaConsegnata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaConsegnata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaMancante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaMancante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaOmaggio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaOmaggio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaOmaggioAlternativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaOmaggioAlternativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaPrenotata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaPrenotata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaRichiesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaRichiesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rifRiga");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rifRiga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
