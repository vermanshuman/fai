import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {GenericTaskService} from '../../core/http';
import {
    CsvFile,
    GenericTask,
    GenericTaskProperty,
    Order,
    Supplier,
    UploadTask,
    Warehouse
} from '../../core/models';
import {NotificationService} from '../../services/notification.service';
import {UploadTaskService} from '../../core/http/upload-task/upload-task.service';
import {TableColumn} from '../../shared/component/generic-table/generic-table.component';
import {Observable, of, Subject, timer} from 'rxjs';
import {OrdersService} from '../../core/http/order/orders.service';
import {WEEK_DAYS} from '../../config/weekdays';
import {WEEK_DAYS_DD_CONFIG} from '../../config/weekdays-dd-config';
import {FILE_UPLOAD_TABLE_COLUMNS} from '../../config/file-upload-table-columns';
import {CALENDAR_TABLE_COLUMNS} from '../../config/calendar-table-columns';
import {ORDER_TABLE_COLUMNS} from '../../config/order-table-columns';
import {WarehouseService} from '../../core/http/warehouses/warehouse.service';
import {SupplierService} from '../../core/http/supplier/supplier.service';
import {PRODUCT_TABLE_COLUMNS} from '../../config/product-table-columns';
import {ProductService} from '../../core/http/product/product.service';
import {retry, share, switchMap, takeUntil} from 'rxjs/operators';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy{
    private stopPolling = new Subject();
    ftpConfigForm: FormGroup;
    fileUploadForm: FormGroup;
    base64SelectedFile = '';
    fileUploadErrMsg = '';
    genericTask: GenericTask;
    hoursList = [];
    @ViewChild('ftpConfigureModal', {static: false}) ftpConfigureModal: any;
    @ViewChild('uploader') fileUploader: ElementRef;
    getCSVScheduleDetail: any = null;
    uploadedFiles$: Observable<UploadTask[]> | undefined;
    orders$: Observable<Order[]> | undefined;
    fileUploadTableColumns: TableColumn[] = FILE_UPLOAD_TABLE_COLUMNS;
    orderTableColumns: TableColumn[] = ORDER_TABLE_COLUMNS;
    @ViewChild('ftpModalTabset', { static: false }) ftpModalTabset: any;
    @ViewChild('navTabset', { static: false }) navTabset: any;
    weekdays = WEEK_DAYS;
    selectedDays = [];
    weekdayDropdownSettings = WEEK_DAYS_DD_CONFIG;
    calendarRecords$: Observable<any[]> = of([]);
    calendarTableColumns = CALENDAR_TABLE_COLUMNS;
    allWarehouseList: Warehouse[] = [];
    warehouseList: Warehouse[] = JSON.parse(JSON.stringify([]));
    allSuppliers: Supplier[] = [];
    suppliers: Supplier[] = [];
    selectedSupplier = '';
    previousSelectedSupplier = '';
    isEdit = false;
    editCalendarRecordIndex = null;
    activeTab = 1;
    calendarStartDate = '';
    calendarEndDate = '';
    products$: Observable<any[]> | undefined;
    productTableColumns: TableColumn[] = PRODUCT_TABLE_COLUMNS;
    usedSuppliers = [
        {
            codice: 'DEMO03',
            oid: 9
        },
        {
            codice: '182560',
            oid: 11
        }
    ];
    savedCalendarRecords: any[] = [
        {
            dayOfWeek:  [1, 2, 3, 4, 5, 6, 7],
            endHour: '13:15',
            oidSupplier: 'DEMO03',
            startHour: '12:16'
        },
        {
            dayOfWeek:  [1, 2, ],
            endHour: '14:16',
            oidSupplier: '182560',
            startHour: '12:16'
        }
    ];

    constructor(private formBuilder: FormBuilder,
                private genericTaskService: GenericTaskService,
                private uploadTaskService: UploadTaskService,
                private ordersService: OrdersService,
                private warehouseService: WarehouseService,
                private supplierService: SupplierService,
                private productService: ProductService,
                private notifyService: NotificationService) {
    }

    ngOnInit(): void {
        this.initFileUploadForm();
        this.initFtpConfigForm();
        this.genericTaskService.getCSVSchedule('IMP_ORDINE_IN').subscribe(data => {
            console.log('getCSVSchedule', data);
            this.getCSVScheduleDetail = data;
        });
        this.refreshUploadedFiles();
        this.genericTaskService.findTaskByAcronym('IMP_ORDINE_IN').subscribe(data => {
            console.log('IMP_ORDINE_IN data: ', data);
            this.genericTask = data;
        });
        this.warehouseService.findAll().subscribe((data: any) => {
            console.log('Warehouse data: ', data.warehouses);
            this.allWarehouseList = this.warehouseList =  data.warehouses;
        });

    }

    ngOnDestroy() {
        this.stopPolling.next();
    }

    initFileUploadForm(): void {
        this.fileUploadForm = this.formBuilder.group({
            fileName: ['', Validators.required],
            magazzino: ['', Validators.required]
        });
    }

    initFtpConfigForm(): void {
        this.calendarRecords$ = of([]);
        this.resetFtpConfigData();
        this.warehouseList = JSON.parse(JSON.stringify(this.allWarehouseList));
        this.supplierService.findAll().subscribe((data: any) => {
            this.allSuppliers = data.suppliers;
            this._prepareSuppliers();
            this._prepareCalendarRecords();
        });
        const timeArray = [];
        if (this.genericTask?.scheduleTimes) {
            this.hoursList = this.genericTask?.scheduleTimes.split(',');
            for (let i = 0; i < this.hoursList.length; i++) {
                const formControl = new FormControl(this.hoursList[i].trim());
                timeArray.push(formControl);
            }
        }
        timeArray.push(new FormControl('11:11'));
        this.ftpConfigForm = this.formBuilder.group({
            ftp_url: ['', Validators.required],
            username: ['', Validators.required],
            password: [],
            file_name: ['', Validators.required],
            magazinno: ['', Validators.required],
            sunday: [],
            monday: [],
            tuesday: [],
            wednesday: [],
            thursday: [],
            friday: [],
            saturday: [],
            hours: new FormArray(timeArray)
        });
        this.ftpConfigForm.get('ftp_url')?.setValue(this.genericTask?.richProperties?.host);
        this.ftpConfigForm.get('username')?.setValue(this.genericTask?.richProperties?.login);
        this.ftpConfigForm.get('file_name')?.setValue(this.genericTask?.richProperties?.csvInFileName);
        if (!this.genericTask?.richProperties?.password || this.genericTask?.richProperties?.password.length === 0) {
            this.ftpConfigForm.controls['password'].setValidators([Validators.required]);
        }
        if (this.genericTask?.scheduledDays) {
            this.ftpConfigForm.get('sunday')?.setValue(this.genericTask?.scheduledDays.charAt(0) === 'X');
            this.ftpConfigForm.get('monday')?.setValue(this.genericTask?.scheduledDays.charAt(1) === 'X');
            this.ftpConfigForm.get('tuesday')?.setValue(this.genericTask?.scheduledDays.charAt(2) === 'X');
            this.ftpConfigForm.get('wednesday')?.setValue(this.genericTask?.scheduledDays.charAt(3) === 'X');
            this.ftpConfigForm.get('thursday')?.setValue(this.genericTask?.scheduledDays.charAt(4) === 'X');
            this.ftpConfigForm.get('friday')?.setValue(this.genericTask?.scheduledDays.charAt(5) === 'X');
            this.ftpConfigForm.get('saturday')?.setValue(this.genericTask?.scheduledDays.charAt(6) === 'X');
        }
        this.ftpConfigForm?.get('magazinno')?.setValue(this.genericTask?.richProperties?.magazzino_acronym);

    }

    private _prepareCalendarRecords(): void {
        const savedCalendarRecords = JSON.parse(JSON.stringify(this.savedCalendarRecords));
        savedCalendarRecords?.forEach((cr: any) => {
            const dayStrArr = [];
            cr?.dayOfWeek?.forEach((dayNo: number) => {
                console.log('dayNo', dayNo);
                if (dayNo === 1) {
                    dayStrArr.push('Sunday');
                } else if (dayNo === 2) {
                    dayStrArr.push('Monday');
                } else if (dayNo === 3) {
                    dayStrArr.push('Tuesday');
                } else if (dayNo === 4) {
                    dayStrArr.push('Wednesday');
                } else if (dayNo === 5) {
                    dayStrArr.push('Thursday');
                } else if (dayNo === 6) {
                    dayStrArr.push('Friday');
                } else if (dayNo === 7) {
                    dayStrArr.push('Saturday');
                }
            });
            cr.dayOfWeek = dayStrArr?.toString()?.replace(/,[s]*/g, ', ');
        });
        this.calendarRecords$ = of(savedCalendarRecords);
    }

    private _prepareSuppliers(): void {
        this.suppliers = [];
        const allSuppliers = JSON.parse(JSON.stringify(this.allSuppliers));
        this.usedSuppliers.forEach((su: Supplier) => {
            allSuppliers.forEach((as: Supplier, index) => {
                if (as.oid === su.oid) {
                    allSuppliers.splice(index, 1);
                }
            });
        });
        this.suppliers = allSuppliers;
    }

    addTime(): void {
        this.hoursList = this.ftpConfigForm?.get('hours').value;
        (this.ftpConfigForm?.get('hours') as FormArray)?.push(new FormControl('11:11'));
    }

    removeTime(index: any) {
        (this.ftpConfigForm?.get('hours') as FormArray).removeAt(index);
        this.hoursList = this.ftpConfigForm?.get('hours').value;
        this.hoursList.splice(index, 1);
    }

    onFTPFormSave(): void {
        // ftp tab(first tab)
        if (this.ftpModalTabset.tabs[0].active) {
            this.ftpConfigForm.get('ftp_url').markAsDirty({onlySelf: true});
            this.ftpConfigForm.get('username').markAsDirty({onlySelf: true});
            this.ftpConfigForm.get('password').markAsDirty({onlySelf: true});
            this.ftpConfigForm.get('file_name').markAsDirty({onlySelf: true});
            this.ftpConfigForm.get('magazinno').markAsDirty({onlySelf: true});
            const weekdayString = this.prepareWeekdays(['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday',
                'saturday']);

            console.log(this.ftpConfigForm);
            if (this.ftpConfigForm?.valid) {
                let genericTask: GenericTask | undefined;
                let genericTaskProperty: GenericTaskProperty | undefined;
                genericTask = {...this.genericTask};

                genericTask.scheduleTimes = this.hoursList.join(',');
                genericTask.scheduledDays = weekdayString;
                if (genericTask.richProperties) {
                    genericTaskProperty = {...genericTask.richProperties};
                }
                genericTaskProperty.host = this.ftpConfigForm.get('ftp_url')?.value;
                genericTaskProperty.login = this.ftpConfigForm.get('username')?.value;
                if (this.ftpConfigForm.get('password').value) {
                    genericTaskProperty.password = this.ftpConfigForm.get('password')?.value;
                }
                genericTaskProperty.csvInFileName = this.ftpConfigForm.get('file_name')?.value;
                genericTaskProperty.magazzino_acronym = this.ftpConfigForm.get('magazinno')?.value;
                genericTask.richProperties = genericTaskProperty;
                if (genericTask.oid) {
                    console.log('Updating Generic Task ', genericTask);
                    this.genericTaskService.updateGenericTask(genericTask).subscribe(data => {
                        console.log('Returned data after update: ', data);
                        this.notifyService.showSuccess('Data updated successfully');
                        this.genericTask = data;
                        this.ftpConfigureModal?.hide();
                    }, (error: any) => {
                        this.notifyService.showError('Something went wrong, data not saved');
                    });
                }
            }
        } else if (this.ftpModalTabset.tabs[1].active) {  // calendar tab(second tab)
            let calendarRecords = [];
            const usedSuppliers = [];
            this.calendarRecords$.subscribe((calRecords: any) => {
                const records = JSON.parse(JSON.stringify(calRecords));
                records.forEach((rec: any) => {
                    this.allSuppliers.forEach((s: any) => {
                        if (s.codice === rec.oidSupplier) {
                            usedSuppliers.push(s);
                        }
                    });
                    const dayArr = [];
                    rec?.dayOfWeek.split(', ')?.forEach((day) => {
                        if (day.includes('Sunday')) {
                            dayArr.push(1);
                        } else if (day.includes('Monday')) {
                            dayArr.push(2);
                        }  else if (day.includes('Tuesday')) {
                            dayArr.push(3);
                        }  else if (day.includes('Wednesday')) {
                            dayArr.push(4);
                        }  else if (day.includes('Thursday')) {
                            dayArr.push(5);
                        }  else if (day.includes('Friday')) {
                            dayArr.push(6);
                        }  else if (day.includes('Saturday')) {
                            dayArr.push(7);
                        }
                    });
                    rec.dayOfWeek = dayArr;
                });
                calendarRecords = JSON.parse(JSON.stringify(records));
                console.log('usedSuppliers' , usedSuppliers);
                console.log('calendarRecords' , calendarRecords);
            });
        }
    }

    private prepareWeekdays(days: string[]): string {
        const weekDays = [];
        days.forEach((day: string) => {
            weekDays.push(this.ftpConfigForm.get(day).value ? 'X' : '-');
        });
        return weekDays.join('');
    }

    browseFile($event): void {
        this.convertFileToBase64($event);
    }

    convertFileToBase64(evt) {
        this.fileUploadErrMsg = '';
        const f = evt.target.files[0];
        if (f.type === 'text/csv' || f.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
            || f.type === 'application/vnd.ms-excel') {
            this.fileUploadForm.patchValue({fileName: f.name});
            const reader = new FileReader();

            reader.onload = ((theFile) => {
                return (e) => {
                    const binaryData = e.target.result;
                    this.base64SelectedFile = window.btoa(binaryData);
                    console.log('base64String', this.base64SelectedFile);
                };
            })(f);
            // Read in the image file as a data URL.
            reader.readAsBinaryString(f);
        } else {
            this.fileUploadErrMsg = 'Only csv/excel format supported';
        }
    }

    uploadFile(): void {
        this.fileUploadForm.get('fileName').markAsDirty({onlySelf: true});
        this.fileUploadForm.get('magazzino').markAsDirty({onlySelf: true});
        if (this.fileUploadForm.valid) {
            let csvFile: CsvFile | undefined;
            if (this.base64SelectedFile) {
                csvFile = {
                    csvFileContent: this.base64SelectedFile,
                    csvFileName: this.fileUploadForm.get('fileName').value,
                    magazzinoAcronym: this.fileUploadForm.get('magazzino').value
                };
                console.log('Uploading csv : ', csvFile);
                this.uploadTaskService.uploadCSV(csvFile).subscribe(data => {
                    console.log('Updated csv');
                    this.notifyService.showSuccess('Data saved successfully');
                    this.refreshUploadedFiles();
                }, (error: any) => {
                    console.log('error', error);
                    this.notifyService.showError('PSomething went wrong, data not saved');
                });
                this.resetFileUploadForm();
            } else {
                this.notifyService.showError('Please select valid file');
                this.fileUploadForm.get('fileName').setErrors({onlySelf: true});
            }
            console.log('base64SelectedFile', this.base64SelectedFile);
        }
    }

    resetFileUploadForm(): void {
        this.initFileUploadForm();
        this.fileUploader.nativeElement.value = null;
    }

    refreshUploadedFiles(): void {

        const response = timer(1, 10000).pipe(
            switchMap(() =>   this.uploadTaskService.findAll(null, null)),
            retry(),
            share(),
            takeUntil(this.stopPolling)
        );
        response.subscribe(data => {
                console.log('Tasks ', data);
                if (data && data.uploadTasks) {
                    this.uploadedFiles$ = of(data.uploadTasks);
                }
            },
            (error: any) => {
                console.log('error', error);
                this.notifyService.showError('Error in loading tasks');
            });
    }

    onExecute(uploadTask: UploadTask): void {
        console.log('execute import: ', uploadTask);
        this.uploadTaskService.executeImportCSV(uploadTask?.oid).subscribe(() => {
            this.refreshUploadedFiles();
            this.uploadTaskService.executeCalculator(uploadTask?.oid).subscribe(() => {
                this.refreshUploadedFiles();
                this.uploadTaskService.procurementTask(uploadTask?.oid).subscribe(() => {
                    this.refreshUploadedFiles();
                });
            });
        });
    }

    loadOrders(uploadTask: UploadTask): void {
        if (uploadTask) {
            this.ordersService.findOrdersByCollection(uploadTask.oid).subscribe(data => {
                    console.log('Orders ', data);
                    if (data && data.orders) {
                        this.orders$ = of(data.orders);
                    } else {
                        this.orders$ = of([]);
                    }
                },
                (error: any) => {
                    console.log('error', error);
                    this.notifyService.showError('Error in loading orders');
                });
        } else {
            this.ordersService.findAll().subscribe(data => {
                    console.log('Orders ', data);
                    if (data && data.orders) {
                        this.orders$ = of(data.orders);
                    }
                },
                (error: any) => {
                    console.log('error', error);
                    this.notifyService.showError('Error in loading orders');
                });
        }
    }

    onDaySelect(item: any): void {
        console.log(item);
    }

    onSelectAllDays(items: any): void {
        console.log(items);
    }

    private _getObjectKeyValue(key: string): any {
        const getProp = prop => obj => obj[prop];
        return getProp(key);
    }

    addCalendarRecord(): void {
        let calendarRecords = [];
        this.calendarRecords$.subscribe((records: any) => {
            calendarRecords = records;
        });

        const duplicateIndex = calendarRecords.findIndex(w => w.oidSupplier === this.selectedSupplier);

        if (duplicateIndex >= 0 && this.selectedSupplier !== this.previousSelectedSupplier) {
            alert('already added this supplier');
        }  else {
            this.suppliers = this.suppliers.filter(w => w.codice !== this.selectedSupplier);
            const record = {
                oidSupplier: this.selectedSupplier,
                dayOfWeek: (this.selectedDays.map(this._getObjectKeyValue('item_text')))?.toString()?.replace(/,[s]*/g, ', '),
                startHour: this.calendarStartDate,
                endHour: this.calendarEndDate
            };
            calendarRecords = [];
            this.calendarRecords$.subscribe((records: any) => {
                calendarRecords = records;
            });
            if (this.editCalendarRecordIndex === null) {
                calendarRecords.push(record);
            } else {
                calendarRecords.splice(this.editCalendarRecordIndex, 1, record);
            }

            this.calendarRecords$ = of(calendarRecords);
            setTimeout(() => {
                this.resetFtpConfigData();
            });
        }
    }

    onCalendarDeleteClick(event: any): void {
        const removedSupplier = this.allSuppliers.filter(w => w.codice === event.oidSupplier);
        if (removedSupplier.length > 0) {
            this.suppliers.unshift(removedSupplier[0]);
        }
        let calendarRecords = [];
        this.calendarRecords$.subscribe((records: any) => {
            calendarRecords = records;
        });
        const index = calendarRecords.findIndex(w => w.oidSupplier === event.oidSupplier);
        if (index >= 0) {
            calendarRecords.splice(index, 1);
            this.calendarRecords$ = of(calendarRecords);
        }
    }

    onCalendarEditClick(event: any): void {
        this.selectedSupplier = this.previousSelectedSupplier = event.oidSupplier;
        this.selectedDays = [];
        event.dayOfWeek.split(', ').forEach((day: any) => {
            const obj = {
                item_id: day,
                item_text: day
            };
            this.selectedDays.push(obj);
        });
        this.selectedDays = this.selectedDays.slice();
        this.isEdit = true;
        const selRec = this.allSuppliers.filter(s => s.codice === event.oidSupplier);
        if (selRec.length > 0) {
            this.suppliers.push(selRec[0]);
        }

        let calendarRecords = [];
        this.calendarRecords$.subscribe((records: any) => {
            calendarRecords = records;
        });
        this.editCalendarRecordIndex = calendarRecords.findIndex(w => w === event);
    }

    updateCalendarRecord(): void {
        if (this.editCalendarRecordIndex >= 0 ) {
            this.addCalendarRecord();
        }
    }

    onCancelEditCalendar(): void {
        this.resetFtpConfigData();
    }

    private resetFtpConfigData(): void {
        this.isEdit = false;
        this.selectedSupplier = '';
        this.previousSelectedSupplier = '';
        this.selectedDays = [];
        this.editCalendarRecordIndex = null;
        this.calendarStartDate = '';
        this.calendarEndDate = '';
        this.calendarRecords$ = of([]);
    }

    onFileRowSelected(uploadTask: UploadTask): void {
        console.log('row selected: ', uploadTask);
        this.activeOrderTab(uploadTask);
    }

    activeOrderTab(uploadTask: UploadTask): void {
        console.log('active Ordini Tab');
        this.navTabset.tabs[0]._active = false;
        this.navTabset.tabs[1]._active = true;
        this.loadOrders(uploadTask);
    }

    onOrderRowSelected(order: Order): void {
        console.log('row selected: ', order);
        this.activeProductTab(order);
    }

    activeProductTab(order: Order): void {
        console.log('active Prodotti Tab' , order);
        this.navTabset.tabs[1]._active = false;
        this.navTabset.tabs[2]._active = true;
        this.productTableColumns[0].isHidden = false;
        this.productTableColumns[1].isHidden = true;
        this.loadProducts(order);
    }

    onSelectProductsTab(): void {
        this.productTableColumns[0].isHidden = true;
        this.productTableColumns[1].isHidden = false;
    }

    loadProducts(order: Order): void {
        if (order) {
            this.productService.findProductsByOrder(order.oid).subscribe(data => {
                    console.log('Products for order ', data);
                    if (data && data.products) {
                        this.products$ = of(data.products);
                    } else {
                        this.products$ = of([]);
                    }
                },
                (error: any) => {
                    console.log('error', error);
                    this.notifyService.showError('Error in loading products for order');
                });
        } else {
            this.productService.findAll().subscribe(data => {
                    console.log('Products ', data);
                    if (data && data.products) {
                        this.products$ = of(data.products);
                    } else {
                        this.products$ = of([]);
                    }
                },
                (error: any) => {
                    console.log('error', error);
                    this.notifyService.showError('Error in loading products');
                });
        }
    }
}
