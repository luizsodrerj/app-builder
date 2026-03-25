import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { providePrimeNG } from 'primeng/config';
import { routes } from './app.routes';
import Nora from '@primeuix/themes/nora';
import { DatePipe } from '@angular/common';

export const appConfig: ApplicationConfig = {
  providers: [
    DatePipe,
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes)
    ,providePrimeNG({
            theme: {
                preset: Nora
            },
        })
  ]

/*
 */
};
