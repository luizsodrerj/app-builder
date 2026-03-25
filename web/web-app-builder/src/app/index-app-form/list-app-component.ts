import { AppService } from './../service/app-service';
import { HeaderComponent } from "../header-component/header-component";
import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { BaseComponent } from '../base/base-component';
import { ButtonModule } from 'primeng/button';
import { RouterLink, RouterModule } from '@angular/router';


@Component({
  selector: 'list-app-component',
  imports: [HeaderComponent, ButtonModule, RouterModule, RouterLink],
  templateUrl: './list-app-component.html',
  styleUrl: './list-app-component.css',
  providers: [AppService]
})
export class ListAppComponent extends BaseComponent implements OnInit {

    private service: AppService  = inject(AppService)
    private cdr: ChangeDetectorRef = inject(ChangeDetectorRef)

    apps: any[] = []

    ngOnInit() {
        this.service.getAllApps().pipe().subscribe({
            next: (data) => {
              this.apps = data;
              this.cdr.detectChanges()
            }
        })

    }


}
