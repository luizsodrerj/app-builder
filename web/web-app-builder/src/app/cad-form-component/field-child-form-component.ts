import { Component, EventEmitter, model, OnInit, Output } from "@angular/core";
import { FormsModule } from '@angular/forms';

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
    typeSelectedValue = ''
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

    getFieldTypeName(id: string): string {
        let typeName = ''
        this.types.forEach((type:any, index:number) => {
            if (type.id == id) {
                typeName = type.typeName
            }
        });
        return typeName;
    }

    ngOnInit(): void {
        this.types.push({
            typeName: 'Campo de Texto',
            id: '1'
        })
    }


}
