import {ApiService} from './api.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpParams} from '@angular/common/http';

export abstract class ObservableApiService<T> {
  protected _behaviorSubject = new BehaviorSubject<T[]>([]);
  readonly observableData$ = this._behaviorSubject.asObservable();
  protected dataStore: { elements: T[] } = {elements: []};
  protected apiPath: string;

  constructor(apiPath: string, protected apiService: ApiService) {
    this.apiPath = apiPath;
  }

  getAll(pathParams?: string, params: HttpParams = new HttpParams()): void {
    this.apiService.get(this.apiPath + (pathParams ? `/${pathParams}` : ''), params).subscribe(
      data => {
        console.log('DATA RECEIVED FROM GET');
        console.log(data);

        this.dataStore.elements = data;
        this._behaviorSubject.next(Object.assign({}, this.dataStore).elements);
      },
      error => console.log('Could not load data.')
    );
  }

  getByID(id: number, params: HttpParams = new HttpParams()): void {
    this.apiService.get(`${this.apiPath}/${id}`, params).subscribe(
      data => {
        console.log('DATA RECEIVED FROM GET');
        console.log(data);

        let notFound = true;

        this.dataStore.elements.forEach((item: any, index) => {
          if (item.id === data.id) {
            this.dataStore.elements[index] = data;
            notFound = false;
          }
        });

        if (notFound) {
          this.dataStore.elements.push(data);
        }

        this._behaviorSubject.next(Object.assign({}, this.dataStore).elements);
      },
      error => console.log('Could not load data.')
    );
  }

  getByUUID(uuid: string, params: HttpParams = new HttpParams()): void {
    this.apiService.get(`${this.apiPath}/${uuid}`, params).subscribe(
      data => {
        console.log('DATA RECEIVED FROM GET');
        console.log(data);

        let notFound = true;

        this.dataStore.elements.forEach((item: any, index) => {
          if (item.uuid === data.uuid) {
            this.dataStore.elements[index] = data;
            notFound = false;
          }
        });

        if (notFound) {
          this.dataStore.elements.push(data);
        }

        this._behaviorSubject.next(Object.assign({}, this.dataStore).elements);
      },
      error => console.log('Could not load data.')
    );
  }

  getAsObservable = (pathParams?: string, params: HttpParams = new HttpParams()): Observable<T> => this.apiService.get(this.apiPath + (pathParams ? `/${pathParams}` : ''), params);

  getAllAsObservable = (pathParams?: string, params: HttpParams = new HttpParams()): Observable<T[]> => this.apiService.get(this.apiPath + (pathParams ? `/${pathParams}` : ''), params);

  getByUUIDAsObservable = (uuid: string, params: HttpParams = new HttpParams()): Observable<T[]> => this.apiService.get(`${this.apiPath}/${uuid}`, params);

  create(body: any = {}): void {
    this.apiService.post(this.apiPath, body)
      .subscribe(
        data => {
          console.log('DATA RECEIVED FROM POST');
          console.log(data);

          this.dataStore.elements.push(data);
          this._behaviorSubject.next(Object.assign({}, this.dataStore).elements);
        },
        error => console.log('Could not create data.')
      );
  }

  createAndGetObservable = (body: any = {}): Observable<T> => this.apiService.post(this.apiPath, body);

  update(body: any = {}, id?: any): void {
    this.apiService.put(this.apiPath + (id ? `/${id}` : ''), body)
      .subscribe(
        data => {
          console.log('DATA RECEIVED FROM PUT');
          console.log(data);

          this.dataStore.elements.forEach((t: any, i) => {
            if (t.id === data.id) {
              this.dataStore.elements[i] = data;
            }
          });

          this._behaviorSubject.next(Object.assign({}, this.dataStore).elements);
        },
        error => console.log('Could not create data.')
      );
  }

  updateAndGetObservable = (body: any = {}, id?: any): Observable<T> => this.apiService.put(this.apiPath + (id ? `/${id}` : ''), body);

  delete(id: number): void {
    this.apiService.delete(`${this.apiPath}/${id}`).subscribe(
      response => {
        if (response) {
          this.dataStore.elements.forEach((t: any, i) => {
            if (t.id === id) {
              this.dataStore.elements.splice(i, 1);
            }
          });

          this._behaviorSubject.next(Object.assign({}, this.dataStore).elements);
        }
      },
      error => console.log('Could not delete data.')
    );
  }

  deleteByUUID(uuid: string): void {
    this.apiService.delete(`${this.apiPath}/${uuid}`).subscribe(
      response => {
        if (response) {
          this.dataStore.elements.forEach((t: any, i) => {
            if (t.uuid === uuid) {
              this.dataStore.elements.splice(i, 1);
            }
          });

          this._behaviorSubject.next(Object.assign({}, this.dataStore).elements);
        }
      },
      error => console.log('Could not delete data.')
    );
  }
}
