export class UpdateTableDto {
  dinnerTableId: string;
  restaurantId: string;

  constructor(dinnerTableId: string, restaurantId: string) {
    this.dinnerTableId = dinnerTableId;
    this.restaurantId = restaurantId;
  }
}
