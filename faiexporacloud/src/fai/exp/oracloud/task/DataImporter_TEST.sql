SELECT
    f.CODICE fornitore ,
    listino.CODICE_CLIENTE cliente ,
    listino.CODICE_PRODOTTO prodotto ,
    listino.PREZZO_AL_PUBBLICO ,
    condizioni.DESCR_PRODOTTO
FROM
    FAI_FARMACLICK_FORNITORE f ,
    FAI_RECORD_L_LISTINO listino ,
    FAI_RECORD_C_CONDIZIONI condizioni
WHERE
    f.oid = listino.OID_FORNITORE
    AND listino.CODICE_CLIENTE = condizioni.CODICE_CLIENTE
    AND listino.CODICE_PRODOTTO = condizioni.CODICE_PRODOTTO
ORDER BY
    f.CODICE,
    LISTINO.CODICE_CLIENTE,
    listino.DESCR_PRODOTTO
    