import { Component } from '@angular/core';
import { AuthorizationService } from '../services/authorization.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  email = '';
  password = '';
  firstName = '';
  lastName = '';
  birthDate = '';
  constructor(private authorizationService: AuthorizationService) {}

  async signUp() {
    await this.authorizationService.signUp(this.email, this.password, this.firstName, this.lastName, this.birthDate);
    window.location.href = '/profile';
  }
}
