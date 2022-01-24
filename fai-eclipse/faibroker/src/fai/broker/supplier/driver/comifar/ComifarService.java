package fai.broker.supplier.driver.comifar;

import java.util.List;

import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.DisponibilitaTemp;
import fai.broker.models.OrdineOut;
import fai.broker.supplier.AbstractSupplierService;

public class ComifarService extends AbstractSupplierService{

	@Override
	public RequestMode getRequestMode() throws Exception {
		return null;
	}

	@Override
	protected void setupMagazzino() throws Exception {
		
	}

	@Override
	protected void setupFornitore() throws Exception {
		
	}

	@Override
	protected List<DisponibilitaTemp> getAvailabilityNoCache(List<DisponibilitaTemp> fabbisogno) throws Exception {
		return null;
	}

	@Override
	protected OrdineOut confirmExecute(List<ApprovvigionamentoFarmaco> approvvigionamento) throws Exception {
		return null;
	}

}
