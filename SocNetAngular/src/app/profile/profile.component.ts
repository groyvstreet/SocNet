import { Component } from '@angular/core';
import { AuthorizationService } from '../services/authorization.service';
import { FormsModule } from '@angular/forms';
import { UserService } from '../services/user.service';
import User from '../entities/user';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  firstName = '';
  lastName = '';
  birthDate = '';
  user = new User;
  
  constructor(private authorizationService: AuthorizationService,
    private userService: UserService) {
    authorizationService.user.subscribe(user => {
      this.user = user;
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.birthDate = user.birthDate;
    });
  }

  logOut() {
    this.authorizationService.logOut();
    window.location.href = '/signin';
  }

  async updateUser() {
    await this.userService.updateUser(this.user.id, this.firstName, this.lastName, this.birthDate, this.user.image);
    const user = new User;
    user.id = this.user.id;
    user.firstName = this.firstName;
    user.lastName = this.lastName;
    user.birthDate = this.birthDate;
    user.image = this.user.image;
    this.authorizationService.setUser(user);
    alert('Обновлено')
  }

  async removeUser() {
    await this.userService.removeUserById(this.user.id);
    this.logOut();
  }
}
