import { Component, signal, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenubarModule } from 'primeng/menubar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet, MenubarModule
],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
    protected readonly title = signal('Application Builder');

    constructor(private router: Router) {}

    ngOnInit(): void {
        this.router.navigate(['/home-form']);
    }

}
