-- GRANT EXECUTE on UTL_FILE to FAI;
--SQL>  create or replace directory FAICSV as 'G:\faicsv\';
--SQL> grant read, write on directory FAICSV to FAI;


DECLARE
   F UTL_FILE.FILE_TYPE;
    V_LINE VARCHAR2 (1000);
    dataOrd VARCHAR2 (10);
    numOrd VARCHAR2(30);
    minsan VARCHAR2(9);
    qta VARCHAR2(9);
   colloid NUMBER(38,0);
  ordoid NUMBER(38,0);
 ordrigaoid NUMBER(38,0);
  numOrdpreval VARCHAR2(30);
 numOrdcurval VARCHAR2(30);
totalquantity NUMBER(38,0);
coupon VARCHAR(1);
couponTitle VARCHAR(6);
     
  BEGIN
	  colloid := OID.NEXTVAL;
    F := UTL_FILE.FOPEN ('FAICSV', 'ordWebSD.csv', 'R');
   IF UTL_FILE.IS_OPEN(F) THEN
   totalquantity := 0;
   --DELETE FROM FAI_ORDINE_IN_RIGA_DETTAGLIO WHERE PREZZO_FINALE =0;
  -- DELETE FROM FAI_ORDINE_IN WHERE ID_UTENTE = 0;
  -- DELETE FROM FAI_ORDINE_IN_COLLECTION WHERE INPUT_RESOURCE ='FAKE.CSV';
  INSERT INTO FAI_ORDINE_IN_COLLECTION 
   (OID, INPUT_RESOURCE, CREATION_TS, OID_STATUS,MAGAZZINO_ACRONYM,BATCH_ID,UNIQUE_ID)
   		VALUES(colloid, 'FAKE.CSV', CURRENT_TIMESTAMP, 3,'UPS',-1,SYS_GUID);
      LOOP
        BEGIN
          UTL_FILE.GET_LINE(F, V_LINE, 1000);
          IF V_LINE IS NULL THEN
            EXIT;
          END IF;
          dataOrd := REGEXP_SUBSTR(V_LINE, '[^;]+', 1, 1);
          numOrd := REGEXP_SUBSTR(V_LINE, '[^;]+', 1, 2);
          minsan := REGEXP_SUBSTR(V_LINE, '[^;]+', 1, 3);
          qta := REGEXP_SUBSTR(V_LINE, '[^;]+', 1, 4);
         
         IF qta != 'qta' AND (numOrdpreval IS NULL OR numOrdpreval !=  numOrd) THEN
       
         UPDATE FAI_ORDINE_IN SET QUANTITA = totalquantity WHERE OID=ordoid;
             numOrdpreval := NULL;
	         numOrdcurval := NULL;
	        ordoid :=  NULL;
	       totalquantity := TO_NUMBER(qta);
	      ELSIF qta != 'qta' THEN
	      	totalquantity := totalquantity + TO_NUMBER(qta);
           END IF;
    	  IF qta != 'qta' AND numOrdcurval IS NULL THEN
		    
	      	numOrdcurval := numOrd;
		  	ordoid := OID.NEXTVAL;
		    INSERT INTO FAI_ORDINE_IN 
	        (OID, ID_VENDITA, ID_UTENTE, QUANTITA, DATA_DI_VENDITA, CREATION_TS, OID_STATUS, 
	        OID_ORDINEINCOLLECTION) VALUES(ordoid,TO_NUMBER(numOrd), 0, TO_NUMBER(qta),CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, colloid);
	       
	       ELSIF qta != 'qta' THEN
	       ordrigaoid := OID.NEXTVAL;
	      	IF (minsan IS NOT NULL AND substr(minsan,1,1) = 'P') THEN 
		      coupon := 'S';
		     couponTitle := 'Coupon';
		    ELSE 
		       coupon := 'N';
		       couponTitle := NULL;
		     END IF;
	       INSERT INTO FAI.FAI_ORDINE_IN_RIGA_DETTAGLIO
(OID, NUMERO_OGGETTO, TITOLO_INSERZIONE, ETICHETTA_PERSONALIZZATA, QUANTITA, PREZZO_FINALE, OID_ORDINEIN, CREATION_TS, CODICE_MINSAN, IS_COUPON, MAGAZZINO_ACRONYM,ALIQUOTA_IVA_INCLUSA)
VALUES(ordrigaoid, minsan, couponTitle,  minsan,  TO_NUMBER(qta), 0, ordoid,SYSDATE  , minsan, coupon, 'UPS',0);

		  END IF;
          COMMIT;
         
          IF qta != 'qta' AND numOrdpreval IS NULL THEN
	          numOrdpreval := numOrdcurval;
           END IF;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
          EXIT;
        END;
      END LOOP;
    END IF;
    UTL_FILE.FCLOSE_ALL;
  END;
  