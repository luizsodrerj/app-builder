

export class StatusApp {

    public static readonly DESABILITADA: number = 2
    public static readonly HABILITADA: number = 1

    public static readonly DESC_DESABILITADA: string = 'Desabilitada'
    public static readonly DESC_HABILITADA: string = 'Habilitada'

    status!: number
    desStatus!: string


    constructor(paramStatus: number, paramDesStatus: string) {
        this.desStatus  = paramDesStatus
        this.status     = paramStatus
    }

}
