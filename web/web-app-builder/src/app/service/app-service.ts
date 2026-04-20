import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { AppConfig } from '../app-config/app-config';
import { Observable } from 'rxjs';
import { App } from '../model/app-data';

@Injectable({
  providedIn: 'root',
})
export class AppService {

    private API = `http://localhost:${AppConfig.BACKEND_PORT}/application`;


    constructor(private http: HttpClient) { }



    update(app: App): Observable<any> {
        let endpoint = `${this.API}/update`;
        return this.http.put<any>(endpoint, app);
    }

    changeStatus(app: App): Observable<any> {
        let endpoint = `${this.API}/change-status`;
        return this.http.put<any>(endpoint, app);
    }

    saveAppForm(app: any): Observable<any> {
        let endpoint = `${this.API}/create-app-form`;
        return this.http.post<any>(endpoint, app);
    }

    saveAppAndForm(app: any): Observable<any> {
        let endpoint = `${this.API}/new-app`;
        return this.http.post<any>(endpoint, app);
    }

    getForms(appId: string): Observable<any> {
        let endpoint = `${this.API}/forms?appId=${appId}`;
        return this.http.get<any>(endpoint);
    }

    getAllWithAnyStatus(): Observable<any> {
        let endpoint = `${this.API}/all-with-any-status`;
        return this.http.get<any>(endpoint);
    }

    getAllApps(): Observable<any> {
        let endpoint = `${this.API}/all`;
        return this.http.get<any>(endpoint);
    }

    getAppById(appId: string): Observable<any> {
        let endpoint = `${this.API}/app/${appId}`;
        return this.http.get<any>(endpoint);
    }


}

