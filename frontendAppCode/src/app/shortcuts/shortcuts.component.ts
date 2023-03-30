import {Component, HostListener} from '@angular/core';

@Component({
  selector: 'app-shortcuts',
  templateUrl: './shortcuts.component.html',
  styleUrls: ['./shortcuts.component.scss']
})
export class ShortcutsComponent {
  @HostListener('window:keydown.control.shift.x', ['$event'])
  minimizeWindow(event: KeyboardEvent): void {
    event.preventDefault();
    // Your logic to minimize the window or perform other actions
  }

  @HostListener('window:keydown', ['$event'])
  onKeyDown(event: KeyboardEvent): void {
    if (event.ctrlKey && event.key === 'KeyX') {
      // Your action for the keybinding
    }
  }
}
