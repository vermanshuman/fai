package fai.broker.models;

import it.swdes.models.AbstractModel;
import it.swdes.models.Attribute;

import java.util.ArrayList;
import java.util.List;

public class ApprovvigionamentoFarmaco extends AbstractModel {


    public static int OID;
    public static int MAGAZZINO;
    public static int FORNITORE;
    public static int CODICE_MIN_SAN;
    public static int CODICE_EAN;
    public static int QUANTITA;
    public static int DISPONIBILITA_RICHIESTA_REQ;
    public static int DISPONIBILITA_RICHIESTA_RES;
    public static int DISPONIBILITA_CONFERMATA_REQ;
    public static int DISPONIBILITA_CONFERMATA_RES;
    public static int ID_ORDINE;
    public static int ID_RICEVUTA;
    public static int ORDINE_OUT;
    public static int PREZZO_UNITARIO;
    public static int PREZZO_TOTALE;
    public static int ALIQUOTA_IVA;
    public static int ALIQUOTA_IVA_INCLUSA;
    public static int STATUS;
    public static int MAGAZZINO_ACRONYM;
    public static int APPROV_RIGA_DETT;
    public static int ID_VENDITA;
    public static int TOTALE_QUANTITA;

    public static final Attribute[] attributes = buildAttributes(ApprovvigionamentoFarmaco.class, new Attribute[]{ //@formatter:off
            new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("magazzino", null, Magazzino.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
            new Attribute("fornitore", null, Fornitore.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
            new Attribute("codiceMinSan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("codiceEan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("quantita", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("disponibilitaRichiestaReq", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("disponibilitaRichiestaRes", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("disponibilitaConfermataReq", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("disponibilitaConfermataRes", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("idOrdine", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("idRicevuta", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("ordineOut", null, OrdineOut.class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
            new Attribute("prezzoUnitario", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("prezzoTotale", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("aliquotaIva", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("aliquotaIvaInclusa", null, Boolean.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("status", null, StatusInfo.class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
            new Attribute("magazzinoAcronym", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("approvRigaDett", null, ApprovToRiga[].class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
            new Attribute("idVendita", null, Long.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("totaleQuantita", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    }); //@formatter:on


    /**
     * CONSTRUCTOR
     **/
    public ApprovvigionamentoFarmaco() {
    }


    /**
     * OID
     **/
    public boolean setOid(Long oid) {
        return setLong(OID, oid);
    }

    public Long getOid() {
        return getLong(OID);
    }


    /**
     * MAGAZZINO
     **/
    public boolean setMagazzino(Magazzino magazzino) {
        return setObject(MAGAZZINO, magazzino);
    }

    public Magazzino getMagazzino() {
        return (Magazzino) getObject(MAGAZZINO);
    }


    /**
     * FORNITORE
     **/
    public boolean setFornitore(Fornitore fornitore) {
        return setObject(FORNITORE, fornitore);
    }

    public Fornitore getFornitore() {
        return (Fornitore) getObject(FORNITORE);
    }


    /**
     * CODICE_MIN_SAN
     **/
    public boolean setCodiceMinSan(String codiceMinSan) {
        return setString(CODICE_MIN_SAN, codiceMinSan);
    }

    public String getCodiceMinSan() {
        return getString(CODICE_MIN_SAN);
    }


    /**
     * CODICE_EAN
     **/
    public boolean setCodiceEan(String codiceEan) {
        return setString(CODICE_EAN, codiceEan);
    }

    public String getCodiceEan() {
        return getString(CODICE_EAN);
    }


    /**
     * QUANTITA
     **/
    public boolean setQuantita(Integer quantita) {
        return setInt(QUANTITA, quantita);
    }

    public Integer getQuantita() {
        return getInt(QUANTITA);
    }


    /**
     * DISPONIBILITA_RICHIESTA_REQ
     **/
    public boolean setDisponibilitaRichiestaReq(java.util.Calendar disponibilitaRichiestaReq) {
        return setCalendar(DISPONIBILITA_RICHIESTA_REQ, disponibilitaRichiestaReq);
    }

    public java.util.Calendar getDisponibilitaRichiestaReq() {
        return getCalendar(DISPONIBILITA_RICHIESTA_REQ);
    }


    /**
     * DISPONIBILITA_RICHIESTA_RES
     **/
    public boolean setDisponibilitaRichiestaRes(java.util.Calendar disponibilitaRichiestaRes) {
        return setCalendar(DISPONIBILITA_RICHIESTA_RES, disponibilitaRichiestaRes);
    }

    public java.util.Calendar getDisponibilitaRichiestaRes() {
        return getCalendar(DISPONIBILITA_RICHIESTA_RES);
    }


    /**
     * DISPONIBILITA_CONFERMATA_REQ
     **/
    public boolean setDisponibilitaConfermataReq(java.util.Calendar disponibilitaConfermataReq) {
        return setCalendar(DISPONIBILITA_CONFERMATA_REQ, disponibilitaConfermataReq);
    }

    public java.util.Calendar getDisponibilitaConfermataReq() {
        return getCalendar(DISPONIBILITA_CONFERMATA_REQ);
    }


    /**
     * DISPONIBILITA_CONFERMATA_RES
     **/
    public boolean setDisponibilitaConfermataRes(java.util.Calendar disponibilitaConfermataRes) {
        return setCalendar(DISPONIBILITA_CONFERMATA_RES, disponibilitaConfermataRes);
    }

    public java.util.Calendar getDisponibilitaConfermataRes() {
        return getCalendar(DISPONIBILITA_CONFERMATA_RES);
    }


    /**
     * ID_ORDINE
     **/
    public boolean setIdOrdine(String idOrdine) {
        return setString(ID_ORDINE, idOrdine);
    }

    public String getIdOrdine() {
        return getString(ID_ORDINE);
    }


    /**
     * ID_RICEVUTA
     **/
    public boolean setIdRicevuta(String idRicevuta) {
        return setString(ID_RICEVUTA, idRicevuta);
    }

    public String getIdRicevuta() {
        return getString(ID_RICEVUTA);
    }


    /**
     * ORDINE_OUT
     **/
    public boolean setOrdineOut(OrdineOut ordineOut) {
        return setObject(ORDINE_OUT, ordineOut);
    }

    public OrdineOut getOrdineOut() {
        return (OrdineOut) getObject(ORDINE_OUT);
    }


    /**
     * PREZZO_UNITARIO
     **/
    public boolean setPrezzoUnitario(Double prezzoUnitario) {
        return setDouble(PREZZO_UNITARIO, prezzoUnitario);
    }

    public Double getPrezzoUnitario() {
        return getDouble(PREZZO_UNITARIO);
    }


    /**
     * PREZZO_TOTALE
     **/
    public boolean setPrezzoTotale(Double prezzoTotale) {
        return setDouble(PREZZO_TOTALE, prezzoTotale);
    }

    public Double getPrezzoTotale() {
        return getDouble(PREZZO_TOTALE);
    }


    /**
     * ALIQUOTA_IVA
     **/
    public boolean setAliquotaIva(Double aliquotaIva) {
        return setDouble(ALIQUOTA_IVA, aliquotaIva);
    }

    public Double getAliquotaIva() {
        return getDouble(ALIQUOTA_IVA);
    }


    /**
     * ALIQUOTA_IVA_INCLUSA
     **/
    public boolean setAliquotaIvaInclusa(Boolean aliquotaIvaInclusa) {
        return setBoolean(ALIQUOTA_IVA_INCLUSA, aliquotaIvaInclusa);
    }

    public Boolean getAliquotaIvaInclusa() {
        return getBoolean(ALIQUOTA_IVA_INCLUSA);
    }

    public boolean isAliquotaIvaInclusa() {
        return isBoolean(ALIQUOTA_IVA_INCLUSA);
    }


    /**
     * STATUS
     **/
    public boolean setStatus(StatusInfo status) {
        return setObject(STATUS, status);
    }

    public StatusInfo getStatus() {
        return (StatusInfo) getObject(STATUS);
    }


    /**
     * MAGAZZINO_ACRONYM
     **/
    public boolean setMagazzinoAcronym(String magazzinoAcronym) {
        return setString(MAGAZZINO_ACRONYM, magazzinoAcronym);
    }

    public String getMagazzinoAcronym() {
        return getString(MAGAZZINO_ACRONYM);
    }


    /**
     * APPROV_RIGA_DETT
     **/
    public boolean setApprovRigaDett(AbstractModel approvRigaDett) {
        return setObject(APPROV_RIGA_DETT, approvRigaDett);
    }

    public AbstractModel getApprovRigaDett() {
        return getObject(APPROV_RIGA_DETT);
    }

    public void setAllApprovRigaDett(ApprovToRiga[] approvRigaDett) {
        setArray(APPROV_RIGA_DETT, approvRigaDett);
    }

    public ApprovToRiga[] getAllApprovRigaDett() {
        return (ApprovToRiga[]) getArray(APPROV_RIGA_DETT);
    }

    public void addApprovRigaDett(ApprovToRiga approvRigaDett) {
        addArrayElement(APPROV_RIGA_DETT, approvRigaDett);
    }

    public void addApprovRigaDett(int index, ApprovToRiga approvRigaDett) {
        addArrayElement(APPROV_RIGA_DETT, index, approvRigaDett);
    }

    public boolean removeApprovRigaDett(ApprovToRiga approvRigaDett) {
        return removeArrayElement(APPROV_RIGA_DETT, approvRigaDett);
    }

    public ApprovToRiga removeApprovRigaDett(int index) {
        return (ApprovToRiga) removeArrayElement(APPROV_RIGA_DETT, index);
    }

    public ApprovToRiga getApprovRigaDettAt(int index) {
        return (ApprovToRiga) getArrayElement(APPROV_RIGA_DETT, index);
    }

    public void replaceApprovRigaDettAt(int index, ApprovToRiga approvRigaDett) {
        copyAt(APPROV_RIGA_DETT, approvRigaDett, index);
    }

    public int getApprovRigaDettCount() {
        return getArrayCount(APPROV_RIGA_DETT);
    }

    //@formatter:on


    public int getQuantitaByOrdineInStatus(ItemStatus status) {
        int sum = 0;
        List<ApprovToRiga> a2rList = getAllApprToRigaByOrdineInStatus(status);
        for (ApprovToRiga a2r : a2rList) {
            Integer quantita = a2r.getQuantita();
            if (quantita == null)
                throw new IllegalStateException("inammissibile, quantita null per l'" + ApprovToRiga.class.getName() + " di OID " + a2r.getOid());
            sum += quantita.intValue();
        }
        return sum;
    }


    public List<ApprovToRiga> getAllApprToRigaByOrdineInStatus(ItemStatus status) {
        if (status == null) throw new IllegalArgumentException("parametro null non ammesso");
        List<ApprovToRiga> a2rList = new ArrayList<ApprovToRiga>();
        //
        for (int i = 0; i < getApprovRigaDettCount(); i++) {
            if (getApprovRigaDettAt(i).getRigaDett() == null || getApprovRigaDettAt(i).getRigaDett().getOrdineIn() == null || getApprovRigaDettAt(i).getRigaDett().getOrdineIn().getStatus() == null || getApprovRigaDettAt(i).getRigaDett().getOrdineIn().getStatus().getStatus() == null || getApprovRigaDettAt(i).getRigaDett().getOrdineIn().getStatus().getStatus().getOid() == null) {
                throw new IllegalStateException("inammissibile, per poter invocare questo metodo, deve essere caricata l'intero ramo di oggetti fino alla foglia dello " + ItemStatus.class.getName() + " dell'" + OrdineIn.class.getName());
            }
            if (getApprovRigaDettAt(i).getRigaDett().getOrdineIn().getStatus().getStatus().getOid().equals(status.getOid())) {
                a2rList.add(getApprovRigaDettAt(i));
            }
        }
        return a2rList;
    }


    public String getCodiceDescr() {
        return "MinSan " + (getCodiceMinSan() == null || "".equals(getCodiceMinSan()) ? "?" : getCodiceMinSan()) + " / " + "EAN " + (getCodiceEan() == null || "".equals(getCodiceEan()) ? "?" : getCodiceEan());


    }

    /**
     * ID_VENDITA
     **/
    public boolean setIdVendita(Long idVendita) {
        return setLong(ID_VENDITA, idVendita);
    }

    public Long getIdVendita() {
        return getLong(ID_VENDITA);
    }



    /**
     * TOTALE_QUANTITA
     **/
    public boolean setTotaleQuantita(Integer totalQuantity) {
        return setInt(TOTALE_QUANTITA, totalQuantity);
    }

    public Integer getTotaleQuantita() {
        return getInt(TOTALE_QUANTITA);
    }



    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


    //@formatter:on


}
