import {Injectable} from '@angular/core';
import {catchError, shareReplay} from 'rxjs/operators';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(
    private http: HttpClient
  ) {
  }

  private formatErrors(error: any): Observable<never> {
    return throwError(error.error);
  }

  get(path: string, params: HttpParams = new HttpParams(), responseType?: any): Observable<any> {
    return this.http.get(`${environment.api_url}${path}`, {params, responseType})
      .pipe(
        shareReplay(),
        // tap(r => console.log(r)),
        catchError(this.formatErrors));
  }

  post(path: string, body: object = {}, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.post(`${environment.api_url}${path}`,
      JSON.stringify(body), {headers: {'Content-Type': 'application/json'}, params}
    ).pipe(
      shareReplay(),
      catchError(this.formatErrors));
  }

  put(path: string, body: object = {}, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.put(
      `${environment.api_url}${path}`,
      JSON.stringify(body), {headers: {'Content-Type': 'application/json'}, params}
    ).pipe(
      shareReplay(),
      catchError(this.formatErrors));
  }

  delete(path: string): Observable<any> {
    return this.http.delete(`${environment.api_url}${path}`)
      .pipe(
        shareReplay(),
        catchError(this.formatErrors));
  }

  download(path: string): Observable<any> {
    return this.http.get(
      `${environment.api_url}${path}`, {responseType: 'blob'}
    ).pipe(
      shareReplay(),
      catchError(this.formatErrors));
  }
}
