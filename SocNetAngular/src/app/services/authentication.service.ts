import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  async signIn(email: string, password: string) {
    const response = await axios.post(`/auth/signin?email=${email}&password=${password}`);

    return response.data;
  }

  async signUp(email: string, password: string, firstName: string, lastName: string, birthDate: string) {
    const response = await axios.post('/auth/signup', {
        email,
        password,
        firstName,
        lastName,
        birthDate
    });

    return response.data;
  }
}
