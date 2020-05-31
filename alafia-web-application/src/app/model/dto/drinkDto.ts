import {Order} from "../order";

export class DrinkDto {
  drinkId: string;
  orderId: string;
  clientId: string;
  bookingId: string;
  dinnerTableId: string;
  restaurantId: string;

  constructor(drinkId: string, orderId: string, clientId: string, bookingId: string, dinnerTableId: string, restaurantId: string) {
    this.drinkId = drinkId;
    this.orderId = orderId;
    this.clientId = clientId;
    this.bookingId = bookingId;
    this.dinnerTableId = dinnerTableId;
    this.restaurantId = restaurantId;
  }
}
