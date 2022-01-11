import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CookieService} from 'ngx-cookie-service';
import {environment} from '../../../environments/environment';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Token
    const organization = this._get('organization');
    const token = this._get('token');
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
        'X-TenantID': `${environment.tenant_name_data}${organization}`
      }
    });

    if (request.url.indexOf('byPeriod') > 0) {
      console.log(request.url);
      console.log(request.headers.get('Authorization'));
    }

    return next.handle(request);
  }

  private _get(name: string): string {
    const getValue = this.cookieService.get(name);
    if (name === 'organization' && getValue === '') {
      return environment.organization_tenant_name;
    } else {
      return getValue;
    }
  }

}
