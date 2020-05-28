import {Order} from './order';

export class Client {
  id: string;
  name: string;
  mail: string;
  order: Order;

  constructor(id: string, name: string, mail: string, order: Order) {
    this.id = id;
    this.name = name;
    this.mail = mail;
    this.order = order;
  }
}
