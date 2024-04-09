import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor() { }

  async getChats() {
    const response = await axios.get('/chats');

    return response.data;
  }

  async addChat(name: string, userId: string) {
    const response = await axios.post('/chats', {
        name,
        userId
    });

    return response.data;
  }

  async getChatById(id: string) {
    const response = await axios.get(`/chats/${id}`);

    return response.data;
  }

  async addUserToChat(chatId: string, userId: string) {
    const response = await axios.post(`/chats/${chatId}/users/${userId}`)

    return response.data;
  }

  async removeUserFromChat(chatId: string, userId: string) {
    const response = await axios.delete(`/chats/${chatId}/users/${userId}`)

    return response.data;
  }

  async updateChat(id: string, name: string) {
    const response = await axios.put('/chats', {
        id,
        name
    });

    return response.data;
  }

  async removeChatById(id: string) {
    const response = await axios.delete(`/chats/${id}`);

    return response.data;
  }
}
