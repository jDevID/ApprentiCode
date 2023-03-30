import {Component, HostListener} from '@angular/core';
import {ThemeService} from "./services/theme.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontendAppCode';

  constructor(private themeService: ThemeService) {
  }

  toggleDarkMode(): void {
    this.themeService.toggleDarkMode();
  }

  @HostListener('window:keydown', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent): void {
    if (event.key === 'm') {
      // Minimize the window or perform any other action
    }
  }
}
