import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import {HttpClient} from "@angular/common/http";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import { MultiTranslateHttpLoader } from '../../core/services/multi-translate-loader';
import {ModalModule} from "ngx-bootstrap/modal";
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";

export function createTranslateLoader(http: HttpClient): MultiTranslateHttpLoader {
  return new MultiTranslateHttpLoader(http, [
    {prefix: './assets/i18n/app/', suffix: '.json'},
    {prefix: './assets/i18n/home/', suffix: '.json'},
  ]);
}

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HomeRoutingModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      },
      defaultLanguage: 'it',
      isolate: true
    }),
    ModalModule.forRoot(),
    NgxMaterialTimepickerModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule
  ]
})
export class HomeModule { }
