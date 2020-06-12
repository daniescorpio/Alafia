import {Booking} from './booking';
import {BehaviorSubject} from "rxjs";

export class DinnerTable {
  id: string;
  booking: Booking;
  allDinersConfirmed: boolean;

  constructor(id: string, booking: Booking) {
    this.id = id;
    this.booking = booking;
    this.allDinersConfirmed = true;
  }
}
