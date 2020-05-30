import {DinnerTable} from './dinnerTable';

export class Restaurant {
  id: string;
  dinnerTables: DinnerTable[];

  constructor(tables: DinnerTable[]) {
    this.dinnerTables = tables;
  }
}
