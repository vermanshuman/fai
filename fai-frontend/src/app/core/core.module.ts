import {NgModule, Optional, SkipSelf} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EnsureModuleLoadedOnceGuard} from './ensureModuleLoadedOnceGuard';
import {HTTP_INTERCEPTORS, HttpClient} from '@angular/common/http';
import {HttpErrorInterceptor} from './interceptors/http-error.interceptor';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {MatPaginatorIntl} from '@angular/material/paginator';
import {CustomMatPaginatorIntl} from '../custom-paginator-intl';
import {MultiTranslateHttpLoader} from './services/multi-translate-loader';
import {GenericTaskService} from './http';

export function createTranslateLoader(http: HttpClient): MultiTranslateHttpLoader {
  return new MultiTranslateHttpLoader(http, [
  ]);
}

export const httpServices = [
    GenericTaskService
];

export const httpInterceptorProviders = [
  // error interceptor
  {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true},
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    // Traduzioni per i nomi colonne sulle tabelle
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      },
      defaultLanguage: 'it'
    })
  ],
  providers: [
    // Http services for the backend API
    httpServices,

    // Per le traduzione del paginator
    {provide: MatPaginatorIntl, useClass: CustomMatPaginatorIntl},
    httpInterceptorProviders,
    // CacheMapService,
    // {provide: Cache, useClass: CacheMapService}
  ]
})
export class CoreModule extends EnsureModuleLoadedOnceGuard {
  // Ensure that CoreModule is only loaded into AppModule
  // Looks for the module in the parent injector to see if it's already been loaded (only want it loaded once)
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    super(parentModule);
  }
}
