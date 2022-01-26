import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ObservableApiService} from '../observable-api.service';
import {ApiService} from '../api.service';
import {Product, Products} from '../../models';


@Injectable({
    providedIn: 'root'
})
export class ProductService extends ObservableApiService<Product> {

    constructor(apiService: ApiService) {
        super('/orders', apiService);
    }

    findAll = ():
        Observable<Products> => this.apiService.get(`/products`);

    findProductsByOrder = (orderId: number):
        Observable<Products> => this.apiService.get(`/products/byOrder/` + orderId);
}

