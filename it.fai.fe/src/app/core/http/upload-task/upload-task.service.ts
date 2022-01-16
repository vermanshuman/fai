import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ObservableApiService} from '../observable-api.service';
import {CsvFile, CsvSchedule, GenericTask, UploadTask, UploadTasks} from '../../models';
import {ApiService} from '../api.service';


@Injectable({
    providedIn: 'root'
})
export class UploadTaskService extends ObservableApiService<UploadTask> {

    constructor(apiService: ApiService) {
        super('/upload', apiService);
    }

    uploadCSV = (data: CsvFile):
        Observable<CsvFile> => this.apiService.put(`/upload/uploadGenericTaskCSV`, data)

    findAll = (startDate: string, endDate: string):
        Observable<UploadTasks> => this.apiService.get(`/upload?startDate=` + startDate + '&endDate=' + endDate)

    executeImportCSV = (taskOID: number):
        Observable<UploadTask> => this.apiService.get(`/upload/importCSV/${taskOID}`)
}

