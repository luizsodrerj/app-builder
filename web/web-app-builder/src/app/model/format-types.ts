

export class FormatTypes {

    public static readonly MONETARY_MASK: string = "separator.2"
    public static readonly MONETARY: number = 0
    public static readonly CPF_MASK: string = "000000000-00"
    public static readonly CPF: number = 1
    public static readonly CNPJ_MASK: string = "00.000.000/0000-00"
    public static readonly CNPJ: number = 2
    public static readonly CELULAR_MASK: string = "(00)00000-0000"
    public static readonly CELULAR: number = 3
    public static readonly CEP_MASK: string = "00000-000"
    public static readonly CEP: number = 4

    formatTypeId!: number
    formatTypeName!: string
    mask!: string

    public getFormatTypeName(): string {
        let formatName: string = ''
        switch(this.formatTypeId) {
            case FormatTypes.MONETARY:
              formatName = 'Valor Monetário'
              break
            case FormatTypes.CPF:
              formatName = 'CPF'
              break
            case FormatTypes.CNPJ:
              formatName = 'CNPJ'
              break
            case FormatTypes.CELULAR:
              formatName = 'Telefone Celular'
              break
            case FormatTypes.CEP:
              formatName = 'CEP'
              break
            default:
              formatName = ''
        }
        return formatName
    }

    public getMask(): string {
        let mask: string = ''
        switch(this.formatTypeId) {
            case FormatTypes.MONETARY:
              mask = FormatTypes.MONETARY_MASK
              break
            case FormatTypes.CPF:
              mask = FormatTypes.CPF_MASK
              break
            case FormatTypes.CNPJ:
              mask = FormatTypes.CNPJ_MASK
              break
            case FormatTypes.CELULAR:
              mask = FormatTypes.CELULAR_MASK
              break
            case FormatTypes.CEP:
              mask = FormatTypes.CEP_MASK
              break
            default:
              mask = ''
        }
        return mask
    }

    public static getFormatTypesList(): FormatTypes[] {
        let list:FormatTypes[] = []
        let ids: number[] = [
            FormatTypes.MONETARY,
            FormatTypes.CPF		,
            FormatTypes.CNPJ	,
            FormatTypes.CELULAR	,
            FormatTypes.CEP
          ]
        ids.forEach((typeId:number) => {
            let formatType = new FormatTypes
            formatType.formatTypeId = typeId
            list.push(formatType)
        })
        return list
    }

}
