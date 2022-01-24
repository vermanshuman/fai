import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ObservableApiService} from '../observable-api.service';
import {ApiService} from '../api.service';
import {GenericTask, Orders, Warehouse} from '../../models';


@Injectable({
    providedIn: 'root'
})
export class WarehouseService extends ObservableApiService<Warehouse> {

    constructor(apiService: ApiService) {
        super('/warehouse', apiService);
    }
    findAll = ():
        Observable<Warehouse[]> => this.apiService.get(`/warehouse`)
}

