import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadDataFormComponent } from './cad-data-form-component';

describe('CadDataFormComponent', () => {
  let component: CadDataFormComponent;
  let fixture: ComponentFixture<CadDataFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadDataFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadDataFormComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
