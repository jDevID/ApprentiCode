import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from '../../models/user';
import {UserService} from '../../services/user.service';
// create forms, validation
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from "@angular/router";
// pagination
import {MatPaginator} from '@angular/material/paginator';
// sorting
import { MatSort } from '@angular/material/sort';



@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  users: User[] = [];
  displayedColumns: string[] = ['id', /* Add other column names as needed */];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit(): void {
    this.paginator.page.subscribe(() => {
      this.getUsers();
    });
  }



  getUsers(filterValue?: string): void {
    const pageIndex = this.paginator.pageIndex;
    const pageSize = this.paginator.pageSize;
    this.userService.getUsers(pageIndex, pageSize).subscribe((users) => {
      this.users = users;
    });
  }

  ngOnInit(): void {
    this.userService.getUsers(pageIndex, pageSize).subscribe((users) => {
      this.users = users;
    });
  }

  applyFilter(filterValue: string): void {
    this.getUsers(filterValue);
  }
  userForm: FormGroup;

  constructor(private router: Router, private userService: UserService, private formBuilder: FormBuilder) {
    this.userForm = this.formBuilder.group({
      // Add form controls with their validators
      active: [true, Validators.required],
      // Add the rest of the fields from the User entity
    });
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      this.userService.addUser(this.userForm.value).subscribe((user) => {
        // Add the new user to the users array
        this.users.push(user);
        // Reset the form
        this.userForm.reset();
      });
    }
  }

// Inside the UserComponent class
this.userForm = this.formBuilder.group({
  active: [true, Validators.required],
  // Add the rest of the fields from the User entity with their validators
  email: ['', [Validators.required, Validators.email]],
  // ...
});

  navigateToUpdateUser(userId: number): void {
    this.router.navigate([`/user/update/${userId}`]);
  }
}
