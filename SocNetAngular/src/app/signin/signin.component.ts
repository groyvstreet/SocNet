import { Component } from '@angular/core';
import { AuthorizationService } from '../services/authorization.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})
export class SigninComponent {
  email = '';
  password = '';
  constructor(private authorizationService: AuthorizationService) {}

  async signIn() {
    await this.authorizationService.signIn(this.email, this.password);
    window.location.href = '/profile';
  }
}
