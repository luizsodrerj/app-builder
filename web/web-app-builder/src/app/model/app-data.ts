import { StatusApp } from "./status-app"


export class App {

    appId!:	string
    name!:   string
    status!: string

    public getStatus(): string {
        let desStatus: string = ''
        if (this.status != undefined && this.status != '') {
            desStatus = parseInt(this.status) == StatusApp.HABILITADA ?
                        StatusApp.DESC_HABILITADA :
                        StatusApp.DESC_DESABILITADA
        } else {
            desStatus = StatusApp.DESC_HABILITADA
        }
        return desStatus
    }

}
