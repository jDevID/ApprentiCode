import { Directive, ElementRef, Input, OnInit, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appLazyLoadImage]',
})
export class LazyLoadImageDirective implements OnInit {
  @Input() appLazyLoadImage: string | undefined;

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  ngOnInit(): void {
    this.renderer.setStyle(this.el.nativeElement, 'display', 'none');
    this.addIntersectionObserver();
  }

  private addIntersectionObserver(): void {
    if ('IntersectionObserver' in window) {
      const intersectionObserver = new IntersectionObserver(entries => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            this.loadImage();
            intersectionObserver.unobserve(this.el.nativeElement);
          }
        });
      });

      intersectionObserver.observe(this.el.nativeElement);
    } else {
      this.loadImage();
    }
  }

  private loadImage(): void {
    this.renderer.setStyle(this.el.nativeElement, 'display', '');
    if (this.appLazyLoadImage != null) {
      this.renderer.setAttribute(this.el.nativeElement, 'src', this.appLazyLoadImage);
    }
  }
}
