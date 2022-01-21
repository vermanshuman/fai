import {TableColumn} from '../shared/component/generic-table/generic-table.component';

export const FILE_UPLOAD_TABLE_COLUMNS: TableColumn[] = [
    {
        name: 'Data',
        dataKey: 'creationDate',
        position: 'center',
        isDate: true,
        isSortable: true,
    },
    {
        name: 'File Name',
        dataKey: 'csvFileName',
        position: 'center',
        isSortable: true,
    },
    {
        name: '# Ordini',
        dataKey: 'orderCount',
        position: 'center',
        isSortable: true,
        'showOutOf': ['orderCount1', 'orderCount2']
    },
    {
        name: 'Stato Ordini',
        dataKey: 'orderStatus',
        position: 'center',
        isSortable: true,
    },
    {
        name: '% Linee Ordini evase',
        dataKey: 'orderLinesFulfilled',
        position: 'center',
        isSortable: true
    },
    {
        name: '% Linee Ordini processate',
        dataKey: 'orderLinesProcessed',
        position: 'center',
        isSortable: true
    },
    {
        name: '% Linee Ordini mancanti',
        dataKey: 'orderLinesMissed',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Magazzino',
        dataKey: 'magazzino',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Stato esecuzione',
        dataKey: 'statoEsecuzione',
        position: 'center',
        isSortable: true
    }
]
