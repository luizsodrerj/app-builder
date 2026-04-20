import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "../header-component/header-component";
import { ButtonModule } from 'primeng/button';
import { RouterLink } from "@angular/router";
import { BaseComponent } from '../base/base-component';

@Component({
  selector: 'index-app-form',
  imports: [HeaderComponent, ButtonModule, RouterLink],
  templateUrl: './index-app-form.html',
  styleUrl: './index-app-form.css',
})
export class IndexAppForm extends BaseComponent implements OnInit {

    appOptions:any[] = [
      {
        title: 'Criar Aplicação',
        text: 'Clique no botão abaixo para Criar uma Nova Aplicação',
        link: '/cad-app-form',
        btLabel: 'Criar uma Nova Aplicação',
        hasParameter: true,
        param: '0',
        icon: 'pi pi-home'
      },
      {
        title: 'Configurar Aplicação',
        text: 'Clique no botão abaixo para Configurar uma Aplicação Existente',
        link: '/conf-app',
        btLabel: 'Configurar uma Aplicação',
        hasParameter: false,
        param: '',
        icon: 'pi pi-check'
      },
      {
        title: 'Iniciar Aplicação',
        text: 'Clique no botão abaixo para Iniciar uma Aplicação',
        link: '/list-app',
        btLabel: 'Iniciar uma Aplicação',
        hasParameter: false,
        param: '',
        icon: 'pi pi-sitemap'
      }
    ]

    ngOnInit() {
    }


}
