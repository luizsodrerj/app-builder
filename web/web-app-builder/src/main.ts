import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { App } from './app/app';
import { HomeForm } from './app/home-form/home-form';

bootstrapApplication(App, appConfig)
  .catch((err) => console.error(err));
