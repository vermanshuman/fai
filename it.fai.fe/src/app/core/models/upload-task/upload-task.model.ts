export interface UploadTask {
    oid: number;
    creationDate: Date;
    csvFileName: string;
    orderCount: number;
    orderStatus: string;
    orderLinesFulfilled: string;
    orderLinesProcessed: string;
    orderLinesMissed: string;
    executionStatus: string;
    magazzinoAcronym: string;
}
