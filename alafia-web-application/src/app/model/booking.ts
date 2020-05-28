import {Client} from './client';

export class Booking {
  id: string;
  client: Client;
  diners: Client[];

  constructor(id: string, client: Client, diners: Client[]) {
    this.id = id;
    this.client = client;
    this.diners = diners;
  }
}
