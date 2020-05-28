import {Client} from './client';
import {Booking} from './booking';

export class Table {
  id: string;
  booking: Booking;

  constructor(id: string, booking: Booking, dinners: Client[]) {
    this.id = id;
    this.booking = booking;
  }
}
