import {WebSocketService} from '../services/websocket.service';
import {Component} from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  constructor(private webSocketService: WebSocketService) {}
}
