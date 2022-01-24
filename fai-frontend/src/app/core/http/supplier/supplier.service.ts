import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ObservableApiService} from '../observable-api.service';
import {ApiService} from '../api.service';
import {GenericTask, Orders, Supplier, Warehouse} from '../../models';


@Injectable({
    providedIn: 'root'
})
export class SupplierService extends ObservableApiService<Supplier> {

    constructor(apiService: ApiService) {
        super('/supplier', apiService);
    }
    findAll = ():
        Observable<Warehouse[]> => this.apiService.get(`/supplier`)
}

