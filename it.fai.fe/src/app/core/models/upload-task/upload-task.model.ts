export interface UploadTask {
    oid: number;
    creationDate: Date;
    csvFileName: string;
    orderCount: number;
    fulFilledOrderCount: number;
    processedOrderCount: number;
    missingOrderCount: number;
    orderStatus: string;
    orderLinesFulfilled: string;
    orderLinesProcessed: string;
    orderLinesMissed: string;
    executionStatus: string;
    executionStatusDescription: string;
    magazzinoAcronym: string;
}
