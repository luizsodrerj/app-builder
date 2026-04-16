import { DataTypes } from "./data-types"

export class FieldData {

    id: string = ''
    name: string = ''
    dataType: string = ''
    label: string = ''
    fieldType: string = ''
    formatType: string = ''
    value: string = ''


    isTypeOfMonetaryData(): boolean {
         return this.dataType != '' &&
                this.dataType == DataTypes.MONETARY
    }

}
