import { Component } from '@angular/core';
import { DatePickerModule, DatePicker } from 'primeng/datepicker';
import { FormatTypes } from '../model/format-types';

@Component({
  selector: 'test-comp',
  imports: [DatePickerModule, DatePicker],
  templateUrl: './test-comp.html',
  styleUrl: './test-comp.css',
})
export class TestComp {

    dateFormats: FormatTypes[] = []

    constructor() {
        this.dateFormats = FormatTypes.getDateTimeFormatTypes()
    }

}
