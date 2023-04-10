import {Component, Input, OnInit} from '@angular/core';
import { ActionButtons } from '../../../core/models/action-buttons.interface';
@Component({
  selector: 'app-action-buttons',
  templateUrl: './action-buttons.component.html',
  styleUrls: ['./action-buttons.component.scss']
})

export class ActionButtonsComponent implements OnInit {
  @Input() buttons: ActionButtons[] = [];

  ngOnInit(): void {
  }
}
