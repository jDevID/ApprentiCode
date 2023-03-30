import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class WindowService {
  private appContainer: HTMLElement;

  constructor() {}

  registerAppContainer(container: HTMLElement): void {
    this.appContainer = container;
  }

  minimize(): void {
    if (this.appContainer) {
      // Apply CSS classes or styles to minimize the app container
    }
  }

  restore(): void {
    if (this.appContainer) {
      // Apply CSS classes or styles to restore the app container
    }
  }

  // Additional methods for window manipulation...
}
