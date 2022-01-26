MERGE INTO FAI_FORNITORE_CALENDAR fc
    USING
        (
            SELECT
                ?OID_FORNITORE?  OID_FORNITORE
  ,?DATE_OF_WEEK?  DATE_OF_WEEK
  ,?HOUR_START?  HOUR_START
  ,?HOUR_END?  HOUR_END
            FROM DUAL
        ) src
    ON (fc.OID_FORNITORE=src.OID_FORNITORE AND fc.DATE_OF_WEEK=src.DATE_OF_WEEK)
    WHEN MATCHED
        THEN UPDATE SET
        fc.HOUR_START = src.HOUR_START
        ,tgt.HOUR_END  = src.HOUR_END
    WHEN NOT MATCHED
        THEN INSERT
        (
         OID
            ,OID_FORNITORE
            ,DATE_OF_WEEK
            ,HOUR_START
            ,HOUR_END
            )
        VALUES
            (
                OID.NEXTVAL
            ,src.OID_FORNITORE
            ,src.DATE_OF_WEEK
            ,src.HOUR_START
            ,src.HOUR_END
            )
