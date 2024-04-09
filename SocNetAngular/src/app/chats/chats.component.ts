import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatService } from '../services/chat.service';
import User from '../entities/user';
import { AuthorizationService } from '../services/authorization.service';
import Chat from '../entities/chat';
import { ChatCardComponent } from '../chat-card/chat-card.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chats',
  standalone: true,
  imports: [FormsModule, ChatCardComponent],
  templateUrl: './chats.component.html',
  styleUrl: './chats.component.css'
})
export class ChatsComponent {
  name = '';
  user = new User;
  chats: Chat[] = [];
  
  constructor(private chatService: ChatService,
    authorizationService: AuthorizationService,
    private router: Router) {
    authorizationService.user.subscribe(user => this.user = user);
  }

  async ngOnInit(){
    this.chats = await this.chatService.getChats();
  }

  async addChat() {
    await this.chatService.addChat(this.name, this.user.id);
    this.ngOnInit();
  }

  navigateToChat(chatId: string) {
    this.router.navigate([`/chats/${chatId}`]);
  }
}
