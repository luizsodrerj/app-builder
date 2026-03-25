import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexAppForm } from './index-app-form';

describe('IndexAppForm', () => {
  let component: IndexAppForm;
  let fixture: ComponentFixture<IndexAppForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IndexAppForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IndexAppForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
