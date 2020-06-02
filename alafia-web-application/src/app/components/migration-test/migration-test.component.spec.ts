import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MigrationTestComponent } from './migration-test.component';

describe('MigrationTestComponent', () => {
  let component: MigrationTestComponent;
  let fixture: ComponentFixture<MigrationTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MigrationTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MigrationTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
