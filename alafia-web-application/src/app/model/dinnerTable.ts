import {Booking} from './booking';

export class DinnerTable {
  id: string;
  booking: Booking;
  allDinersConfirmed: boolean;
  activeNotification: boolean;

  constructor(id: string, booking: Booking) {
    this.id = id;
    this.booking = booking;
    this.allDinersConfirmed = true;
    this.activeNotification = false;
  }
}
