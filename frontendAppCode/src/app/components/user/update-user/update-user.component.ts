import { Component,Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import {Router} from "@angular/router";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.scss']
})

export class UpdateUserComponent implements OnInit {
  @Input() userId: number;
  updateUserForm: FormGroup;
  successMessage: string;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {
    this.updateUserForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
    this.userService.getUserById(this.userId).subscribe(user => {
      this.updateUserForm.patchValue(user);
    });
  }

    onKeyDown(event: KeyboardEvent): void {
    if (event.key === 'Enter' && this.updateUserForm.valid) {
      this.onSubmit();
    } else if (event.key === 'Escape') {
      this.router.navigate(['/users']);
    }
  }

  onSubmit(): void {
    if (this.updateUserForm.valid) {
      const user: User = this.updateUserForm.value;
      this.userService.updateUser(user).subscribe(
        response => {
          console.log('User updated successfully', response);
        },
        error => {
          console.error('Error updating user', error);
        }
      );
    }
  }
}
