import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadAppForm } from './cad-app-form';

describe('CadAppForm', () => {
  let component: CadAppForm;
  let fixture: ComponentFixture<CadAppForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadAppForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadAppForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
