import { Injectable } from '@angular/core';
import { AppConfig } from '../app-config/app-config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FormService {

    private API = `http://localhost:${AppConfig.BACKEND_PORT}/form`;


    constructor(private http: HttpClient) { }


    persistRegister(register: any): Observable<any> {
        let endpoint = `${this.API}/new-register`;
        return this.http.post<any>(endpoint, register);
    }

    getLastRegisters(formId: string): Observable<any> {
        let endpoint = `${this.API}/last-registers/${formId}`;
        return this.http.get<any>(endpoint);
    }

    getForm(id: string): Observable<any> {
        let endpoint = `${this.API}/${id}`;
        return this.http.get<any>(endpoint);
    }


}
