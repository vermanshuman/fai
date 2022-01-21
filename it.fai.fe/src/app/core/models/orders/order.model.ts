export interface Order {
    idVendita: number;
    userName: string;
    email: string;
    numberOfLines: number;
    fulfilledCount: number;
    missingCount: number;
    amount: number;
    status: string;
}
