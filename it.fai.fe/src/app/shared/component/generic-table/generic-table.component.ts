import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatSort, Sort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {isObservable, Observable} from 'rxjs';
import {DatePipe} from '@angular/common';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import * as moment from 'moment';

export interface TableColumn {
  name: string; // column name
  dataKey: string; // name of key of the actual data in this column
  position?: 'right' | 'left' | 'center'; // should it be right-aligned, left-aligned or center-aligned?
  isSortable?: boolean; // can a column be sorted?
  isDate?: boolean; // is the column a Data type
  isTime?: boolean; // is the column a Data type
  columnWidth?: string;
  method?: { methodName: string, methodParam: string };
  isCurrency?: boolean;
  isBold?: boolean;
  isPercentage?: boolean;
  clickable?: boolean;
  isImage?: boolean;
  isInput?: boolean;
  isDropdown?: boolean;
  isHtml?: boolean;
  showOutOf?: string[];
}

export interface RowActionIcons {
  edit?: string;
  delete?: string;
  download?: string;
  complete?: string;
  apri?: string;
  custom?: string;
  position?: 'right' | 'left' | 'center'; // should it be right-aligned, left-aligned or center-aligned?
}

export interface AddAction {
  buttonIcon: string;
  buttonText: string;
  disabled?: boolean;
}

export interface UploadAction {
  buttonIcon: string;
  buttonText: string;
}

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.scss']
})
export class GenericTableComponent<T> implements OnInit, AfterViewInit {

  public tableDataSource = new MatTableDataSource<any>([]);
  public allData: any;
  public displayedColumns: string[] = [];
  groupedColumns: string[] = ['groupedHeader'];
  @ViewChild(MatSort, {static: false}) matSort?: MatSort;
  @ViewChild(MatPaginator, {static: false}) matPaginator?: MatPaginator;

  // Inputs
  @Input() isPageable = false;
  @Input() isSortable = false;
  @Input() isFilterable = false;
  @Input() inUpperCase = false;
  @Input() isShortActions = false;
  @Input() isDADateFilterable = false;
  @Input() isADateFilterable = false;
  @Input() dateField = 'date';
  @Input() mainContainerClass = '';
  @Input() tableColumns: TableColumn[] = [];
  @Input() addAction: AddAction | undefined;
  @Input() uploadAction: UploadAction | undefined;
  @Input() rowActionIcons: RowActionIcons | undefined;
  @Input() paginationSizes: number[] = [5, 10, 15, 20];
  @Input() defaultPageSize = this.paginationSizes[1];
  @Input() enableRowToggle = false;
  @Input() noSearchPanel = false;
  @Input() isFormComponentTable = false;
  @Input() paginationAlwaysShow = false;
  @Input() isEditable = true;
  @Input() textFilter = 'search';
  @Input() tableHeader = '';
  @Input() replaceBooleanTrue = '';
  @Input() replaceBooleanFalse = '';
  @Input() isMatCardTable = false;
  @Input() isNoTextBtn = false;
  @Input() externalFilterSearch = '';
  @Input() rowBg: any | undefined;

  @Input() set tableData(data: Observable<any[]> | any[] | undefined) {
    if (data) {
      if (isObservable(data)) {
        data.subscribe(d => {
          if (d?.length >= 0) {
            this.setTableDataSource(d);
          }
        });
      } else {
        this.setTableDataSource(data);
      }
    }
  }

  // Outputs
  @Output() sort: EventEmitter<Sort> = new EventEmitter();
  @Output() ngAdd: EventEmitter<any> = new EventEmitter();
  @Output() ngEdit: EventEmitter<any> = new EventEmitter<any>();
  @Output() ngDelete: EventEmitter<any> = new EventEmitter<any>();
  @Output() ngDownload: EventEmitter<any> = new EventEmitter<any>();
  @Output() ngComplete: EventEmitter<any> = new EventEmitter<any>();
  @Output() ngUpload: EventEmitter<any> = new EventEmitter();
  @Output() ngRowSelected: EventEmitter<any> = new EventEmitter();

  selectedDADate: Date | undefined;
  selectedADate: Date | undefined;

  constructor() {
  }

  /**
   * OnInit component lifecycle callback.
   */
  ngOnInit(): void {
    const columnNames = this.tableColumns.map((tableColumn: TableColumn) => tableColumn.name);
    if (this.rowActionIcons) {
      this.displayedColumns = [...columnNames, 'actions'];
    } else {
      this.displayedColumns = columnNames;
    }
    if (this.matPaginator) {
      this.tableDataSource.paginator = this.matPaginator;
    }
  }

  /**
   * AfterViewInit component lifecycle callback.
   * Needed in order to make pagination work with *ngIf.
   */
  ngAfterViewInit(): void {
    if (this.matSort) {
      this.tableDataSource.sort = this.matSort;
    }
    if (this.matPaginator) {
      this.tableDataSource.paginator = this.matPaginator;
    }
  }

  /**
   * Set table data table.
   * @param data Table data.
   * @private
   */
  setTableDataSource(data: any): void {
    this.tableDataSource = new MatTableDataSource(data);
    this.allData = data;
    if (this.matSort) {
      this.tableDataSource.sort = this.matSort;
    }
    if (this.matPaginator) {
      this.tableDataSource.paginator = this.matPaginator;
    }
  }

  /**
   * OnChanges component lifecycle callback
   */
   ngOnChanges(changes: any): void {
    if (changes.externalFilterSearch) {
      this.filterTable(changes.externalFilterSearch.currentValue);
    }
  }

  /**
   * Apply the filter on the table data.
   * @param event Filter write event
   */
  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tableDataSource.filter = filterValue.trim().toLowerCase();
    this.tableDataSource.filterPredicate = (data: any, filter) => {
      const dataStr = JSON.stringify(data).toLowerCase();
      return dataStr.indexOf(filter) !== -1;
    };
  }

  filterTable(value: string): void {
    this.tableDataSource.filter = value.trim().toLowerCase();
    this.tableDataSource.filterPredicate = (data: any, filter) => {
      const dataStr = JSON.stringify(data).toLowerCase();
      return dataStr.indexOf(filter) !== -1;
    };
  }

  applyDADateFilter(daDate: MatDatepickerInputEvent<any, any>): void {
    this.selectedDADate = daDate.value;
    this._filterData();
  }

  deleteDADateFilter(): void {
    this.selectedDADate = undefined;
    this._filterData();
  }

  deleteADateFilter(): void {
    this.selectedADate = undefined;
    this._filterData();
  }

  applyADateFilter(aDate: MatDatepickerInputEvent<any, any>): void {
    this.selectedADate = aDate.value;
    this._filterData();
  }

  private _filterData(): void {

    this.tableDataSource.data = this.allData;

    if (this.selectedDADate || this.selectedADate) {
      this.tableDataSource.data = this.tableDataSource.data.filter(data => {
        if (this.selectedDADate && this.selectedADate) {
          return moment(data[this.dateField]).toDate() >= this.selectedDADate && moment(data[this.dateField]).toDate() <= this.selectedADate;
        } else if (this.selectedDADate) {
          return moment(data[this.dateField]).toDate() >= this.selectedDADate;
        } else if (this.selectedADate) {
          return moment(data[this.dateField]).toDate() <= this.selectedADate;
        }
        return data;
      });
    }

  }

  /**
   * Apply the sorting on the table data.
   * @param sortParameters The current sort state.
   */
  sortTable(sortParameters: Sort): void {
    // defining name of data property, to sort by, instead of column name
    const dataKey = this.tableColumns.find(column => column.name === sortParameters.active)?.dataKey;
    if (dataKey) {
      sortParameters.active = dataKey;
      const data = this.tableDataSource.data.slice();
      const dataKeyArr = dataKey.split('.');
      this.tableDataSource.data = data.sort((a, b) => {
        const isAsc = sortParameters.direction === 'asc';
        if (dataKeyArr.length > 1) {
          return this.compare_data((a[dataKeyArr[0]])[dataKeyArr[1]], (b[dataKeyArr[0]])[dataKeyArr[1]], isAsc);
        }
        return this.compare_data(a[dataKey], b[dataKey], isAsc);
      });
      this.sort.emit(sortParameters);
    }
  }

  emitAddAction(): void {
    this.ngAdd.emit();
  }

  emitEditAction(row: any): void {
    this.ngEdit.emit(row);
  }

  emitDeleteAction(row: any): void {
    this.ngDelete.emit(row);
  }

  emitDownloadAction(row: any): void {
    this.ngDownload.emit(row);
  }

  emitCompleteAction(row: any): void {
    this.ngComplete.emit(row);
  }

  emitUploadAction(): void {
    this.ngUpload.emit();
  }

  /**
   * Resolve access to nested object using dot notation.
   * @param objectKey Object key for with find the value
   * @param baseObject Base object.
   */
  resolve(objectKey: string, baseObject: any): any {
    let value = '';
    if (objectKey.includes('+')) {
      const res: string[] = [];
      objectKey.split('+').forEach(key => {
        res.push(this.resolve(key, baseObject));
      });
      return res;
    } else if (objectKey.includes('|')) {
      objectKey.split('|').reverse().forEach(key => {
        const tempVal = key.split('.').reduce((p, q) => {
          return p ? p[q] : null;
        }, baseObject || self);
        value = tempVal ? tempVal : value;
      });
    } else {
      value = objectKey.split('.').reduce((p, q) => {
        return p ? p[q] : null;
      }, baseObject || self);
    }
    if (this.replaceBooleanFalse && value !== null && value !== undefined && value.toString() === 'false') {
      return this.replaceBooleanFalse;
    }
    if (this.replaceBooleanTrue) {
      value = value ? (value.toString() === 'true' ? this.replaceBooleanTrue : value.toString()) : '';
    }
    if (this.inUpperCase) {
      value = value ? value.toString().toUpperCase() : '';
    }
    return value ? value.toString() : '';
  }

  gridEvent(event: any, objectKey: string, baseObject: any): any {
    if (event.methodName === 'nonSpecificatoUscita') {
      return this.nonSpecificatoUscita(event, objectKey, baseObject, event.methodParam);
    } else if (event.methodName === 'nonSpecificatoRientro') {
      return this.nonSpecificatoRientro(event, objectKey, baseObject, event.methodParam);
    }
  }

  nonSpecificatoUscita(event: any, objectKey: string, baseObject: any, methodParam: string): any {
    const value = this.resolve(objectKey, baseObject);
    const datePipe = new DatePipe('it-IT');
    if (value) {
      if (methodParam === 'Uscita Programmata') {
        return datePipe.transform(value, 'dd/MM/yyyy');
      } else if (methodParam === 'Today') {
        return datePipe.transform(value, 'HH:mm');
      } else {
        return datePipe.transform(value, 'dd/MM/yyyy HH:mm');
      }
    } else if (baseObject != null) {
      if (methodParam === 'Non specificato') {
        return baseObject.actualExitDate ? datePipe.transform(baseObject.actualExitDate, 'dd/MM/yyyy HH:mm') :
          baseObject.plannedExitDate ? datePipe.transform(baseObject.plannedExitDate, 'dd/MM/yyyy') : event.methodParam;
      } else if (methodParam === 'Uscita Programmata') {
        return baseObject.plannedExitDate ? datePipe.transform(baseObject.plannedExitDate, 'dd/MM/yyyy') : 'Non specificata';
      } else if (methodParam === 'Uscita Effettiva') {
        return baseObject.actualExitDate ? datePipe.transform(baseObject.actualExitDate, 'dd/MM/yyyy HH:mm') : 'Non specificata';
      }
    }
  }

  nonSpecificatoRientro(event: any, objectKey: string, baseObject: any, methodParam: string): any {
    const value = this.resolve(objectKey, baseObject);
    const datePipe = new DatePipe('it-IT');
    if (value) {
      if (methodParam === 'Rientro Programmato') {
        return datePipe.transform(value, 'dd/MM/yyyy');
      } else if (methodParam === 'Today') {
        return datePipe.transform(value, 'HH:mm');
      } else {
        return datePipe.transform(value, 'dd/MM/yyyy HH:mm');
      }
    } else if (baseObject != null) {
      if (methodParam === 'Non specificato') {
        return baseObject.actualReturnDate ? datePipe.transform(baseObject.actualReturnDate, 'dd/MM/yyyy HH:mm') :
          baseObject.plannedReturnDate ? datePipe.transform(baseObject.plannedReturnDate, 'dd/MM/yyyy') : event.methodParam;
      } else if (methodParam === 'Rientro Programmato') {
        return baseObject.plannedReturnDate ? datePipe.transform(baseObject.plannedReturnDate, 'dd/MM/yyyy') : 'Non specificato';
      } else if (methodParam === 'Rientro Effettiva') {
        return baseObject.actualReturnDate ? datePipe.transform(baseObject.actualReturnDate, 'dd/MM/yyyy HH:mm') : 'Non specificato';
      }
    }
  }

  displayPaginator(): boolean {
    return this.isPageable && this.tableDataSource.data.length > this.paginationSizes[0];
  }

  compare_data(a: number | string, b: number | string, isAsc: boolean): number {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  checkIfItMultipleLines(obj: any): boolean {
    return Array.isArray(obj);
  }

  emitSelectRow(element: any) {
    this.ngRowSelected.emit(element);
  }

  getBackgroundColor(row: any) {
    if(this.rowBg) {
      const keys = Object.keys(this.rowBg);
      if(keys && row[keys[0]]== this.rowBg[keys[0]]) {
        return 'bg-red';
      }
    }
    return '';
  }
}
