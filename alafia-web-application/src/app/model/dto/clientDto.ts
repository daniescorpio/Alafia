import {Order} from "../order";

export class ClientDto {
  name: string;
  mail: string;
  bookingId: string;
  dinnerTableId: string;
  restaurantId: string;

  constructor(name: string, mail: string, bookingId: string, dinnerTableId: string, restaurantId: string) {
    this.name = name;
    this.mail = mail;
    this.bookingId = bookingId;
    this.dinnerTableId = dinnerTableId;
    this.restaurantId = restaurantId;
  }
}
