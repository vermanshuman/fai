SELECT OID, OID_FORNITORE, DATE_OF_WEEK, HOUR_START, HOUR_END 
FROM FAI_FORNITORE_CALENDAR
WHERE DATE_OF_WEEK = ?DATE_OF_WEEK?
