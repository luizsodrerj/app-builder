

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

    // DATE/TIME FORMATS

    static dateFormatTypes: any[] = [
      {
        FORMAT_NAME: "Dia/Mes/Ano. Ex.: 01/10/2000",
        MASK:  "dd/mm/yy",
        FORMAT_ID:  100,
        showSeconds : false,
        timeOnly : false,
        showTime : false,
        hourFormat: ''
      },
      {
        FORMAT_NAME: "Mes/Ano. Ex.: 12/2000",
        MASK:  "mm/yy",
        FORMAT_ID:  101,
        showSeconds : false,
        timeOnly : false,
        showTime : false,
        hourFormat: ''
      },
      {
        FORMAT_NAME:  "Dia/Mes/Ano Hora:Min. Ex.: 01/10/2000 12:00",
        MASK:  "dd/mm/yy",
        FORMAT_ID:  102,
        showSeconds : false,
        timeOnly : false,
        showTime : true,
        hourFormat: '24'
      },
      {
        FORMAT_NAME:  "Dia/Mes/Ano Hora:Min:Seg. Ex.: 01/10/2000 12:00:00",
        MASK:  "dd/mm/yy",
        FORMAT_ID:  103,
        showSeconds : true,
        timeOnly : false,
        showTime : true,
        hourFormat: '24'
      },
      {
        FORMAT_NAME:  "Hora:Min:Seg. Ex.: 12:00:00",
        MASK:  "dd/mm/yy",
        FORMAT_ID:  104,
        showSeconds : true,
        timeOnly : true,
        showTime : true,
        hourFormat: '24'
      },
      {
        FORMAT_NAME: "Hora:Min. Ex.: 12:00",
        MASK:  "dd/mm/yy",
        FORMAT_ID:  105,
        showSeconds : false,
        timeOnly : true,
        showTime : true,
        hourFormat: '24'
      }
    ]

    formatTypeId!: number
    formatTypeName!: string
    showSeconds = false
    timeOnly = false
    showTime = false
    hourFormat!: string
    mask!: string

    // <p-datepicker inputId="calendar-24h" [(ngModel)]="datetime24h" [showTime]="true" [hourFormat]="24" />
    // <p-datepicker inputId="calendar-timeonly" [(ngModel)]="time" [timeOnly]="true" />
    // timeOnly - hh:mm
    // [showSeconds]="true" - hh:mm:ss

    public static getDateTimeFormatTypes(): FormatTypes[] {
        let types: FormatTypes[] = []
        FormatTypes.dateFormatTypes.forEach((format:any) => {
            let dateFormat = new FormatTypes
            dateFormat.formatTypeId	  = format.FORMAT_ID
            dateFormat.formatTypeName = format.FORMAT_NAME
            dateFormat.showSeconds    = format.showSeconds
            dateFormat.timeOnly       = format.timeOnly
            dateFormat.hourFormat     = format.hourFormat
            dateFormat.showTime       = format.showTime
            dateFormat.mask           = format.MASK
            types.push(dateFormat)
        })
        return types
    }

    public static getDateTimeFormatType(types:FormatTypes[], typeId:string): FormatTypes {
        let formatId: number = parseInt(typeId)
        let dateFormat!: FormatTypes

        types.forEach((type: FormatTypes) => {
            if (formatId === type.formatTypeId) {
                dateFormat = type
            }
        })
        return dateFormat
    }

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
