import { Component, Input } from '@angular/core';
import Chat from '../entities/chat';

@Component({
  selector: 'app-chat-card',
  standalone: true,
  imports: [],
  templateUrl: './chat-card.component.html',
  styleUrl: './chat-card.component.css'
})
export class ChatCardComponent {
  @Input() chat: Chat = new Chat;
}
