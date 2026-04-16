import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { RouterLink, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { HeaderComponent } from '../header-component/header-component';
import { BaseComponent } from '../base/base-component';
import { AppService } from '../service/app-service';
import { App } from '../model/app-data';

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

    apps: any[] = []

    ngOnInit() {
        this.service.getAllApps().pipe().subscribe({
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
