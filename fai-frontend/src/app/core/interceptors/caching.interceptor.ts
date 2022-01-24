import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {CacheMapService} from '../services/cache-map.service';
import {tap} from 'rxjs/operators';

const CACHABLE_URL = '/administration/api';

@Injectable()
export class CachingInterceptor implements HttpInterceptor {
  constructor(private cache: CacheMapService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isRequestCachable(req)) {
      return next.handle(req);
    }
    const cachedResponse = this.cache.get(req);
    if (cachedResponse !== null) {
      return of(cachedResponse);
    }
    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          this.cache.put(req, event);
        }
      })
    );
  }

  private isRequestCachable(req: HttpRequest<any>): boolean {
    return (req.method === 'GET') && (req.url.indexOf(CACHABLE_URL) > -1);
  }
}
