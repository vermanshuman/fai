import {Component, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {GenericTaskService} from '../../core/http';
import {CsvFile, GenericTask, GenericTaskProperty} from '../../core/models';
import {NotificationService} from '../../services/notification.service';
import {UploadTaskService} from '../../core/http/upload-task/upload-task.service';

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
  getCSVScheduleDetail: any = null;

  constructor(private formBuilder: FormBuilder,
              private genericTaskService: GenericTaskService,
              private uploadTaskService: UploadTaskService,
              private notifyService: NotificationService) { }

  ngOnInit(): void {
    this.initFileUploadForm();
    this.initFtpConfigForm();
    this.genericTaskService.getCSVSchedule('IMP_ORDINE_IN').subscribe(data => {
      console.log('getCSVSchedule', data);
      this.getCSVScheduleDetail = data;
    });

    this.genericTaskService.findTaskByAcronym('IMP_ORDINE_IN').subscribe(data => {
      console.log('IMP_ORDINE_IN data: ', data);
      this.genericTask = data;
    });
    this.uploadTaskService.findAll(null, null).subscribe(data => {
      console.log('Tasks ', data);
    });
  }

  initFileUploadForm(): void {
    this.fileUploadForm = this.formBuilder.group({
      fileName: ['', Validators.required],
      magazzino: ['', Validators.required]
    });
  }

  initFtpConfigForm(): void {
    const timeArray = [];
    if (this.genericTask?.scheduleTimes) {
      this.hoursList = this.genericTask?.scheduleTimes.split(',');
      for (let i = 0; i < this.hoursList.length ; i++) {
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
    if (!this.genericTask?.richProperties?.password || this.genericTask?.richProperties?.password.length === 0 ) {
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
    this.ftpConfigForm.get('ftp_url').markAsDirty({ onlySelf: true });
    this.ftpConfigForm.get('username').markAsDirty({ onlySelf: true });
    this.ftpConfigForm.get('password').markAsDirty({ onlySelf: true });
    this.ftpConfigForm.get('file_name').markAsDirty({ onlySelf: true });
    this.ftpConfigForm.get('magazinno').markAsDirty({ onlySelf: true });
    const weekdayString = this.prepareWeekdays(['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday',
      'saturday']);

    console.log(this.ftpConfigForm);
    if (this.ftpConfigForm?.valid) {
      let genericTask: GenericTask | undefined;
      let genericTaskProperty: GenericTaskProperty | undefined;
      genericTask = {... this.genericTask};

      genericTask.scheduleTimes = this.hoursList.join(',');
      genericTask.scheduledDays = weekdayString;
      if (genericTask.richProperties) {
        genericTaskProperty = {... genericTask.richProperties};
      }
      genericTaskProperty.host =  this.ftpConfigForm.get('ftp_url')?.value;
      genericTaskProperty.login =  this.ftpConfigForm.get('username')?.value;
      if (this.ftpConfigForm.get('password').value) {
        genericTaskProperty.password =  this.ftpConfigForm.get('password')?.value;
      }
      genericTaskProperty.csvInFileName =  this.ftpConfigForm.get('file_name')?.value;
      genericTaskProperty.magazzino_acronym =  this.ftpConfigForm.get('magazinno')?.value;
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
    this.fileUploadForm.get('fileName').markAsDirty({ onlySelf: true });
    this.fileUploadForm.get('magazzino').markAsDirty({ onlySelf: true });
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
        }, (error: any) => {
          console.log('error', error);
          this.notifyService.showError('PSomething went wrong, data not saved');
        });
      } else {
        this.notifyService.showError('Please select valid file');
        this.fileUploadForm.get('fileName').setErrors({ onlySelf: true });
      }
      console.log('base64SelectedFile', this.base64SelectedFile);
    }
  }
}
