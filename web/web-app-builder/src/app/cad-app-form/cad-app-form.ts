import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { HeaderComponent } from '../header-component/header-component';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink } from "@angular/router";
import { BaseComponent } from '../base/base-component';
import { AppService } from '../service/app-service';
import { App } from '../model/app-data';
import { DialogModule } from 'primeng/dialog';

@Component({
  selector: 'app-cad-app-form',
  imports: [
    HeaderComponent, InputTextModule,
    FormsModule, ButtonModule,
    RouterLink, DialogModule
],
  templateUrl: './cad-app-form.html',
  styleUrl: './cad-app-form.css',
  providers: [AppService]
})
export class CadAppForm extends BaseComponent {
    btNewAppLabel: string = 'Salvar'
    disableBtCadForm: boolean = false
    forms: any[] = []

    dlgVisible: boolean = false
    msgDialog: string = ''

    app = {
      nome: '',
      id: '0'
    }
    private route: ActivatedRoute = inject(ActivatedRoute)
    private service: AppService = inject(AppService)
    private cdr: ChangeDetectorRef = inject(ChangeDetectorRef)


    onClickBtSave() {
        if (this.validateDadosApp()) {
            this.saveApp()
        }
    }

    validateDadosApp(): boolean {
        let ok = true

        if (this.app.nome.trim() == '') {
            this.msgDialog  = 'É obrigatório informar o Nome da Aplicação'
            this.dlgVisible = true
            ok = false
        } else {
            if (this.forms.length == 0) {
                this.msgDialog = "Para salvar uma Aplicação é necessário possuir pelo menos um " +
                                 "Formulário ou Cadastro. Clique no botão 'Criar um Formulário de Cadastro' " +
                                 "para criar seu primeiro Cadastro"
                this.dlgVisible = true
                ok = false
            }
        }
        return ok
    }

    saveApp() {
        let app: App = new App
        app.appId = this.app.id
        app.name  = this.app.nome

        this.service.update(app).pipe().subscribe({
          next: (appData) => {
              this.msgDialog  = 'Aplicação salva com sucesso.'
              this.dlgVisible = true
              this.cdr.detectChanges()
          }
        })
    }

    constructor() {
        super()
        this.route.params.subscribe(params => {
            this.app.id = params['appId']
            if (params['appId'] != undefined && params['appId'] != '0') {
                this.service.getAppById(this.app.id).pipe().subscribe({
                  next: (appData) => {
                    this.app.id   = appData.appId
                    this.app.nome = appData.name
                    this.forms    = appData.forms
                    this.btNewAppLabel = 'Salvar'
                    this.cdr.detectChanges()
                  }
                })
            }
        });
    }

}
