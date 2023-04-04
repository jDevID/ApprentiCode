import {Directive, EventEmitter, HostListener, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Subject, Subscription} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Directive({
  selector: '[appDebounceClick]',
})
export class DebounceClickDirective implements OnInit, OnDestroy {
  @Input() debounceTime = 300;
  @Output() debounceClick = new EventEmitter();
  private clicks = new Subject();
  private subscription: Subscription = new Subscription();

  ngOnInit(): void {
    this.subscription = this.clicks.pipe(debounceTime(this.debounceTime)).subscribe((e) => this.debounceClick.emit(e));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  @HostListener('click', ['$event'])
  clickEvent(event: Event): void {
    event.preventDefault();
    event.stopPropagation();
    this.clicks.next(event);
  }
}
