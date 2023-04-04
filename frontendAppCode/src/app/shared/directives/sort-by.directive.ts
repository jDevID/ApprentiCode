import { Directive, EventEmitter, HostListener, Input, Output } from '@angular/core';

export type SortOrder = 'asc' | 'desc';

@Directive({
  selector: '[appSortBy]',
})
export class SortByDirective {
  @Input() appSortBy: string = '';
  @Input() sortOrder: SortOrder = 'asc';
  @Output() sorted = new EventEmitter<{ property: string; order: SortOrder }>();

  @HostListener('click')
  onClick(): void {
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
    this.sorted.emit({ property: this.appSortBy, order: this.sortOrder });
  }
}
