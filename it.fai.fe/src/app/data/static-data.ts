import {Order} from '../core/models';

export const FILE_UPLOAD_TABLE_DATA: any[] = [
    {
        oid: 1,
        creationDate: new Date(),
        csvFileName: 'File Name 1',
        orderCount: 1,
        orderCount1: 2,
        orderCount2: 3,
        orderStatus: 'Order Status 1',
        orderLinesFulfilled: 'Order Lines Fulfilled 1',
        orderLinesProcessed: 'Order Lines Processed 1',
        orderLinesMissed: 'Order Lines Missed 1'
    },
    {
        oid: 2,
        creationDate: new Date(),
        csvFileName: 'File Name 2',
        orderCount: 3,
        orderCount1: 2,
        orderCount2: 1,
        orderStatus: 'Order Status 2',
        orderLinesFulfilled: 'Order Lines Fulfilled 2',
        orderLinesProcessed: 'Order Lines Processed 2',
        orderLinesMissed: 'Order Lines Missed 2'
    },
    {
        oid: 3,
        creationDate: new Date(),
        csvFileName: 'File Name 3',
        orderCount: 2,
        orderCount1: 1,
        orderCount2: 3,
        orderStatus: null,
        orderLinesFulfilled: 'Order Lines Fulfilled 3',
        orderLinesProcessed: 'Order Lines Processed 3',
        orderLinesMissed: 'Order Lines Missed 3'
    }
];

export const ORDER_TABLE_DATA: Order[] = [
    {
        idVendita: 12345,
        userName: 'test-user',
        email: 'test-email',
        numberOfLines: 1,
        fulfilledCount: 1,
        missingCount: 1,
        amount: 100,
        status: 'ok'
    }
];
