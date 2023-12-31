INSERT INTO FAI_LISTINI_DISPONIBILITA_TEMP
(
    OID_FORNITORE
   ,CODICE_MINSAN
   ,CODICE_EAN
   ,PREZZO
)
SELECT 
    OID_FORNITORE
   ,CODICE_MINSAN
   ,CODICE_EAN
   ,PREZZO
FROM 
    V_FAI_LISTINI_FORNITORI 
WHERE
    CODICE_MINSAN IN (SELECT CODICE_MINSAN FROM FAI_APPROVVIGIONAMENTO_FARMACO WHERE OID_STATUS = ?OID_STATUS?)
    OR
    CODICE_EAN IN (SELECT CODICE_EAN FROM FAI_APPROVVIGIONAMENTO_FARMACO WHERE OID_STATUS = ?OID_STATUS?)