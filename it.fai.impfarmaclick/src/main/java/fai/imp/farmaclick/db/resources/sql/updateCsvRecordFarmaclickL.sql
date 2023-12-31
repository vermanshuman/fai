UPDATE 
FAI_RECORD_L_LISTINO
SET
   ALIQUOTA_IVA              = ?ALIQUOTA_IVA?
  ,ALLINEAM_SCADENZA         = ?ALLINEAM_SCADENZA?
  ,AZIONE                    = ?AZIONE?
  ,CATEGORIA_TIPO_PRODOTTO   = ?CATEGORIA_TIPO_PRODOTTO?
  ,CODICE_ADDEBITO           = ?CODICE_ADDEBITO?
  ,CODICE_EAN                = ?CODICE_EAN?
  ,CODICE_MINSAN             = ?CODICE_MINSAN?
  ,DESCR_PRODOTTO            = ?DESCR_PRODOTTO?
  ,DILAZ_PAGAM_GIORNI        = ?DILAZ_PAGAM_GIORNI?
  ,GIORNI_ABBU_CALC_ADD      = ?GIORNI_ABBU_CALC_ADD?
  ,PERC_ADDEBITO_AGG         = ?PERC_ADDEBITO_AGG?
  ,PERC_SC_PRZPUBDR_PRZNETUN = ?PERC_SC_PRZPUBDR_PRZNETUN?
  ,PERC_SC_PRZPUB_PRZNETUN   = ?PERC_SC_PRZPUB_PRZNETUN?
  ,PERIODICITA_FATTURAZ      = ?PERIODICITA_FATTURAZ?
  ,PREZZO_AL_PUBBLICO        = ?PREZZO_AL_PUBBLICO?
  ,PREZZO_NETTO_UNITARIO     = ?PREZZO_NETTO_UNITARIO?
  ,PREZZO_PUBB_DERIVATO      = ?PREZZO_PUBB_DERIVATO?
  ,PREZZO_VEND_CONSIG        = ?PREZZO_VEND_CONSIG?
  ,PREZ_VEND_LIST_LORDO_SC   = ?PREZ_VEND_LIST_LORDO_SC?
  ,QUANT_PER_COLLO_VEND      = ?QUANT_PER_COLLO_VEND?
  ,SCONTO_1                  = ?SCONTO_1?
  ,SCONTO_2                  = ?SCONTO_2?
  ,SCONTO_CASSA              = ?SCONTO_CASSA?
  ,SOMMA_SCONTO_1_2          = ?SOMMA_SCONTO_1_2?
  ,UPDATE_TS                 = SYSDATE
WHERE
OID_FORNITORE=?OID_FORNITORE? AND CODICE_CLIENTE=?CODICE_CLIENTE? AND CODICE_PRODOTTO=?CODICE_PRODOTTO?
