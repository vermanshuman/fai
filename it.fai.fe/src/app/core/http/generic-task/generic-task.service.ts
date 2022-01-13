import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ObservableApiService} from '../observable-api.service';
import {CsvFile, CsvSchedule, GenericTask} from '../../models';
import {ApiService} from '../api.service';


@Injectable({
    providedIn: 'root'
})
export class GenericTaskService extends ObservableApiService<CsvSchedule> {

    constructor(apiService: ApiService) {
        super('/drugAna', apiService);
    }

    getCSVSchedule = (acronym: string):
        Observable<CsvSchedule> => this.apiService.get(`/config?acronym=` + acronym)

    findTaskByAcronym = (acronym: string):
        Observable<GenericTask> => this.apiService.get(`/config/byAcronym?acronym=` + acronym)

    saveGenericTask = (data: GenericTask):
        Observable<GenericTask> => this.apiService.post(`/config`, data)

    updateGenericTask = (data: GenericTask):
        Observable<GenericTask> => this.apiService.put(`/config`, data)

    uploadCSV = (data: CsvFile):
        Observable<CsvFile> => this.apiService.put(`/config/uploadGenericTaskCSV`, data)
}

