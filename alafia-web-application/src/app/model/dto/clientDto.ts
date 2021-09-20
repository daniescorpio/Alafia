export class ClientDto {
  name: string;
  mail: string;
  oldClientId: string;
  bookingId: string;
  dinnerTableId: string;
  restaurantId: string;
  confirmed: boolean;

  constructor(name: string, mail: string, oldClientId: string, bookingId: string, dinnerTableId: string, restaurantId: string, confirmed: boolean) {
    this.name = name;
    this.mail = mail;
    this.oldClientId = oldClientId;
    this.bookingId = bookingId;
    this.dinnerTableId = dinnerTableId;
    this.restaurantId = restaurantId;
    this.confirmed = confirmed;
  }
}
