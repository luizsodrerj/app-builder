import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { RouterLink, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { HeaderComponent } from '../header-component/header-component';
import { BaseComponent } from '../base/base-component';
import { AppService } from '../service/app-service';
import { App } from '../model/app-data';
import { StatusApp } from '../model/status-app';

@Component({
  selector: 'app-config-app-component',
  imports: [HeaderComponent, ButtonModule, RouterModule],
  templateUrl: './config-app-component.html',
  styleUrl: './config-app-component.css',
  providers: [AppService]
})
export class ConfigAppComponent extends BaseComponent implements OnInit {
    private service: AppService  = inject(AppService)
    private cdr: ChangeDetectorRef = inject(ChangeDetectorRef)

    apps: App[] = []

    public onClickBtDisableApp(appId: string) {
        this.changeStatus(appId,StatusApp.DESABILITADA)
    }

    public onClickBtEnableApp(appId: string) {
        this.changeStatus(appId,StatusApp.HABILITADA)
    }

    public changeStatus(appId: string, status: number) {
        let app: App = new App
        app.status = status.toString()
        app.appId  = appId

        this.service.changeStatus(app).pipe().subscribe({
            next: (data) => {
              this.apps = []
              this.ngOnInit()
              this.cdr.detectChanges()
            }
          })
    }

    ngOnInit() {
        this.service.getAllWithAnyStatus().pipe().subscribe({
            next: (data) => {
              data.forEach((appData: any) => {
                  let app: App = new App
                  app.appId = appData.appId
                  app.status= appData.status
                  app.name  = appData.name
                  this.apps.push(app)
              });
              this.cdr.detectChanges()
            }
        })
    }

}
