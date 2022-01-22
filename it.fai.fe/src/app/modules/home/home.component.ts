import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {GenericTaskService} from '../../core/http';
import {CsvFile, GenericTask, GenericTaskProperty, Order, Orders, UploadTask} from '../../core/models';
import {NotificationService} from '../../services/notification.service';
import {UploadTaskService} from '../../core/http/upload-task/upload-task.service';
import {TableColumn} from '../../shared/component/generic-table/generic-table.component';
import {Observable, of} from 'rxjs';
import {OrdersService} from '../../core/http/order/orders.service';
import {WEEK_DAYS} from '../../config/weekdays';
import {WEEK_DAYS_DD_CONFIG} from '../../config/weekdays-dd-config';
import {FILE_UPLOAD_TABLE_COLUMNS} from '../../config/file-upload-table-columns';
import {CALENDAR_TABLE_COLUMNS} from '../../config/calendar-table-columns';
import {ORDER_TABLE_COLUMNS} from '../../config/order-table-columns';
import {WarehouseService} from '../../core/http/warehouses/warehouse.service';


const WARE_HOUSE_LIST  = [
    {
        value: '',
        label: '--SELEZIONA  MAGAZZINO--'
    }, {
        value: 'UPS',
        label: 'UPS di Formello'
    },
    {
        value: 'LORETO',
        label: 'Magazzino Loreto'
    },
];

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
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
    allWarehouseList = WARE_HOUSE_LIST;
    warehouseList = JSON.parse(JSON.stringify(WARE_HOUSE_LIST));
    selectedWarehouse = '';
    isEdit = false;
    editCalendarRecordIndex = null;
    activeTab = 1;

    constructor(private formBuilder: FormBuilder,
                private genericTaskService: GenericTaskService,
                private uploadTaskService: UploadTaskService,
                private ordersService: OrdersService,
                private warehouseService: WarehouseService,
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
        this.warehouseService.findAll().subscribe(data => {
            console.log('Warehouse data: ', data);
        });
    }

    initFileUploadForm(): void {
        this.fileUploadForm = this.formBuilder.group({
            fileName: ['', Validators.required],
            magazzino: ['', Validators.required]
        });
    }

    initFtpConfigForm(): void {
        this.calendarRecords$ = of([]);
        this.warehouseList = JSON.parse(JSON.stringify(WARE_HOUSE_LIST));
        this.selectedWarehouse = '';
        this.isEdit = false;
        this.editCalendarRecordIndex = null;
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
            this.ftpConfigForm.get('sunday')?.setValue(true);
            this.ftpConfigForm.get('monday')?.setValue(true);
            this.ftpConfigForm.get('tuesday')?.setValue(true);
            this.ftpConfigForm.get('wednesday')?.setValue(true);
            this.ftpConfigForm.get('thursday')?.setValue(true);
            this.ftpConfigForm.get('friday')?.setValue(true);
            this.ftpConfigForm.get('saturday')?.setValue(true);
        }
        this.ftpConfigForm?.get('magazinno')?.setValue(this.genericTask?.richProperties?.magazzino_acronym);

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
        this.fileUploadForm.reset();
        this.fileUploader.nativeElement.value = null;
    }

    refreshUploadedFiles(): void {
        this.uploadTaskService.findAll(null, null).subscribe(data => {
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
        this.warehouseList = this.warehouseList.filter(w => w.value !== this.selectedWarehouse);
        const record = {
            warehouse: this.selectedWarehouse,
            weekdays: (this.selectedDays.map(this._getObjectKeyValue('item_text')))?.toString()?.replace(/,[s]*/g, ', ')
        };
        let calendarRecords = [];
        this.calendarRecords$.subscribe((records: any) => {
            calendarRecords = records;
        });
        if (this.editCalendarRecordIndex === null) {
            calendarRecords.push(record);
        } else {
            calendarRecords.splice(this.editCalendarRecordIndex, 1, record);
        }
        this.calendarRecords$ = of(calendarRecords);
        this.selectedDays = [];
        this.selectedWarehouse = '';
    }

    onCalendarDeleteClick(event: any): void {
        console.log('event', event);
        const removedWarehouse = WARE_HOUSE_LIST.filter(w => w.value === event.warehouse);
        if (removedWarehouse.length > 0) {
            this.warehouseList.unshift(removedWarehouse[0]);
        }
        let calendarRecords = [];
        this.calendarRecords$.subscribe((records: any) => {
            calendarRecords = records;
        });
        const index = calendarRecords.findIndex(w => w.warehouse === event.warehouse);
        if (index >= 0) {
            calendarRecords.splice(index, 1);
            this.calendarRecords$ = of(calendarRecords);
        }
    }
    onCalendarEditClick(event: any): void {
        this.selectedWarehouse = event.warehouse;
        this.selectedDays = [];
        event.weekdays.split(', ').forEach((day: any) => {
            const obj = {
                item_id: day,
                item_text: day
            };
            this.selectedDays.push(obj);
        });
        this.selectedDays = this.selectedDays.slice();
        this.isEdit = true;
        const selRec = WARE_HOUSE_LIST.filter(w => w.value === event.warehouse);
        if (selRec.length > 0) {
            this.warehouseList.push(selRec[0]);
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
        setTimeout(() => {
            this.onCancelEditCalendar();
        });
    }

    onCancelEditCalendar(): void {
        this.isEdit = false;
        this.selectedWarehouse = '';
        this.selectedDays = [];
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

    onOrderRowSelected(uploadTask: UploadTask): void {
        console.log('row selected: ', uploadTask);
        this.activeProductTab();
    }

    activeProductTab(): void {
        console.log('active Prodotti Tab');
        this.navTabset.tabs[1]._active = false;
        this.navTabset.tabs[2]._active = true;
    }
}
