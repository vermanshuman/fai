import {MatPaginatorIntl} from '@angular/material/paginator';
import {TranslateService} from '@ngx-translate/core';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomMatPaginatorIntl extends MatPaginatorIntl {

  constructor(private translateService: TranslateService) {
    super();

    // React whenever the language is changed
    this.translateService.onLangChange.subscribe((_event: Event) => {
      this.translateLabels();
    });

    // Initialize the translations once at construction time
    this.translateLabels();
  }

  injectTranslateService(translate: TranslateService): void {
    this.translateService = translate;

    this.translateService.onLangChange.subscribe(() => {
      this.translateLabels();
    });

    this.translateLabels();
  }

  getRangeLabel = (page: number, pageSize: number, length: number): string => {
    const of = this.translateService ? this.translateService.instant('PAGINATOR.OF') : 'of';
    if (length === 0 || pageSize === 0) {
      return '0 ' + of + ' ' + length;
    }
    length = Math.max(length, 0);
    const startIndex = page * pageSize > length ? (Math.ceil(length / pageSize) - 1) * pageSize : page * pageSize;

    const endIndex = Math.min(startIndex + pageSize, length);
    return startIndex + 1 + ' - ' + endIndex + ' ' + of + ' ' + length;
  }

  translateLabels(): void {
    this.translateService.get([
      'PAGINATOR.FIRST_PAGE',
      'PAGINATOR.ITEMS_PER_PAGE',
      'PAGINATOR.NEXT_PAGE',
      'PAGINATOR.PREVIOUS_PAGE'
    ])
      .subscribe(translation => {
        this.firstPageLabel = translation['PAGINATOR.FIRST_PAGE'];
        this.lastPageLabel = translation['PAGINATOR.LAST_PAGE'];
        this.itemsPerPageLabel = translation['PAGINATOR.ITEMS_PER_PAGE'];
        this.nextPageLabel = translation['PAGINATOR.NEXT_PAGE'];
        this.previousPageLabel = translation['PAGINATOR.PREVIOUS_PAGE'];
        this.changes.next(); // Fire a change event to make sure that the labels are refreshed
      });
  }
}
