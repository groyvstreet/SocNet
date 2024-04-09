import { Component } from '@angular/core';
import { ChatService } from '../services/chat.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import Chat from '../entities/chat';
import User from '../entities/user';
import { UserService } from '../services/user.service';
import Message from '../entities/message';
import { MessageService } from '../services/message.service';
import { MessageComponent } from '../message/message.component';
import { AuthorizationService } from '../services/authorization.service';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [FormsModule, MessageComponent],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent {
  chat = new Chat;
  users: User[] = [];
  chatUsers: User[] = [];
  messages: Message[] = [];
  text = '';
  user = new User;
  
  constructor(private chatService: ChatService,
    private userService: UserService,
    private messageService: MessageService,
    private router: Router,
    authorizationService: AuthorizationService) {
      authorizationService.user.subscribe(user => this.user = user);
    }

  async ngOnInit() {
    await this.loadChat();
    await this.loadUsers();
    await this.loadChatUsers();
    await this.loadMessages();
  }

  async updateChat() {
    await this.chatService.updateChat(this.chat.id, this.chat.name);
    alert('Обновлено');
  }

  async removeChat() {
    await this.chatService.removeChatById(this.chat.id);
    this.router.navigate(['/chats']);
  }

  async loadChat() {
    const parts = this.router.url.split('/');
    this.chat = await this.chatService.getChatById(parts[parts.length - 1]);
  }

  async loadUsers() {
    this.users = await this.userService.getUsersByChatId(this.chat.id, false);
  }

  async loadChatUsers() {
    this.chatUsers = await this.userService.getUsersByChatId(this.chat.id, true);
  }

  async addUserToChat(chatId: string, userId: string) {
    await this.chatService.addUserToChat(chatId, userId);
    this.ngOnInit();
  }

  async removeUserFromChat(chatId: string, userId: string) {
    await this.chatService.removeUserFromChat(chatId, userId);
    this.ngOnInit();
  }

  async loadMessages() {
    this.messages = await this.messageService.getMessagesByChatId(this.chat.id);
  }

  async addMessage() {
    await this.messageService.addMessage(this.chat.id, this.user.id, this.text);
    this.ngOnInit();
  }

  async updateMessage(messageId: string) {
    await this.messageService.updateMessage(messageId, this.text);
    this.ngOnInit();
  }

  async removeMessage(messageId: string) {
    await this.messageService.removeMessageById(messageId);
    this.ngOnInit();
  }
}
