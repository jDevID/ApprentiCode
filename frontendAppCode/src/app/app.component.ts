import {Component, HostListener, OnInit} from '@angular/core';
import {ThemeService} from "./services/theme.service";
import { WindowService } from './services/window.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(private windowService: WindowService, private themeService: ThemeService) {}
  title = 'frontendAppCode';

  ngOnInit(): void {
    this.windowService.registerAppContainer(document.getElementById('app-container'));
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
