import { Component, OnInit, ChangeDetectorRef, Inject, ViewChild } from '@angular/core';
import { HeaderComponent } from '../header-component/header-component';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { BaseComponent } from '../base/base-component';
import { CadFormChildComponent } from './cad-form-child-component';

@Component({
  selector: 'cad-form',
  imports: [
    InputTextModule, HeaderComponent,
    FormsModule, ButtonModule,
    CadFormChildComponent
],
  templateUrl: './cad-form-component.html',
  styleUrl: './cad-form-component.css',
})
export class CadFormComponent extends BaseComponent implements OnInit {

    form = {
        nome: '',
        id: ''
    }
    field = {
        name: '',
        label: '',
        dataType: '',
        typeId: '',
        type: '',
        id: ''
    }
    fieldType = {
        typeName: '',
        id: ''
    }
    types:any[] = []
    prevTypeHidden: boolean = true
    btUpdateHidden: boolean = true
    btAddHidden: boolean = false
    typeSelectedValue = ''

    @ViewChild(CadFormChildComponent) childForm!: CadFormChildComponent


    onChildRowSelect(childEvent: any) {
        let selectedField = childEvent
        this.typeSelectedValue = selectedField.typeId
        this.field.name     = selectedField.name
        this.field.label    = selectedField.label
        this.field.dataType = selectedField.dataType
        this.field.typeId   = selectedField.typeId
        this.field.type     = selectedField.type
        this.prevTypeHidden = false
        this.btUpdateHidden = false
        this.btAddHidden = true
    }

    updateField() {
        this.childForm.updateField(this.field)
        this.btUpdateHidden = true
        this.btAddHidden = false
        this.reset()
    }

    addField() {
        this.field.dataType = 'Texto'
        this.childForm.addField(this.field)
        this.reset()
    }

    cancelUpdateField() {
        this.btUpdateHidden = true
        this.btAddHidden = false
        this.reset()
    }

    reset() {
        this.typeSelectedValue = '0'
        this.prevTypeHidden = true
        this.field.name = ''
        this.field.type = ''
        this.field.typeId = ''
        this.field.label = ''
    }

    onChangeType(value: any) {
        this.fieldType.id = value
        this.prevTypeHidden = !(value != '0')
        this.field.type = value != '0' ? this.getFieldTypeName(value): ''
        this.field.typeId = value != '0' ? value: ''
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

    constructor(private cdr: ChangeDetectorRef) {
      super()
    }

    ngOnInit(): void {
        this.types.push({
            typeName: 'Campo de Texto',
            id: '1'
        })
    }


}



