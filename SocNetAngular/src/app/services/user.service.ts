import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  async getUserById(id: string) {
    const response = await axios.get(`/users/${id}`);

    return response.data;
  }

  async getUsers() {
    const response = await axios.get('/users');

    return response.data;
  }

  async getUsersByChatId(chatId: string, isInChat: string) {
    const response = await axios.get(`/chats/${chatId}/users?isInChat=${isInChat}`);

    return response.data;
  }

  async updateUser(id: string, firstName: string, lastName: string, birthDate: string, image: string) {
    const response = await axios.put('/users', {
        id,
        firstName,
        lastName,
        birthDate,
        image
    });

    return response.data;
  }

  async removeUserById(id: string) {
    const response = await axios.delete(`/users/${id}`);

    return response.data;
  }
}
