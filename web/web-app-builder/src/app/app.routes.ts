import { Routes } from '@angular/router';
import { IndexAppForm } from './index-app-form/index-app-form';
import { HomeForm } from './home-form/home-form';
import { CadAppForm } from './cad-app-form/cad-app-form';
import { CadFormComponent } from './cad-form-component/cad-form-component';
import { ListAppComponent } from './index-app-form/list-app-component';
import { ListFormComponent } from './cad-form-component/list-form-component';
import { CadDataFormComponent } from './cad-data-form-component/cad-data-form-component';
import { ConfigAppComponent } from './config-app-component/config-app-component';

export const routes: Routes = [

    { path: 'list-form/:appId', component: ListFormComponent },
    { path: 'index-app-form', component: IndexAppForm },
    { path: 'home-form', component: HomeForm },
    { path: 'cad-app-form', component: CadAppForm },
    { path: 'cad-form/:appId/:appName', component: CadFormComponent },
    { path: 'list-app', component: ListAppComponent },
    { path: 'cad-data-form/:formId', component: CadDataFormComponent },
    { path: 'conf-app', component: ConfigAppComponent }


];
