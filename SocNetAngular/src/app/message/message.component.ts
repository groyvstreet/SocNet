import { Component, EventEmitter, Input, Output } from '@angular/core';
import Message from '../entities/message';

@Component({
  selector: 'app-message',
  standalone: true,
  imports: [],
  templateUrl: './message.component.html',
  styleUrl: './message.component.css'
})
export class MessageComponent {
  @Input() message = new Message;
  @Output() updateMessage = new EventEmitter();
  @Output() removeMessage = new EventEmitter();

  updateMessageOnClick() {
    this.updateMessage.emit();
  }

  removeMessageOnClick() {
    this.removeMessage.emit();
  }
}
