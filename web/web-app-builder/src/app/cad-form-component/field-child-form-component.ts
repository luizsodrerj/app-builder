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
      typeId: '',
      type: '',
      id: ''
    }
    fieldType = {
      typeName: '',
      id: ''
    }
    dataTypeSelectedValue = '0'
    typeSelectedValue = '0'
    dataTypes:any[] = []
    types:any[] = []

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

    ngOnInit(): void {
        this.populateDataTypes()
        this.populateTypes()
    }


}
