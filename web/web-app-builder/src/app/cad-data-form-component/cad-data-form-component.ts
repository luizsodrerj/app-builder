import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { BaseComponent } from '../base/base-component';
import { HeaderComponent } from '../header-component/header-component';
import { ActivatedRoute } from '@angular/router';
import { FormService } from '../service/form-service';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'cad-data-form-component',
  imports: [
    HeaderComponent, FormsModule,
    TableModule
  ],
  templateUrl: './cad-data-form-component.html',
  styleUrl: './cad-data-form-component.css',
  providers: [FormService]
})
export class CadDataFormComponent extends BaseComponent implements OnInit {

    fields: any[] = []
    formRegisters: any[] = []

    form = {
      name: '',
      label: '',
      id: ''
    }
    private cdr: ChangeDetectorRef  = inject(ChangeDetectorRef)
    private route: ActivatedRoute   = inject(ActivatedRoute)
    private service: FormService    = inject(FormService)


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

    populateFields(fieldsData: any) {
      fieldsData.forEach((data:any) => {
          this.fields.push({
            id        : data.id,
            name  	  : data.name,
            dataType 	: data.dataTypeId,
            label 	  : data.label,
            fieldType	: data.typeId,
            formatType: '',
            value: ''
          })
      });
    }

    ngOnInit(): void {
    }

}
