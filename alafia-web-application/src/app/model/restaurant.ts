import {Table} from './table';

export class Restaurant {
  tables: Table[];

  constructor(tables: Table[]) {
    this.tables = tables;
  }
}
