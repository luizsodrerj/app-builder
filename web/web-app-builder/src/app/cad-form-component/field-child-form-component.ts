import { Component, EventEmitter, model, OnInit, Output } from "@angular/core";
import { FormsModule } from '@angular/forms';
import { FormatTypes } from "../model/format-types";
import { DataTypes } from "../model/data-types";

@Component({
  selector: 'field-child-form-component',
  imports: [FormsModule],
  templateUrl: './field-child-form-component.html'
  //styleUrl: './cad-form-child-component.css',
})
export class FieldChildFormComponent implements OnInit {

    field = {
      name: '',
      label: '',
      dataTypeId: '',
      dataType: '',
      formatTypeName: '',
      formatType: '',
      typeId: '',
      type: '',
      id: ''
    }
    fieldType = {
      typeName: '',
      id: ''
    }
    dateFormatTypes: FormatTypes[] = []
    dataTypes: DataTypes[] = []
    types:any[] = []
    formatTypeSelectedValue = '0'
    dataTypeSelectedValue = '0'
    typeSelectedValue = '0'
    formatTypesVisible: boolean = false

    @Output()
    onChangeChildType = new EventEmitter();

    labelValue = model.required<string>();


    onKeyUpLabel(event: KeyboardEvent) {
        const inputValue = (event.target as HTMLInputElement).value;
        this.labelValue.set(inputValue)
    }

    onChangeType(value: any) {
        this.field.type   = value != '0' ? this.getFieldTypeName(value): ''
        this.field.typeId = value != '0' ? value: ''
        this.fieldType.id = value

        this.onChangeChildType.emit(value)
    }

    onChangeDataType(value: string) {
        let dataType = value != '0' ? DataTypes.getDataType(this.dataTypes, value) : new DataTypes
        this.field.dataTypeId = value != '0' ? dataType.dataTypeId : ''
        this.field.dataType   = value != '0' ? dataType.getDataTypeName(): ''
        this.field.formatType = dataType.hasDefaultMask ? dataType.defaultMaskId : ''

        let showFormats = dataType.showFormatTypes
        let dateTime    = dataType.isDateTime()
        let notEmpty    = value != '0'
        this.formatTypesVisible = notEmpty && dateTime && showFormats
    }

    onChangeDateFormatType(formatTypeValue: string) {
        this.field.formatType = formatTypeValue != '0' ? formatTypeValue : ''

        if (formatTypeValue != '0') {
            this.field.formatTypeName = FormatTypes.getDateTimeFormatType(
                                          this.dateFormatTypes,
                                          formatTypeValue
                                        ).formatTypeName
        }
    }

    getFieldTypeName(id: string): string {
        let typeName = ''
        this.types.forEach((type:any, index:number) => {
            if (type.id == id) {
                typeName = type.typeName
            }
        });
        return typeName;
    }

    populateTypes() {
        this.types.push({
            typeName: 'Campo de Texto',
            id: '1'
        })
    }

    populateDataTypes() {
        this.dataTypes = DataTypes.getDataTypesList()
    }

    populateDateFormatTypes() {
        this.dateFormatTypes = FormatTypes.getDateTimeFormatTypes()
    }

    ngOnInit(): void {
        this.populateDateFormatTypes()
        this.populateDataTypes()
        this.populateTypes()
    }


}
