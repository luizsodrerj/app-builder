import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button'
import { RouterLink } from '@angular/router';

@Component({
  selector: 'home-form',
  imports: [ButtonModule, RouterLink],
  templateUrl: './home-form.html',
  styleUrl: './home-form.css',
})
export class HomeForm {

}
