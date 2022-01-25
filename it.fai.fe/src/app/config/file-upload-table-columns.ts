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
        clickable: true
    },
    {
        name: '# Ordini',
        dataKey: 'orderCount',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Stato Ordini',
        dataKey: 'fulFilledOrderCount',
        position: 'center',
        isSortable: true,
        color: '#00B050',
        'showOutOf': [{value: 'processedOrderCount', color: '#FFC000'}, {value: 'missingOrderCount', color: '#FF0000'}]
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
        dataKey: 'magazzinoAcronym',
        position: 'center',
        isSortable: true
    },
    {
        name: 'Stato esecuzione',
        dataKey: 'executionStatus',
        position: 'center',
        isSortable: true
    }
]
