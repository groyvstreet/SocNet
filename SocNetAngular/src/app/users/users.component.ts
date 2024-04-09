import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import User from '../entities/user';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
  users: User[] = [];

  constructor(private userService: UserService) {}

  async ngOnInit() {
    this.users = await this.userService.getUsers();
  }
}
