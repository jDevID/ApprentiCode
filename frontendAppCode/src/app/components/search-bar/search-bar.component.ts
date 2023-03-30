import {Component, EventEmitter, OnInit, Output, HostListener} from '@angular/core';
import {FormControl} from '@angular/forms';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit {
  searchControl = new FormControl();

  @Output() search = new EventEmitter<string>();
  searchQuery = '';
  onSearch(): void {
    this.search.emit(this.searchQuery);
  }

  constructor() {
    this.searchControl.valueChanges
      .pipe(debounceTime(300), distinctUntilChanged())
      .subscribe((query) => {
        // Call your search API with the query
      });
  }

  @HostListener('window:keydown.enter', ['$event'])
  onEnterKeyDown(event: KeyboardEvent): void {
    event.preventDefault();
    // Call your search function here
  }
  ngOnInit(): void {
  }
}
