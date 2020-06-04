import {Order} from './order';

export class Client {
  id: string;
  bookingId: string;
  name: string;
  mail: string;
  order: Order;
  confirmed: boolean;

  constructor(id: string, bookingId: string, name: string, mail: string, order: Order) {
    this.id = id;
    this.bookingId = bookingId;
    this.name = name;
    this.mail = mail;
    this.order = order;
    this.confirmed = false;
  }
}
