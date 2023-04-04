import { Directive, ElementRef, EventEmitter, HostListener, Output } from '@angular/core';

@Directive({
  selector: '[appInfiniteScroll]',
})
export class InfiniteScrollDirective {
  @Output() scrolled = new EventEmitter<void>();

  constructor(private elementRef: ElementRef) {}

  @HostListener('scroll', ['$event'])
  onScroll(event: Event): void {
    const scrollHeight = this.elementRef.nativeElement.scrollHeight;
    const scrollTop = this.elementRef.nativeElement.scrollTop;
    const clientHeight = this.elementRef.nativeElement.clientHeight;

    if (scrollTop + clientHeight >= scrollHeight) {
      this.scrolled.emit();
    }
  }
}
