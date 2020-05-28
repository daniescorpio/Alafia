export class Drink {
  id: string;
  description: string;
  price: number;

  constructor(id: string, description: string, price: number) {
    this.id = id;
    this.description = description;
    this.price = price;
  }
}
