import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {GenericTaskService} from '../../core/http';
import {GenericTask, GenericTaskProperty} from '../../core/models';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  timeList = new FormArray([new FormControl('11:11')]);
  ftpConfigForm: FormGroup;
  selectedFileName = '';
  base64SelectedFile = '';
  fileUploadErrMsg = '';
  genericTask: GenericTask;
  hoursList = [];
  constructor(private formBuilder: FormBuilder,
              private genericTaskService: GenericTaskService) { }

  ngOnInit(): void {
    this.initFtpConfigForm();
    this.genericTaskService.getCSVSchedule('IMP_ORDINE_IN').subscribe(data => {
    });

    this.genericTaskService.findTaskByAcronym('IMP_ORDINE_IN').subscribe(data => {
      this.genericTask = data;
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
    (this.ftpConfigForm?.get('hours') as FormArray)?.push(new FormControl('11:11'));
    this.hoursList = this.ftpConfigForm?.get('hours').value;
  }

  removeTime(index: any) {
    (this.ftpConfigForm?.get('hours') as FormArray).removeAt(index);
  }

  onFTPFormSave(): void {
    this.ftpConfigForm.get('ftp_url').markAsDirty({ onlySelf: true });
    this.ftpConfigForm.get('username').markAsDirty({ onlySelf: true });
    this.ftpConfigForm.get('password').markAsDirty({ onlySelf: true });
    this.ftpConfigForm.get('file_name').markAsDirty({ onlySelf: true });
    this.ftpConfigForm.get('magazinno').markAsDirty({ onlySelf: true });
    console.log(this.ftpConfigForm);
    if (this.ftpConfigForm?.valid) {
      let genericTask: GenericTask | undefined;
      let genericTaskProperty: GenericTaskProperty | undefined;
      genericTask = {... this.genericTask};
      if (this.ftpConfigForm.get('hours')?.value) {
        genericTask.scheduleTimes = this.ftpConfigForm.get('hours').value.join(',');
      }
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
          this.genericTask = data;
        });
      }
    }
  }

  browseFile($event): void {
    this.convertFileToBase64($event);
  }

  convertFileToBase64(evt) {
    this.fileUploadErrMsg = '';
    const f = evt.target.files[0];
    if (f.type === 'text/csv' || f.type === 'application/vnd.ms-excel') {
      this.selectedFileName = f.name;
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
    if (!this.selectedFileName) {
      alert('Please select file');
    } else {
      console.log('base64SelectedFile', this.base64SelectedFile);
    }
  }
}
