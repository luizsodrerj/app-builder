import { Component, EventEmitter, Output } from '@angular/core';
import { Table, TableModule } from 'primeng/table';

@Component({
  selector: 'cad-form-child-component',
  imports: [TableModule],
  templateUrl: './cad-form-child-component.html',
  styleUrl: './cad-form-child-component.css',
})
export class CadFormChildComponent {

    @Output() childEvent = new EventEmitter();

    fields: any[] = []

    selectedField: any


    updateField(parentField: any) {
        this.selectedField.name       =	parentField.name
        this.selectedField.label      = parentField.label
        this.selectedField.dataTypeId = parentField.dataTypeId
        this.selectedField.dataType   = parentField.dataType
        this.selectedField.typeId     = parentField.typeId
        this.selectedField.type       = parentField.type
    }

    onRowSelect(event: any) {
        this.childEvent.emit(event.data)
    }

    addField(field: any) {
        this.fields.push({
          name: 	    field.name,
          label: 	    field.label,
          dataTypeId: field.dataTypeId,
          dataType:   field.dataType,
          formatType: field.formatType,
          typeId:     field.typeId,
          type: 	    field.type
        })
    }


}
