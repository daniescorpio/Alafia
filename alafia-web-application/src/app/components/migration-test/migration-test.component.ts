import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Client} from '../../model/client';
import {DataService} from '../../services/data.service';
import {ClientAnswersDto} from '../../model/dto/clientAnswersDto';

@Component({
  selector: 'app-migration-test',
  templateUrl: './migration-test.component.html',
  styleUrls: ['./migration-test.component.css']
})
export class MigrationTestComponent implements OnInit {

  answer1: string;
  answer2: string;
  answer3: string;
  answer4: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: Client,
              public dialogRef: MatDialogRef<MigrationTestComponent>,
              public dataService: DataService) {
  }

  ngOnInit(): void {
    this.inicializeAnswers();
  }

  private inicializeAnswers() {
    this.answer1 = '';
    this.answer2 = '';
    this.answer3 = '';
    this.answer4 = '';
  }

  submitAnswers(): void {
    console.log('to send: ' +
      '  Answer 1: ' + this.answer1 +
      ', Answer 2: ' + this.answer2 +
      ', Answer 3: ' + this.answer3 +
      ', Answer 4: ' + this.answer4);
    let answers = [this.answer1, this.answer2, this.answer3, this.answer4];
    let clientDto: ClientAnswersDto = new ClientAnswersDto(this.data.id, answers);
    this.dataService.postClientAnswers(clientDto);
    this.dialogRef.close();
  }

  backToMenu(): void {
    this.dialogRef.close();
  }

}
