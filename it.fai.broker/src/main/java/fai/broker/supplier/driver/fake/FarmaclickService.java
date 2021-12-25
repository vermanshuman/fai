package fai.broker.supplier.driver.fake;

import java.util.List;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.DisponibilitaReqTemp;
import fai.broker.models.DisponibilitaResTemp;
import fai.broker.models.DisponibilitaTemp;
import fai.broker.models.OrdineOut;
import fai.broker.models.StatusInfo;
import fai.broker.supplier.AbstractSupplierService;
import fai.broker.supplier.SupplierService.RequestMode;

public class FarmaclickService extends AbstractSupplierService {
	@Override
	public List<DisponibilitaTemp> getAvailabilityNoCache(List<DisponibilitaTemp> fabbisogno) throws Exception {
		//
		// A seconda che il "RequestMode" restituito sia "OneProductOneRequest"
		// o "MoreProductOneRequest" la List ricevuta in input sarà caratterizzata
		// da 1 o N elementi (con N pari al numero di farmaci/prodotti per i quali
		// richiedere la disponibilità)
		//
		for (DisponibilitaTemp fabb : fabbisogno) {
			//
			// --- tracciamento nel log della richiesta che sta per essere inoltrata ---
			//
			fai.common.db.SqlQueries.logInfo("inoltro richiesta di disponibilità al " + asShortDescr() + " ...",
					fabb.toXml(), null, conn);
			conn.commit();
			//
			// --- aggiornamento delle FAI_DISPONIBILITA_TEMP/FAI_DISPONIBILITA_REQ_TEMP ---
			//
			fabb.setStatus(StatusInfo.newProcessingInstance("richieta fittizia", fabb.toXml()));
			SqlQueries.storeRequestDisponibilitaTemp(fabb, conn);
			conn.commit();
			
			// need call web service to check quantity at supplier
			DisponibilitaReqTemp[] req = fabb.getAllReq();
			for (int i = 0; i < req.length; i++) {
				double rand = Math.random();
				int quantitaDisponibile = 0;
				if (rand <= 0.60) {
					quantitaDisponibile = req[i].getQuantitaRichiesta();
				} else if (rand <= 0.80) {
					quantitaDisponibile = req[i].getQuantitaRichiesta() / 2;
				} else if (rand <= 1.00) {
					quantitaDisponibile = 0;
				}
				DisponibilitaResTemp res = new DisponibilitaResTemp();
				res.setQuantitaDisponibile(quantitaDisponibile);
				req[i].addResp(res);
			}
			//
			// --- tracciamento nel log della risposta ricevuta ---
			//
			fai.common.db.SqlQueries.logInfo(
					"risposta ricevuta per la richiesta di disponibilità inoltrata " + asShortDescr(), fabb.toXml(),
					null, conn);
			conn.commit();
			//
			// --- aggiornamento delle FAI_DISPONIBILITA_TEMP/FAI_DISPONIBILITA_RES_TEMP ---
			//
			fabb.setStatus(StatusInfo.newProcessedInstance("risposta fittizia", fabb.toXml()));
			SqlQueries.storeResponseDisponibilitaTemp(fabb, conn);
			conn.commit();
			//

		}
		return fabbisogno;
	}

	@Override
	protected void setupMagazzino() throws Exception {
		// nothing to do ... è un fake ...
	}

	@Override
	protected void setupFornitore() throws Exception {
		// nothing to do ... è un fake ...
	}

	public RequestMode getRequestMode() throws Exception {
		//
		// random... così le provo entrambe!
		return Math.random() < 0.5 ? RequestMode.MoreProductOneRequest : RequestMode.OneProductOneRequest;
	}

	@Override
	protected OrdineOut confirmExecute(List<ApprovvigionamentoFarmaco> approvvigionamento) throws Exception {
		throw new NoSuchMethodError("non ancora implementato");
	}

}
