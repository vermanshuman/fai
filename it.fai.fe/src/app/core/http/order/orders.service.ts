import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ObservableApiService} from '../observable-api.service';
import {ApiService} from '../api.service';
import {GenericTask, Orders} from '../../models';


@Injectable({
    providedIn: 'root'
})
export class OrdersService extends ObservableApiService<Orders> {

    constructor(apiService: ApiService) {
        super('/orders', apiService);
    }
    findAll = ():
        Observable<Orders> => this.apiService.get(`/orders`)

    findOrdersByCollection = (taskOID: number):
        Observable<Orders> => this.apiService.get(`/orders/byTask/` + taskOID)
}

