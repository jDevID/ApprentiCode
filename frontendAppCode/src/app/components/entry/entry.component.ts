import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.scss']
})
export class EntryComponent {

  entryForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.entryForm = this.formBuilder.group({
      command: '',
      response: '',
      // ...
    });
  }

}
