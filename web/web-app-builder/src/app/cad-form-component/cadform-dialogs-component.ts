import { Component, EventEmitter, Output } from "@angular/core";
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'cadform-dialogs-child-component',
  imports: [DialogModule, ButtonModule],
  templateUrl: './cadform-dialogs-component.html'
  //styleUrl: './cad-form-child-component.css',
})
export class CadFormDialogsComponent {

    dlgConfCriaFormVisible = false

    @Output()
    onClickOk = new EventEmitter();


    onClickBtOk() {
        this.onClickOk.emit()
    }

}
