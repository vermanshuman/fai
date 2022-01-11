import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

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
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initFtpConfigForm();
  }

  initFtpConfigForm(): void {
    this.ftpConfigForm = this.formBuilder.group({
      ftp_url: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      file_name: ['', Validators.required],
      sunday: [],
      monday: [],
      tuesday: [],
      wednesday: [],
      thursday: [],
      friday: [],
      saturday: [],
      hours: this.timeList
    })
  }

  addTime(): void {
    (this.ftpConfigForm?.get('hours') as FormArray)?.push(new FormControl('11:11'));
  }

  removeTime(index: any) {
    (this.ftpConfigForm?.get('hours') as FormArray).removeAt(index);
  }

  onSave(): void {
    this.ftpConfigForm.get('ftp_url').markAsDirty({ onlySelf: true })
    this.ftpConfigForm.get('username').markAsDirty({ onlySelf: true })
    this.ftpConfigForm.get('password').markAsDirty({ onlySelf: true })
    this.ftpConfigForm.get('file_name').markAsDirty({ onlySelf: true })
  }

  browseFile($event): void {
      this.convertFileToBase64($event);
  }

  convertFileToBase64(evt) {
    this.fileUploadErrMsg = '';
    const f = evt.target.files[0];
    console.log(f)
    if(f.type == 'text/csv' || f.type == 'application/vnd.ms-excel'){
      this.selectedFileName = f.name;
      const reader = new FileReader();

      reader.onload = ((theFile) => {
        return (e) => {
          const binaryData = e.target.result;
          this.base64SelectedFile = window.btoa(binaryData)
          console.log('base64String', this.base64SelectedFile);
        };
      })(f);
      // Read in the image file as a data URL.
      reader.readAsBinaryString(f);
    }else{
      this.fileUploadErrMsg = 'Only csv/excel format supported';
    }
    
  }

  uploadFile(): void {
    if(!this.selectedFileName) {
      alert('Please select file')
    } else {
      console.log('base64SelectedFile', this.base64SelectedFile)
    }
  }
}
