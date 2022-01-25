package fai.broker.supplier;

import java.sql.Connection;
import java.util.List;

import fai.broker.models.Fornitore;
import fai.broker.models.Magazzino;
import fai.common.models.GenericTaskConfig;
import fai.imp.base.models.FaiImportConfig;

public class SupplierServiceFactory {
  
  
  public static List<SupplierService> getAllSupplierService(boolean warehouses, boolean actualSuppliers) throws Exception {
    throw new NoSuchMethodError("non ancora implementato");
  }

  public static SupplierService getSupplierService(Magazzino magazzino, Connection conn) throws Exception {
    String className = magazzino.getSupplierServiceClassName();
    if (className == null || "".equals(className.trim())) throw new IllegalArgumentException("inammissibile, classe implementante "+SupplierService.class.getName()+" non specificata per il FAI_MAGAZZINO "+magazzino.getAcronym());
    SupplierService ss = (SupplierService)Class.forName(className).newInstance();
    ss.setup(magazzino, conn);
    return ss;
  }

  public static SupplierService getSupplierService(Fornitore fornitore, Connection conn) throws Exception {
    String className = fornitore.getSupplierServiceClassName();
    if (className == null || "".equals(className.trim())) throw new IllegalArgumentException("inammissibile, classe implementante "+SupplierService.class.getName()+" non specificata per il FAI_FORNITORE "+fornitore.getCodice());
    SupplierService ss = (SupplierService)Class.forName(className).newInstance();
    ss.setup(fornitore, conn);
    return ss;
  }

}
