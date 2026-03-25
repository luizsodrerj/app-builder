import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'header-component',
  imports: [MenubarModule],
  templateUrl: './header-component.html',
  styleUrl: './header-component.css',
})
export class HeaderComponent implements OnInit {
    items: MenuItem[] | undefined;

    private router = inject(Router);



    ngOnInit(): void {
        this.items = [
            {
              label: 'Cadastro de Aplicações',
              routerLink: '/index-app-form',
              icon: 'pi pi-home'
            },
            {
              label: 'Cadastro de Formulários',
              routerLink: '/filiais-form',
              icon: 'pi pi-link'
            }
        ]
    }

}
