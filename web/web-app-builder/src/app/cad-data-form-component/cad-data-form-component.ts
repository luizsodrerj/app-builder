import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { BaseComponent } from '../base/base-component';
import { HeaderComponent } from '../header-component/header-component';
import { ActivatedRoute } from '@angular/router';
import { FormService } from '../service/form-service';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputNumberModule } from 'primeng/inputnumber';
import { FieldData } from '../model/field-data';
import { DataTypes } from '../model/data-types';

@Component({
  selector: 'cad-data-form-component',
  imports: [
    HeaderComponent, FormsModule,
    TableModule, ButtonModule,
    DialogModule, InputNumberModule
  ],
  templateUrl: './cad-data-form-component.html',
  styleUrl: './cad-data-form-component.css',
  providers: [FormService]
})
export class CadDataFormComponent extends BaseComponent implements OnInit {

    fields: FieldData[] = []
    formRegisters: any[] = []
    dlgFormDataVisible: boolean = false

    form = {
      name: '',
      label: '',
      id: ''
    }
    private cdr: ChangeDetectorRef  = inject(ChangeDetectorRef)
    private route: ActivatedRoute   = inject(ActivatedRoute)
    private service: FormService    = inject(FormService)


    onClickBtSalvar() {
        let values: any[] = []
        this.fields.forEach((field:any) => {
            values.push({
              "val": field.value,
              "fieldId": field.id
            })
        })
        let register = {
            "formId": this.form.id,
            "values": values
        }
        this.service.persistRegister(register).pipe().subscribe({
            next: (data) => {
              this.dlgFormDataVisible = true
              this.getLastRegisters()
              this.reset()
              this.cdr.detectChanges()
            }
        })
    }

    onClickBtPesquisar() {
        let values: any[] = []
        let register = {
          "formId": this.form.id,
          "values": values
        }
        this.fields.forEach((field:any) => {
            if (field.value.trim() != '') {
                values.push({
                  "val": field.value
                })
            }
        })
        this.service.findRegisters(register).pipe().subscribe({
            next: (registersData) => {
              this.populateDataTable(registersData)
              this.cdr.detectChanges()
            }
        })
    }

    getLastRegisters() {
        this.service.getLastRegisters(this.form.id).pipe().subscribe({
            next: (registersData) => {
              this.populateDataTable(registersData)
              this.cdr.detectChanges()
            }
        })
    }

    populateDataTable(registersData: any) {
        this.formRegisters.length = 0

        registersData.forEach((regData: any) => {
            let values: any[] = []
            let register = {
                "id": regData.id,
                "values": values
            }
            register.values = this.getValues(regData.values)
            this.formRegisters.push(register)
        })
    }

    getValues(valuesData: any): any[] {
        let values: any[] = []

        this.fields.forEach((field: any) => {
            values.push(this.findField(valuesData, field.id))
        })
        return values
    }

    findField(values: any[], id: string) {
        let fieldValue: any
        values.some((fieldVal: any) => {
            if (fieldVal.fieldId == id) {
                fieldValue = fieldVal
                return true
            }
            return false
        })
        this.formatFieldValue(fieldValue)
        return fieldValue;
    }

    formatFieldValue(fieldValue: any) {
      //formatType
        let isMonetary: boolean = DataTypes.isMonetaryDataType(fieldValue.dataType)
        let isNotEmpty: boolean = fieldValue.val != undefined && fieldValue.val != ''
        if (isNotEmpty && isMonetary) {
            let val = parseFloat(fieldValue.val)
            const formatter = new Intl.NumberFormat('pt-BR', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
            });
            fieldValue.val = formatter.format(val)
        }
    }

    onClickBtLimpar() {
        this.reset()
    }

    reset() {
        this.fields.forEach((field: any) => {
            field.value = ''
        });
    }

    populateFields(fieldsData: any) {
      fieldsData.forEach((fieldData:any) => {
          let field: FieldData = new FieldData
          field.id        = fieldData.id
          field.name  	  = fieldData.name
          field.dataType  = fieldData.dataType
          field.label 	  = fieldData.label
          field.fieldType	= fieldData.typeId
          field.formatType= fieldData.formatType
          field.value		  = ''
          this.fields.push(field)
      });
    }

    constructor() {
        super()
        this.route.params.subscribe(params => {
            this.form.id = params['formId']
            this.service.getForm(this.form.id).pipe().subscribe({
                next: (data) => {
                  this.form.label = data.label
                  this.populateFields(data.fields)
                  this.cdr.detectChanges()
                }
            })
        });
    }

    ngOnInit(): void {
    }

}
