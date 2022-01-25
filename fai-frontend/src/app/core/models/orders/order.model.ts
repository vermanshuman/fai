export interface Order {
    oid: number;
    idVendita: number;
    userName: string;
    email: string;
    numberOfLines: number;
    fulfilledCount: number;
    missingCount: number;
    amount: number;
    status: string;
}
