import { Component } from '@angular/core';
import { HeaderComponent } from '../header-component/header-component';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { RouterLink } from "@angular/router";
import { BaseComponent } from '../base/base-component';

@Component({
  selector: 'app-cad-app-form',
  imports: [
    HeaderComponent, InputTextModule,
    FormsModule, ButtonModule,
    RouterLink
],
  templateUrl: './cad-app-form.html',
  styleUrl: './cad-app-form.css',
})
export class CadAppForm extends BaseComponent {

    disableBtCadForm: boolean = false

    app = {
      nome: '',
      id: '0'
    }


}
