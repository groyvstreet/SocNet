import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import axios from 'axios';
import { AuthorizationService } from './services/authorization.service';

axios.defaults.baseURL = 'http://localhost:8080';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  isAuthenticated = false;

  constructor(authorizationService: AuthorizationService) {
    this.isAuthenticated = authorizationService.getIsAuthenticated();
  }
}
