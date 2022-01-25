import {GenericTaskProperty} from './generic-task-property.model';

export interface GenericTask {
    oid: number;
    acronym: string;
    scheduleTimes: string;
    scheduledDays: string;
    richProperties: GenericTaskProperty;
}
