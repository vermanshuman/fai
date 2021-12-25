import {TranslateLoader} from '@ngx-translate/core';
import {HttpClient} from '@angular/common/http';
import {forkJoin, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

export class MultiTranslateHttpLoader implements TranslateLoader {

    constructor(private http: HttpClient,
                public resources: { prefix: string, suffix: string }[] = [{
                    prefix: '/assets/i18n/',
                    suffix: '.json'
                }]) {
    }

    /**
     * Gets the translations from the server
     * @param lang Language
     */
    public getTranslation(lang: string): Observable<any> {
        return forkJoin(this.resources.map(config => this.http.get(`${config.prefix}${lang}${config.suffix}`)))
            .pipe(
                map(response => {
                    return response.reduce((a, b) => {
                        return Object.assign(a, b);
                    });
                }));
    }
}
