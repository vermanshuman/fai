import {Injectable, Injector} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {TranslateService} from '@ngx-translate/core';

@Injectable()
export class SnackbarInterceptor implements HttpInterceptor {

  constructor(private snackBar: MatSnackBar, private readonly injector: Injector) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    return next.handle(request).pipe(
      tap(e => {
        if (request.url.indexOf('/checkCF') === -1 && (request.method === 'POST' || request.method === 'PUT')) {
          if (e instanceof HttpResponse && e.status === 200) {
            this.snackBar.open(
              this.injector.get(TranslateService).instant('SNACKBAR.SUCCESS'),
              this.injector.get(TranslateService).instant('SNACKBAR.ACTION'),
              {duration: 2000, panelClass: 'successSnack', verticalPosition: 'top'});
          }
        }
      }),
      catchError(error => {
        this.snackBar.open(
          this.injector.get(TranslateService).instant('SNACKBAR.ERROR'),
          this.injector.get(TranslateService).instant('SNACKBAR.ACTION'),
          {duration: 2000, panelClass: 'errorSnack', verticalPosition: 'top'});
        return throwError(error);
      })
    );
  }
}
