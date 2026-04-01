import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { AppConfig } from '../app-config/app-config';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AppService {

    private API = `http://localhost:${AppConfig.BACKEND_PORT}/application`;


    constructor(private http: HttpClient) { }


    saveAppAndForm(app: any): Observable<any> {
        let endpoint = `${this.API}/new-app`;
        return this.http.post<any>(endpoint, app);
    }

    getForms(appId: string): Observable<any> {
        let endpoint = `${this.API}/forms?appId=${appId}`;
        return this.http.get<any>(endpoint);
    }

    getAllApps(): Observable<any> {
        let endpoint = `${this.API}/all`;
        return this.http.get<any>(endpoint);
    }



}

