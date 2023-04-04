import { Directive, ElementRef, OnInit } from '@angular/core';

@Directive({
  selector: '[appAutofocus]',
})
export class AutofocusDirective implements OnInit {
  constructor(private el: ElementRef) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.el.nativeElement.focus();
    }, 100);
  }
}
