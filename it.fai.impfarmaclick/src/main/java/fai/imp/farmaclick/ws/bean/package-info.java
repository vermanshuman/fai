/**
 *  Ha lo scopo di uniformare i bean restituiti dalle varie versioni delle 
 *  API.<br/>
 *  <br/>
 *  Ad esempio, il servizio {@code FCKDisponibilita} � implementato solo 
 *  dall'API 2005.001, per cui il bean restituito in risposta dal metodo
 *  {@link fai.imp.farmaclick.ws.FarmaclickWS#callDisponibilita(fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean, boolean, boolean, boolean, java.util.List)},
 *  pu� tranquillamente coincidere con quella della specifica API, ossia {@link fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.DettaglioArticoliOutputBean}.<br>
 *  <br/>
 *  In altri casi, tuttavia, lo stesso servizio, ad esempio {@code FCKDownloadListino}, 
 *  � implementato da pi� API, con il bean di ritorno uguale (ma appartenente a package diversi)
 *  o con poche differenze tra un API e l'altra.<br/>
 *  In questi casi, allo scopo di astrarre dalla specifica API, � stato 
 *  concepito il presente package destinato ad ospitare, per ogni bean
 *  presente in pi� API, una versione del medesimo bean indipendente dalla 
 *  specifica API e compatibile con tutte.
 */
package fai.imp.farmaclick.ws.bean;