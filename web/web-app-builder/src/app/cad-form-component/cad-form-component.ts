import { model } from '@angular/core';
import { Component, OnInit, ChangeDetectorRef, ViewChild, inject, signal } from '@angular/core';
import { HeaderComponent } from '../header-component/header-component';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { BaseComponent } from '../base/base-component';
import { CadFormChildComponent } from './cad-form-child-component';
import { ActivatedRoute, Router } from '@angular/router';
import { CadFormDialogsComponent } from './cadform-dialogs-component';
import { FieldChildFormComponent } from './field-child-form-component';
import { AppService } from '../service/app-service';

@Component({
  selector: 'cad-form',
  imports: [
    InputTextModule, HeaderComponent,
    FormsModule, ButtonModule,
    CadFormChildComponent, CadFormDialogsComponent,
    FieldChildFormComponent
  ],
  templateUrl: './cad-form-component.html',
  styleUrl: './cad-form-component.css',
  providers: [AppService]
})
export class CadFormComponent extends BaseComponent implements OnInit {

    form = {
        nome: '',
        id: ''
    }
    field = {
        name: '',
        label: '',
        dataTypeId: '',
        dataType: '',
        typeId: '',
        type: '',
        id: ''
    }
    prevTypeHidden: boolean = true
    btUpdateHidden: boolean = true
    btAddHidden: boolean = false
    appName = ''
    appId = ''

    @ViewChild(FieldChildFormComponent) fieldChildForm!: FieldChildFormComponent
    @ViewChild(CadFormChildComponent) childForm!: CadFormChildComponent
    @ViewChild(CadFormDialogsComponent) dialogs!: CadFormDialogsComponent

    private service: AppService = inject(AppService)
    private cdr: ChangeDetectorRef  = inject(ChangeDetectorRef)
    private route: ActivatedRoute   = inject(ActivatedRoute)
    private router: Router = inject(Router)

    childLabelValue = signal('')


    onClickBtSalvar() {
        let app     = this.createApp()
        let fields  = this.childForm.fields
        fields.forEach((field:any, index:number) => {
            app.forms[0].fields.push({
              "name"  	  : field.name,
              "dataType" 	: field.dataTypeId,
              "label" 	  : field.label,
              "fieldType"	: field.typeId,
              "formatType": ""
            })
        })
        this.service.saveAppAndForm(app).pipe().subscribe({
            next: (data) => {
              this.dialogs.dlgConfCriaFormVisible = true
              this.cdr.detectChanges()
            }
        })
    }

    onClickChildBtOk(childEvent: any) {
        this.dialogs.dlgConfCriaFormVisible = false
        this.router.navigate(['/index-app-form']);
    }

    createApp() {
        let fields: any[] = []
        return {
            "name": this.appName,
            "forms": [
              {
                "label": this.form.nome,
                "nome" : this.form.nome,
	              "fields" : fields
              }
            ]
        }
    }

    onChildRowSelect(childEvent: any) {
        let selectedField = childEvent
        this.fieldChildForm.typeSelectedValue = selectedField.typeId
        this.fieldChildForm.field.name        = selectedField.name
        this.fieldChildForm.field.label       = selectedField.label
        this.fieldChildForm.field.dataType    = selectedField.dataType
        this.fieldChildForm.field.typeId      = selectedField.typeId
        this.fieldChildForm.field.type        = selectedField.type
        this.fieldChildForm.labelValue.set(selectedField.label)
        this.prevTypeHidden = false
        this.btUpdateHidden = false
        this.btAddHidden = true
    }

    updateField() {
        this.childForm.updateField(this.fieldChildForm.field)
        this.btUpdateHidden = true
        this.btAddHidden = false
        this.reset()
    }

    addField() {
      // trocar para Combo de tipos de dados
        this.fieldChildForm.field.dataTypeId = this.fieldChildForm.field.typeId
        this.fieldChildForm.field.dataType = 'Texto'

        this.childForm.addField(this.fieldChildForm.field)
        this.reset()
    }

    cancelUpdateField() {
        this.btUpdateHidden = true
        this.btAddHidden = false
        this.reset()
    }

    reset() {
        this.fieldChildForm.typeSelectedValue = '0'
        this.fieldChildForm.field.name = ''
        this.fieldChildForm.field.type = ''
        this.fieldChildForm.field.typeId = ''
        this.fieldChildForm.field.label = ''
        this.fieldChildForm.labelValue.set('')
        this.prevTypeHidden = true
    }

    onChangeType(value: any) {
        this.prevTypeHidden = !(value != '0')
    }

    constructor() {
        super()
        this.route.params.subscribe(params => {
            this.appName = params['appName']
            this.appId   = params['appId']
        });
    }

    ngOnInit(): void {
    }


}



