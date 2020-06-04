import {Order} from "../order";

export class ClientDto {
  name: string;
  mail: string;
  oldClientId: string;
  bookingId: string;
  dinnerTableId: string;
  restaurantId: string;

  constructor(name: string, mail: string, oldClientId: string, bookingId: string, dinnerTableId: string, restaurantId: string) {
    this.name = name;
    this.mail = mail;
    this.oldClientId = oldClientId;
    this.bookingId = bookingId;
    this.dinnerTableId = dinnerTableId;
    this.restaurantId = restaurantId;
  }
}
