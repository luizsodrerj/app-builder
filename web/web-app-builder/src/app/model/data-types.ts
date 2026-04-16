import { FormatTypes } from "./format-types"

export class DataTypes {

    public static readonly TEXT: string = '1'
    public static readonly MONETARY: string = '2'
    public static readonly DATE_TIME: string = '3'

    dataTypeName!: string
    dataTypeId!: string
    hasDefaultMask: boolean = false
    defaultMaskId!: string
    defaultMask!: string

    public static typesIds = [
        DataTypes.TEXT,
        DataTypes.MONETARY,
        DataTypes.DATE_TIME
    ]

    public static isMonetaryDataType(typeId: string): boolean {
        return typeId == DataTypes.MONETARY
    }

    public isMonetary(): boolean {
        return this.dataTypeId == DataTypes.MONETARY
    }

    public getDataTypeName(): string {
        let name = ''
        switch(this.dataTypeId) {
            case DataTypes.TEXT:
                name = 'Texto'
                break
            case DataTypes.MONETARY:
                name = 'Valor Monetário'
                break
            case DataTypes.DATE_TIME:
                name = 'Data/Hora'
                break
            default:
              name = 'Tipo Inválido'
        }
        return name
    }

    public static getDataType(dataTypes:DataTypes[], typeId:string) {
        let dataType!: DataTypes
        dataTypes.forEach((type:DataTypes) => {
            if (type.dataTypeId == typeId) {
                dataType = type
            }
        });
        return dataType;
    }

    public static getDataTypesList() {
        let list: DataTypes[] = []
        DataTypes.typesIds.forEach((typeId:string) => {
            let type: DataTypes = new DataTypes
            type.dataTypeId     = typeId
            type.hasDefaultMask = typeId == DataTypes.MONETARY ? true : false
            type.defaultMaskId  = typeId == DataTypes.MONETARY ? FormatTypes.MONETARY.toString() : ""
            type.defaultMask    = typeId == DataTypes.MONETARY ? FormatTypes.MONETARY_MASK : ""
            list.push(type)
        })
        return list
    }



}
