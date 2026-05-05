import { Component, signal, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';
import { HeaderAppComp } from './header-component/header';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet, MenubarModule, HeaderAppComp
],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
    protected readonly title = signal('Application Builder');

    items: MenuItem[] | undefined;


    constructor(private router: Router) {}

    ngOnInit(): void {
        this.items = [
            {
              label: 'Cadastro de Aplicações',
              routerLink: '/index-app-form',
              icon: 'pi pi-home'
            },
            {
              label: 'Cadastro de Formulários',
              routerLink: '/test-comp',
              icon: 'pi pi-link'
            }
        ]

        this.router.navigate(['/home-form']);
    }

}
