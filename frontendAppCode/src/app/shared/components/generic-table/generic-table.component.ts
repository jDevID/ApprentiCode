import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.scss']
})
export class GenericTableComponent {
  @Input() data: any[] = [];
  @Input() columns: string[] = [];
  @Input() columnLabels: string[] = [];

  constructor() {}

  ngOnInit(): void {}
}
