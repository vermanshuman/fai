SELECT
     ITEM_NUM -- Item Number, colonna [A],  numero articolo secondo Farmadati 
    ,DESCR -- Description, colonna [G],  descrizione
    ,PRIMARY_UOM -- Primary Unit of Measure, colonna [M], unità di misura, (se non dovesse essere disponibile, assegnare "Unit")
    ,PACKAGE_TYPE -- Pack Type, colonna [EY], è la confezione (se non dovesse essere disponibile, assegnare "CASE")
    ,LONG_DESCR -- Long Description, colonna [FD],  descrizione lunga in plain-text: assegnare descrizione + principio attivo
    ,FORMATTED_DESCR -- Formatted Description, colonna [FE] come "Long Description", ma supporta HTML ed XML: assegnare descrizione + principio attivo (in plain text)
    ,INPUT_TAX_CLASS_CODE-- Input Tax Classification Code,  colonna [HR], aliquota IVA
    ,LIST_PRICE -- List Price, colonna [IA],  prezzo
    ,ATTR_CAT-- ATTRIBUTE_CATEGORY, colonna [KX], al momento (2021.05.03) deve essere proprio "Codici Prodotto", intesa come costante
    ,ATTR1 -- ATTRIBUTE1, colonna [KY], codice AIC
    ,ATTR2 -- ATTRIBUTE2, colonna [KZ], codice EAN
    ,ATTR3 -- ATTRIBUTE3, colonna [KA], codice ATC
FROM 
(
    SELECT 
         FDI_0001 AS ITEM_NUM
        ,FDI_0004 AS DESCR
        ,'Unit' AS PRIMARY_UOM
        ,'CASE' AS PACKAGE_TYPE
        ,FDI_0004 AS LONG_DESCR
        ,FDI_0004 AS FORMATTED_DESCR
        ,FDI_0248 AS INPUT_TAX_CLASS_CODE
        ,FDI_9007 AS LIST_PRICE
        ,'Codici Prodotto' AS ATTR_CAT
        ,FDI_0329 ATTR1
        ,FDI_0002 AS ATTR2
        ,NULL ATTR3
    FROM 
        TE001

)

                            
                            
                    
                    
