import {Booking} from './booking';

export class DinnerTable {
  id: string;
  booking: Booking;

  constructor(id: string, booking: Booking) {
    this.id = id;
    this.booking = booking;
  }
}
