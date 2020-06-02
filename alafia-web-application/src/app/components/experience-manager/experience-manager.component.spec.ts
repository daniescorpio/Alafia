import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExperienceManagerComponent } from './experience-manager.component';

describe('ExperienceManagerComponent', () => {
  let component: ExperienceManagerComponent;
  let fixture: ComponentFixture<ExperienceManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExperienceManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExperienceManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
