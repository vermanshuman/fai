UPDATE ?TABLE_NAME? SET AZIONE = ?ACTION? WHERE OID_FORNITORE IN (SELECT OID FROM FAI_FARMACLICK_FORNITORE WHERE NO_LONGER_EXISTS_TS IS NOT NULL)