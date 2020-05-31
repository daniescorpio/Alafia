export class Course {
  id: string;
  description: string;
  price: number;
  nutritionalValues: string;
  obtainProcess: string;
  history: string;
  served: boolean;

  constructor(id: string, description: string, price: number, nutritionalValues: string, obtainProcess: string, history: string) {
    this.id = id;
    this.description = description;
    this.price = price;
    this.nutritionalValues = nutritionalValues;
    this.obtainProcess = obtainProcess;
    this.history = history;
    this.served = false;
  }
}
