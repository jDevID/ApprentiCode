import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { GenericTableComponent } from './components/generic-table/generic-table.component';
import { AutofocusDirective } from './directives/autofocus.directive';
import {TruncatePipe} from "./pipes/truncate.pipe";
import {DebounceClickDirective} from "./directives/debounce-click.directive";
import {FileSizePipe} from "./pipes/file-size.pipe";
import {SortByDirective} from "./directives/sort-by.directive";
import { LoadingSpinnerComponent } from './components/loading-spinner/loading-spinner.component';
import {DatePipe} from "./pipes/date.pipe";
import {SafeHtmlPipe} from "./pipes/safe-html.pipe";
import {ClickOutsideDirective} from "./directives/click-outside.directive";
import {InfiniteScrollDirective} from "./directives/infinite-scroll.directive";
import {LazyLoadImageDirective} from "./directives/lazy-load-image.directive";
import {SearchFilterPipe} from "./pipes/search-filter.pipe";
import {MatTableModule} from "@angular/material/table";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import { PaginatorComponent } from './components/paginator/paginator.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import { NavigationComponent } from './components/navigation/navigation.component';
import {RouterLink, RouterLinkActive} from "@angular/router";
import { ActionButtonsComponent } from './components/action-buttons/action-buttons.component';
@NgModule({
  declarations: [
    // Declare components directives pipes
    GenericTableComponent,
    AutofocusDirective,
    TruncatePipe,
    DebounceClickDirective,
    FileSizePipe,
    SortByDirective,
    LoadingSpinnerComponent,
    DatePipe,
    SafeHtmlPipe,
    ClickOutsideDirective,
    InfiniteScrollDirective,
    LazyLoadImageDirective,
    SearchFilterPipe,
    PaginatorComponent,
    NavigationComponent,
    ActionButtonsComponent,

  ],
  imports: [
    CommonModule,
    MatButtonModule,
    MatInputModule,
    MatToolbarModule,
    MatSidenavModule,
    MatTableModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    RouterLink,
    RouterLinkActive,

  ],
    exports: [
        CommonModule,
        MatButtonModule,
        MatInputModule,
        MatToolbarModule,
        MatSidenavModule,
// Export components directives pipes
        GenericTableComponent,
        AutofocusDirective,
        TruncatePipe,
        DebounceClickDirective,
        FileSizePipe,
        SortByDirective,
        LoadingSpinnerComponent,
        DatePipe,
        SafeHtmlPipe,
        ClickOutsideDirective,
        InfiniteScrollDirective,
        LazyLoadImageDirective,
        SearchFilterPipe,
        MatProgressSpinnerModule,
        PaginatorComponent,
        NavigationComponent,
        ActionButtonsComponent,
    ]
})
export class SharedModule { }
