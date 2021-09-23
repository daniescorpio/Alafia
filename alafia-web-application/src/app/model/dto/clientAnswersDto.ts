export class ClientAnswersDto {
  clientId: string;
  answers: string[];

  constructor(clientId: string, answers: string[]) {
    this.clientId = clientId;
    this.answers = answers;
  }
}
