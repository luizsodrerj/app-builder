import { AppService } from '../service/app-service';
import { HeaderComponent } from "../header-component/header-component";
import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { BaseComponent } from '../base/base-component';
import { ButtonModule } from 'primeng/button';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-list-app-component',
  imports: [HeaderComponent, ButtonModule, RouterLink],
  templateUrl: './list-form-component.html',
  styleUrl: './list-form-component.css',
  providers: [AppService]
})
export class ListFormComponent extends BaseComponent implements OnInit {

    private service: AppService  = inject(AppService)
    private cdr: ChangeDetectorRef = inject(ChangeDetectorRef)

    forms: any[] = []

    ngOnInit() {
    }

    constructor(private route: ActivatedRoute) {
        super()
        this.route.params.subscribe(params => {
            let appId = params['appId']
            this.service.getForms(appId).pipe().subscribe({
                next: (data) => {
                  this.forms = data;
                  this.cdr.detectChanges()
                }
            })
        });
    }

}
