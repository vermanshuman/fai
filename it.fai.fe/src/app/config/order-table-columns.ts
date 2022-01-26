import {TableColumn} from '../shared/component/generic-table/generic-table.component';

export const ORDER_TABLE_COLUMNS: TableColumn[] = [
    {
        name: 'Id Vendita',
        dataKey: 'idVendita',
        position: 'center',
        isSortable: true,
        clickable: true
    },
    {
        name: 'Utente Web',
        dataKey: 'userName',
        position: 'center',
        isSortable: true,
    },
    {
        name: 'email',
        dataKey: 'email',
        position: 'center',
        isSortable: true,
    },
    {
        name: '# Linee',
        dataKey: 'numberOfLines',
        position: 'center',
        isSortable: true,
    },
    {
        name: 'Evase',
        dataKey: 'orderLinesFulfilled',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Mancanti',
        dataKey: 'missingCount',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Importo',
        dataKey: 'amount',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Stato',
        dataKey: 'status',
        position: 'center',
        isSortable: true,
        statusIcons: [{icon: 'check', color: '#01B050', key: 'status', value: 'status3'},
            {key: 'status', value: 'status2', className: 'sand-clock'},
            {icon: 'check', color: '#FABE06', key: 'status', value: 'status3'}]
    }
];
