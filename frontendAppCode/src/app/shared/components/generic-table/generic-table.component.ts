import {Component, OnInit, Input, Output, EventEmitter, TemplateRef, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrls: ['./generic-table.component.scss']
})
export class GenericTableComponent implements OnInit {
  @Input() data: any[] = [];
  @Input() columns: string[] = [];
  @Input() columnLabels: string[] = [];
  @Input() specialColumns: SpecialColumn[] = [];
  @Input() customTemplate?: TemplateRef<any>;

  @Input() entityName: string = '';
  @Output() createEntity = new EventEmitter<void>();
  @Output() reloadEntity = new EventEmitter<void>();

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['data']) {
      console.log('Data received in generic-table:', this.data);
    }
  }

  constructor() {
  }

  ngOnInit(): void {
  }

  getColumn(column: string): SpecialColumn | undefined {
    return this.specialColumns.find((specialColumn) => specialColumn.key === column);
  }

  getColumnLabel(column: string): string {
    const index = this.columns.indexOf(column);
    return index >= 0 && index < this.columnLabels.length ? this.columnLabels[index] : column;
  }
}

interface SpecialColumn {
  key: string;
  formatFunction: (value: any) => string;
}

