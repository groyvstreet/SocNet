import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor() { }

  async addMessage(chatId: string, userId: string, text: string) {
    const response = await axios.post('/messages', {
        text,
        chatId,
        userId
    });

    return response.data;
  }

  async updateMessage(id: string, text: string) {
    const response = await axios.put('/messages', {
        id,
        text
    });

    return response.data;
  }

  async removeMessageById(id: string) {
    const response = await axios.delete(`/messages/${id}`);

    return response.data;
  }

  async getMessagesByChatId(chatId: string) {
    const response = await axios.get(`/messages?chatId=${chatId}`);

    return response.data;
  }
}
