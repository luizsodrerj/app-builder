import { StatusApp } from "./status-app"


export class App {

    appId!:	string
    name!:   string
    status!: string

    public isDisabled() {
        let notNull = this.status != undefined && this.status != ''
        return notNull && this.status == StatusApp.DESABILITADA.toString()
    }

    public isEnabled() {
        let notNull = this.status != undefined && this.status != ''
        let isNull  = this.status == undefined || this.status == ''
        let enabled = notNull && this.status == StatusApp.HABILITADA.toString()

        return isNull || enabled
    }

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
