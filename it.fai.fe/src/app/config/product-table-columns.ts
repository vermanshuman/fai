import {TableColumn} from '../shared/component/generic-table/generic-table.component';

export const PRODUCT_TABLE_COLUMNS: TableColumn[] = [
    {
        name: 'Id Vendita',
        dataKey: 'idVendita',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Minsan',
        dataKey: 'minsan',
        position: 'center',
        isSortable: true,
    },
    {
        name: 'Descrizione prodotto',
        dataKey: 'email',
        position: 'center',
        isSortable: true,
    },
    {
        name: 'Quantità richiesta',
        dataKey: 'totalQuantity',
        position: 'center',
        isSortable: true,
    },
    {
        name: 'Quantità Magazzino',
        dataKey: 'warehouseQuantity',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Quantità Purchase Order',
        dataKey: 'supplierQuantity',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Quantità Evasa Mag.',
        dataKey: 'amount',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Quantità Evasa PO',
        dataKey: 'amount',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Quantità Evasa mancante',
        dataKey: 'missingQuantity',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Stato',
        dataKey: 'status',
        position: 'center',
        isSortable: true
    }
];
